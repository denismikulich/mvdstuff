package com.stuff.stuffapp.controller;

import org.springframework.web.servlet.ModelAndView;

public interface IBaseController {

	/**
	 * Initialize header html context.
	 * 
	 * @return html context.
	 */
	public String getPageHeaderHtml();

	/**
	 * @return url to back page.
	 */
	public String getBackPageUrl();

	/**
	 * @return URL to home page.
	 */
	public String getHomePageUrl();

	/**
	 * Add basic attributes to the ModelAndView object returned in HTTP
	 * response.
	 * 
	 * @param model
	 *            must be not null;
	 * @return ModelAndView object with common attributes.
	 */
	public ModelAndView baseHandle(ModelAndView model);
}
