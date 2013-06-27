package com.stuff.stuffapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.stuff.stuffapp.dao.StuffDao;
import com.stuff.stuffapp.dao.StuffFlowDao;
import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.domain.StuffFlow;
import com.stuff.stuffapp.domain.User;
import com.stuff.stuffapp.formbean.PrepareStuffsFormBean;
import com.stuff.stuffapp.formbean.StuffNumberBean;
import com.stuff.stuffapp.loginservice.UserDetailsImpl;

@Component
public class PrepareSendStuffModel {

	private PrepareStuffsFormBean prepareBean;
	private List<StuffNumberBean> stuffNumbers;
	private List<Stuff> stuffList;

	@Autowired
	private StuffDao stuffDao;
	
	@Autowired
	private StuffFlowDao stuffFlowDao;

	public PrepareSendStuffModel() {
		resetData();
	}

	public void resetData() {
		prepareBean = new PrepareStuffsFormBean();
		prepareBean.setSendDate(new Date());
		stuffNumbers = new ArrayList<StuffNumberBean>();
	}

	public void processData() {
		String stuffs = prepareBean.getListStuffs();
		if (stuffs == null)
			return;
		String[] stuffArray = stuffs.split("[ .,]");
		stuffNumbers.clear();
		for (String str : stuffArray) {
			str = str.trim();
			if (!str.isEmpty()) {
				stuffNumbers.add(new StuffNumberBean(str, 1));
			}
		}
		checkStuffExist();
	}

	private void checkStuffExist() {
		stuffList = new ArrayList<Stuff>();
		for (StuffNumberBean stuffNumberBean : stuffNumbers) {
			Stuff stuffEntity = stuffDao.findStuff(
					stuffNumberBean.getStuffNumber(),
					prepareBean.getStuffsYear());
			if (stuffEntity == null) { // not created entity.
				stuffEntity = new Stuff();
				stuffEntity.setRegNumber(stuffNumberBean.getStuffNumber());
				stuffEntity.setType(prepareBean.getStuffsType());
				stuffEntity.setYear(prepareBean.getStuffsYear());
				stuffNumberBean.setState(3); // state not created;
				try {
					Integer.parseInt(stuffNumberBean.getStuffNumber());
				} catch(NumberFormatException ex) {
					stuffNumberBean.setState(4); // state not created and regNumber contain symbols.
				}
				
			} else { // stuff is exist
				// TODO:
				// boolean bWrongPlace = isWrongPlace(Stuff stuff);
			}
			
			if (isRepeat(stuffNumberBean)) {
				stuffNumberBean.setState(5);
			}
			stuffList.add(stuffEntity);
		}
	}

	private boolean isRepeat(StuffNumberBean bean) {
		for (StuffNumberBean stuffNumberBean : stuffNumbers) {
			if (stuffNumberBean != bean && stuffNumberBean.getStuffNumber().equals(bean.getStuffNumber())) {
				return true;
			}
		}
		return false;
	}
	
	public PrepareStuffsFormBean getPrepareBean() {
		return prepareBean;
	}

	public void setPrepareBean(PrepareStuffsFormBean prepareBean) {
		this.prepareBean = prepareBean;
	}

	public List<StuffNumberBean> getStuffNumbers() {
		return stuffNumbers;
	}

	public void setStuffNumbers(List<StuffNumberBean> stuffNumbers) {
		this.stuffNumbers = stuffNumbers;
	}

	public void submitData() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetailsImpl userDet = (UserDetailsImpl) auth.getPrincipal();
		User user = userDet.getUser();
		for (Stuff stuff : stuffList) {
			stuff = stuffDao.saveStuff(stuff);
			
			StuffFlow flow = new StuffFlow();
			flow.setSender(prepareBean.getSender());
			flow.setReciever(prepareBean.getReciever());
			flow.setStuff(stuff);
			flow.setSendNumber(prepareBean.getSendNumber());
			flow.setSendDate(prepareBean.getSendDate());
			flow.setSign(prepareBean.getSign());
			flow.setUser(user);
			stuffFlowDao.saveFlow(flow);
		}

	}

}
