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

import com.stuff.stuffapp.formbean.PrepareStuffsFormBean;
import com.stuff.stuffapp.model.PrepareSendStuffModel;

@Controller
public class PrepareSendStuffController extends BaseController {

	@Autowired
	private PrepareSendStuffModel pageModel;
	
	private boolean isBack = false;
	
	@Override
	public String getPageHeaderHtml() {
		return "Send Stuff";
	}
	
	@RequestMapping(value = "/main/sendstuff", method = RequestMethod.GET)
	public ModelAndView handleMain() {
		if (!isBack) {
			pageModel.resetData();
		}
		isBack = false;
		ModelAndView model = new ModelAndView("sendstuff");
		model.addObject("sendBean", pageModel.getPrepareBean());
		model.addObject("stuffNumbers", pageModel.getStuffNumbers());
		return super.baseHandle(model);
	}
	
	@RequestMapping(value = "/main/processStuffs", method = RequestMethod.GET)
	public ModelAndView processStuffsGET(@ModelAttribute("sendBean") PrepareStuffsFormBean bean) {
		return handleMain();
	}
	
	@RequestMapping(value = "/main/processStuffs", method = RequestMethod.POST)
	public ModelAndView processStuffsPOST(@ModelAttribute("sendBean") PrepareStuffsFormBean bean) {
		ModelAndView model = new ModelAndView("redirect:/main/submitsending");
		pageModel.setPrepareBean(bean);
		pageModel.processData();
		return super.baseHandle(model);
	}
	
	@RequestMapping(value = "/main/submitStuffs", method = RequestMethod.POST, params = "next")
	public ModelAndView handleSubmitStuffsSubmit() {
		ModelAndView model = new ModelAndView("redirect:/main/main");
		pageModel.submitData();
		return super.baseHandle(model);
	}
	
	@RequestMapping(value = "/main/submitStuffs", method = RequestMethod.POST, params = "back")
	public ModelAndView handleSubmitStuffsBack() {
		isBack = true;
		ModelAndView model = new ModelAndView("redirect:/main/sendstuff");
		return super.baseHandle(model);
	}
	
	@RequestMapping(value = "/main/submitsending", method = RequestMethod.GET)
	public ModelAndView handleSubmitSending() {
		ModelAndView model = new ModelAndView("submitsending");
		model.addObject("sendBean", pageModel.getPrepareBean());
		model.addObject("stuffNumbers", pageModel.getStuffNumbers());
		return super.baseHandle(model);
	}
	
	/**
	 * Binder. Convert Date-format between browser and servlet. 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}

}
