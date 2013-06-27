package com.stuff.stuffapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.stuff.stuffapp.loginservice.UserDetailsImpl;

/**
 * This class define common things in controller.
 * 
 * @author Denis.Mikulich
 *
 */
public abstract class BaseController implements IBaseController {

	public final String ATTR_HEADER_HTML_CONTEXT = "headerHtmlContext";
	public final String ATTR_HOME_PAGE_URL = "homePageUrl";
	public final String ATTR_USER_FULL_NAME = "userFullName";
	
	public ModelAndView baseHandle(ModelAndView model) {
		if (model == null) {
			throw new NullPointerException("ModelAndView is not initialized");
		}
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetailsImpl userDet = (UserDetailsImpl) auth.getPrincipal();
		
		model.addObject(ATTR_HEADER_HTML_CONTEXT, getPageHeaderHtml());
		model.addObject(ATTR_HOME_PAGE_URL, getHomePageUrl() );
		model.addObject(ATTR_USER_FULL_NAME, userDet.getUser().getFirstname() + " " +userDet.getUser().getLastname());
		return model;
	}

	@Override
	public String getBackPageUrl() {
		return null;
	}

	@Override
	public String getHomePageUrl() {
		return "../main/main";
	}
	
	/**
	 * This method must return Page Title.
	 */
	@Override
	public abstract String getPageHeaderHtml();
	
	
}
