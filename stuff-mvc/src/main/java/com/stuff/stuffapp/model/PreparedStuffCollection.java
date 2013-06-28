package com.stuff.stuffapp.model;

import java.util.ArrayList;
import java.util.List;

import com.stuff.stuffapp.data.StuffBO;
import com.stuff.stuffapp.data.StuffState;
import com.stuff.stuffapp.data.StuffType;
import com.stuff.stuffapp.formbean.StuffStateFormBean;
import com.stuff.stuffapp.service.DBService;

public class PreparedStuffCollection {

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
	public void processData(String numbersCommaList, StuffType type, int year) {
		if (numbersCommaList == null)
			return;
		String[] numbersArray = numbersCommaList.split("[ .,]");
		mapper.clear();
		for (String regNumber : numbersArray) {
			regNumber = regNumber.trim();
			if (!regNumber.isEmpty()) {
				StuffBO stuff = new StuffBO();
				stuff.setRegNumber(regNumber);
				stuff.setType(type);
				stuff.setYear(year);
				mapper.add(new StuffAndStateContainer(stuff, StuffState.UNCHECKED));
			}
		}
		checkStuffsState();
	}

	private void checkStuffsState() {

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
				// TODO:
				// boolean bWrongPlace = isWrongPlace(Stuff stuff);
				sNs.state = StuffState.OK;
			}
			checkRepeat(sNs);
		}
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
					sNs.state.ordinal());
			resultList.add(bean);
		}
		return resultList;
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
