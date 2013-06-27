package com.stuff.stuffapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application Main page.
 */
@Controller
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "index";
	}
}
