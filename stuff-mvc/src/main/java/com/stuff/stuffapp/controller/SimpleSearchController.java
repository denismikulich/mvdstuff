package com.stuff.stuffapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stuff.stuffapp.domain.Stuff;
import com.stuff.stuffapp.formbean.StuffSearchCriteria;
import com.stuff.stuffapp.service.DBService;

@Controller
public class SimpleSearchController extends BaseController {

	private StuffSearchCriteria searchCriteria;

	@Autowired
	private DBService dbService;

	@RequestMapping(value = "/main/simpleSearch", method = RequestMethod.GET)
	public ModelAndView handleSearch(@RequestParam(value = "noresult", required = false) Boolean showNoResults) {
		ModelAndView model = new ModelAndView("simplesearch");
		model.addObject("showNoResult", showNoResults);
		searchCriteria = new StuffSearchCriteria(); // clear search data.
		return commonSearchHandle(model);
	}

	@RequestMapping(value = "/main/processSimpleSearch", method = RequestMethod.GET)
	public ModelAndView ProcessGET() {
		return handleSearch(false);
	}

	@RequestMapping(value = "/main/processSimpleSearch", method = RequestMethod.POST)
	public ModelAndView ProcessPOST(@ModelAttribute("criteria") StuffSearchCriteria bean) {
		Stuff stuff = dbService.getStuff(bean);
		if (stuff != null) {
			ModelAndView model = new ModelAndView("redirect:/main/stuff?id=" + stuff.getId());
			return model;
		} else {
			return handleSearch(true);
		}
	}

	private ModelAndView commonSearchHandle(ModelAndView model) {
		model.addObject("criteria", searchCriteria);
		return baseHandle(model);
	}

	@Override
	public String getPageHeaderHtml() {
		return "simplesearch.header.text";
	}
}
