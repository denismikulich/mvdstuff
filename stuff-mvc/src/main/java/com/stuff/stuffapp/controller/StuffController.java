package com.stuff.stuffapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stuff.stuffapp.dao.StuffFlowDao;
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
		return "Stuff Page";
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
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "stuff", required = false) String stuff,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "year", required = false) Integer year) {

		List<StuffFlow> stuffFlows = null;
		if (id != null) {
			stuffFlows = dbService.getStuffHistory(id);
		} else {
			StuffSearchCriteria criteria = new StuffSearchCriteria();
			criteria.setStuffNumber(stuff);
			criteria.setType(type);
			criteria.setStuffsYear(year);
			stuffFlows = dbService.getStuffHistory(criteria);
		}

		ModelAndView model = new ModelAndView("stuff");
		model.addObject("stuffFlows", stuffFlows);

		return super.baseHandle(model);
	}
}
