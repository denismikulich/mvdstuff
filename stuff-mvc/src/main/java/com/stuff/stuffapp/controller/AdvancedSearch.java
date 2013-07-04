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

import com.stuff.stuffapp.data.SearchResultData;
import com.stuff.stuffapp.formbean.SearchCriteriaBean;
import com.stuff.stuffapp.service.DBService;

@Controller
public class AdvancedSearch extends BaseController {
	@Autowired
	DBService dbService;

	@Autowired
	SearchResultData searchResultData;
	
	@Override
	public String getPageHeaderHtml() {
		return "searchpage.header.text";
	}
	
	@RequestMapping(value = "/main/advanced", method = RequestMethod.GET)
	public ModelAndView handleSearch() {
		ModelAndView model = new ModelAndView("advanced");
		model.addObject("criteria",new SearchCriteriaBean());
		return baseHandle(model);
	}
	
	@RequestMapping(value = "/main/processSearch", method = RequestMethod.POST)
	public ModelAndView processSearchPOST(@ModelAttribute("criteria")SearchCriteriaBean bean) {
		dbService.advancedSearch(bean);
		searchResultData.setData(dbService.advancedSearch(bean));
		ModelAndView model = new ModelAndView("redirect:/main/searchresult");
		return baseHandle(model);
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, true));
	}

}
