package com.stuff.stuffapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stuff.stuffapp.data.SearchResultData;

@Controller
public class SearchResultController  extends BaseController {

	@Autowired
	SearchResultData searchResultData;
	
	@Override
	public String getPageHeaderHtml() {
		return "searchresultpage.header.text";
	}
	
	@RequestMapping(value = "/main/searchresult", method = RequestMethod.GET)
	public ModelAndView handleSearch() {
		ModelAndView model = new ModelAndView("searchresult");
		model.addObject("searchResult", searchResultData.getData());
		return baseHandle(model);
	}

}
