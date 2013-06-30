package com.stuff.stuffapp.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.stuff.stuffapp.data.FlowBO;
import com.stuff.stuffapp.data.StuffBO;
import com.stuff.stuffapp.data.StuffState;
import com.stuff.stuffapp.data.StuffType;
import com.stuff.stuffapp.exception.StuffBusinessException;
import com.stuff.stuffapp.formbean.PrepareStuffsFormBean;
import com.stuff.stuffapp.formbean.StuffStateFormBean;
import com.stuff.stuffapp.service.DBService;

public class PreparedStuffCollection {
	
	private static Logger log = Logger.getLogger(PreparedStuffCollection.class);

	List<StuffAndStateContainer> mapper;
	DBService dbService;

	public PreparedStuffCollection(DBService dbService) {
		this.dbService = dbService;
		mapper = new ArrayList<StuffAndStateContainer>();
	}

	public void clear() {
		mapper.clear();
	}

	/**
	 * Split numbers comma list and put it into the state map. Check stuff state
	 * with DB.
	 * 
	 * @param numbersCommaList
	 *            Numbers comma list.
	 * @param type
	 *            Stuff type.
	 * @param year
	 *            Stuff year.
	 */
	public void processData(PrepareStuffsFormBean prepareBean) {
		if (prepareBean == null) {
			return;
		}
		String numbersCommaList = prepareBean.getListStuffs();
		if (numbersCommaList == null)
			return;
		String[] numbersArray = numbersCommaList.split("[ .,]");
		mapper.clear();
		for (String regNumber : numbersArray) {
			regNumber = regNumber.trim();
			if (!regNumber.isEmpty()) {
				StuffBO stuff = new StuffBO();
				stuff.setRegNumber(regNumber);
				stuff.setType(StuffType.valueOf(prepareBean.getStuffsType()));
				stuff.setYear(prepareBean.getStuffsYear());
				mapper.add(new StuffAndStateContainer(stuff, StuffState.UNCHECKED));
			}
		}
		checkStuffsState(prepareBean.getSender());
	}

	private void checkStuffsState(String sender) {

		for (StuffAndStateContainer sNs : mapper) {
			if (!dbService.isStuffExist(sNs.stuff)) { // not created entity.
				sNs.state = StuffState.NOT_CREATED;
				try {
					Integer.parseInt(sNs.stuff.getRegNumber());
				} catch (NumberFormatException ex) {
					sNs.state = StuffState.CONTAIN_SIMBOLS; // state not created
															// and regNumber
															// contain symbols.
				}
			} else { // stuff is exist
				if (isWrongPlace(sNs.stuff, sender)) {
					sNs.state = StuffState.WRONG_PLACE;
				}
				sNs.state = StuffState.OK;
			}
			checkRepeat(sNs);
		}
	}

	/**
	 * check last recipient and current sender.
	 * @param stuff
	 * @param sender
	 * @return
	 */
	private boolean isWrongPlace(StuffBO stuff, String sender) {
		FlowBO lastFlow = dbService.getLastFlow(stuff);
		if (lastFlow == null) {
			return false;
		}
		return !lastFlow.getRecipient().equals(sender);
	}
	private boolean checkRepeat(StuffAndStateContainer checked) {
		for (StuffAndStateContainer sNs : mapper) {
			if (sNs != checked && sNs.stuff.equals(checked.stuff)) {
				checked.state = StuffState.REPEAT;
			}
		}
		return false;
	}

	public List<StuffStateFormBean> buildFormBean() {
		List<StuffStateFormBean> resultList = new ArrayList<StuffStateFormBean>();
		for (StuffAndStateContainer sNs : mapper) {
			StuffStateFormBean bean = new StuffStateFormBean(sNs.stuff.getRegNumber(),
					sNs.state);
			resultList.add(bean);
		}
		return resultList;
	}
	
	public void save(PrepareStuffsFormBean prepareBean) {
		for (StuffAndStateContainer sNs : mapper) {
			FlowBO flow = new FlowBO();
			flow.setSender(prepareBean.getSender());
			flow.setRecipient(prepareBean.getReciever());
			flow.setStuff(sNs.stuff);
			flow.setOutgoingNo(prepareBean.getSendNumber());
			flow.setOutgoingDate(prepareBean.getSendDate());
			flow.setSignature(prepareBean.getSign());
			flow.setDescription(prepareBean.getDescription());
			try {
				dbService.saveFlow(flow);
			} catch (StuffBusinessException e) {
				log.error("Business Exception");
				return;
			}
		}
		
	}


	/**
	 * Stuff and it state container.
	 * 
	 * @author Home
	 * 
	 */
	private class StuffAndStateContainer {
		public StuffBO stuff;
		public StuffState state;

		public StuffAndStateContainer(StuffBO stuff, StuffState state) {
			this.stuff = stuff;
			this.state = state;
		}
	}

}
