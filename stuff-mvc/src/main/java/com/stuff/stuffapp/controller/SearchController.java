package com.stuff.stuffapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stuff.stuffapp.formbean.SearchCriteriaBean;
import com.stuff.stuffapp.model.SearchModel;

/**
 * Handles requests for the application Search page.
 */
@Controller
public class SearchController extends BaseController {

	@Autowired
	private SearchModel pageModel;
	
	@Override
	public String getPageHeaderHtml() {
		return "Search criteria";
	}

	@RequestMapping(value = "/main/search", method = RequestMethod.GET)
	public ModelAndView handleSearch() {
		ModelAndView model = new ModelAndView("search");
		pageModel.clearSearchResult(); // clear search criteria.
		return commonSearchHandle(model);
	}
	
	private ModelAndView commonSearchHandle(ModelAndView model) {
		model.addObject("criteria", pageModel.getSearchCriteriaBean());
		model.addObject("searchResult", pageModel.getSearchResult());
		return baseHandle(model);
	}
	
	@RequestMapping(value = "/main/processSearch", method = RequestMethod.GET)
	public ModelAndView processSearchGET() {
		return handleSearch();
	}
	
	@RequestMapping(value = "/main/processSearch", method = RequestMethod.POST)
	public ModelAndView processSearchPOST(@ModelAttribute("criteria")SearchCriteriaBean bean) {
		ModelAndView model = new ModelAndView("search");
		pageModel.setSearchCriteriaBean(bean);
		pageModel.processSearch();
		return commonSearchHandle(model);
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, true));
	}
}
