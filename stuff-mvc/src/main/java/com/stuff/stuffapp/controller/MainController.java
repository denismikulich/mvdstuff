package com.stuff.stuffapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application Main page.
 */
@Controller
public class MainController extends BaseController {

	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView handleDefault(ModelMap model) {
		return handleMain(model);
	}*/

	@RequestMapping(value = "/main/main", method = RequestMethod.GET)
	public ModelAndView handleMain(ModelMap model) {
		return super.baseHandle(new ModelAndView("main"));
	}

	@Override
	public String getPageHeaderHtml() {
		return "mainpage.header.text";
	}

}
