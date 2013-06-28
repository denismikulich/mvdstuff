package com.stuff.stuffapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stuff.stuffapp.dao.StuffFlowDao;
import com.stuff.stuffapp.data.FlowBO;
import com.stuff.stuffapp.data.StuffBO;
import com.stuff.stuffapp.data.StuffType;
import com.stuff.stuffapp.domain.StuffFlow;
import com.stuff.stuffapp.formbean.StuffSearchCriteria;
import com.stuff.stuffapp.service.DBService;

/**
 * Handles requests for the application Staff History page.
 */
@Controller
public class StuffController extends BaseController {

	@Autowired
	StuffFlowDao stuffFlowDao;
	
	@Autowired
	private DBService dbService;

	@Override
	public String getPageHeaderHtml() {
		return "stuff.header.text";
	}

	/**
	 * Get stuff history by stuff number and year.
	 * 
	 * @param stuff
	 * @param year
	 * @return
	 */
	@RequestMapping(value = "/main/stuff", method = RequestMethod.GET)
	public ModelAndView handleMain(
			@RequestParam(value = "stuff", required = false) String stuff,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "year", required = false) Integer year) {

		List<FlowBO> stuffFlows = null;
		StuffBO bo = new StuffBO();
		bo.setRegNumber(stuff);
		bo.setType(StuffType.valueOf(type));
		bo.setYear(year);
		stuffFlows = dbService.getStuffHistory(bo);

		ModelAndView model = new ModelAndView("stuff");
		model.addObject("stuffFlows", stuffFlows);

		return super.baseHandle(model);
	}
}
