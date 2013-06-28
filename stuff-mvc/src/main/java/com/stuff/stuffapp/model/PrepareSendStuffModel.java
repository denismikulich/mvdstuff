package com.stuff.stuffapp.model;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.stuff.stuffapp.data.StuffType;
import com.stuff.stuffapp.formbean.PrepareStuffsFormBean;
import com.stuff.stuffapp.formbean.StuffStateFormBean;
import com.stuff.stuffapp.service.DBService;

@Component
public class PrepareSendStuffModel {

	@Autowired
	private DBService dbService;
	
	private PrepareStuffsFormBean prepareBean;
	private PreparedStuffCollection preparedStuffs;

	public PrepareSendStuffModel() {
	}
	
	@PostConstruct
	private void init() {
		preparedStuffs = new PreparedStuffCollection(dbService);
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

	/**
	 * Return List of the form beans which contain state info about stuffs.
	 * 
	 * @return List<StuffStateFormBean>
	 */
	public List<StuffStateFormBean> getStates() {
		return preparedStuffs.buildFormBean();
	}

	public void submitData() {
		/*
		 * for (Stuff stuff : stuffList) { stuff = stuffDao.saveStuff(stuff);
		 * 
		 * StuffFlow flow = new StuffFlow();
		 * flow.setSender(prepareBean.getSender());
		 * flow.setReciever(prepareBean.getReciever()); flow.setStuff(stuff);
		 * flow.setSendNumber(prepareBean.getSendNumber());
		 * flow.setSendDate(prepareBean.getSendDate());
		 * flow.setSign(prepareBean.getSign()); flow.setUser(user);
		 * stuffFlowDao.saveFlow(flow); }
		 */

	}

}
