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
import com.stuff.stuffapp.data.StuffType;
import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.domain.StuffFlow;
import com.stuff.stuffapp.domain.User;
import com.stuff.stuffapp.formbean.PrepareStuffsFormBean;
import com.stuff.stuffapp.formbean.StuffNumberBean;
import com.stuff.stuffapp.loginservice.UserDetailsImpl;

@Component
public class PrepareSendStuffModel {

	private PrepareStuffsFormBean prepareBean;
	private PreparedStuffCollection preparedStuffs;

	@Autowired
	private StuffDao stuffDao;

	@Autowired
	private StuffFlowDao stuffFlowDao;

	public PrepareSendStuffModel() {
		preparedStuffs = new PreparedStuffCollection();
		resetData();
	}

	public void resetData() {
		prepareBean = new PrepareStuffsFormBean();
		prepareBean.setSendDate(new Date());
		preparedStuffs.clear();
	}

	public void processData() {
		preparedStuffs.processData(prepareBean.getListStuffs(),
				StuffType.valueOf(prepareBean.getStuffsType()), prepareBean.getStuffsYear());
	}

	public PrepareStuffsFormBean getPrepareBean() {
		return prepareBean;
	}

	public void setPrepareBean(PrepareStuffsFormBean prepareBean) {
		this.prepareBean = prepareBean;
	}

	public void submitData() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDet = (UserDetailsImpl) auth.getPrincipal();
		User user = userDet.getUser();
		/*for (Stuff stuff : stuffList) {
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
		}*/

	}

}
