package com.stuff.stuffapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.stuff.stuffapp.domain.User;
import com.stuff.stuffapp.model.AdminModel;
import com.stuff.stuffapp.validator.NewUserValidator;

/**
 * Handles requests for the application Admin page.
 */
@Controller
public class AdminController extends BaseController {

	Logger log = Logger.getLogger(AdminController.class);
	
	@Autowired
	private AdminModel pageModel;

	@Autowired
	private NewUserValidator validator;

	@RequestMapping(value = "/admin/admin", method = RequestMethod.GET)
	public ModelAndView handleAdmin() {
		return commonPageHandle(new ModelAndView("admin"), true);
	}

	/**
	 * handler for Add New User form.
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/admin/submitUser", method = RequestMethod.POST)
	public ModelAndView handleSubmitUser(
			@ModelAttribute("EditableUser") User user, BindingResult result) {

		validator.validate( user, result);
		if (result.hasErrors()) {
			return commonPageHandle(new ModelAndView("admin"), false);
		}

		pageModel.submitUser(user);
		
		return commonPageHandle(new ModelAndView("admin"), true);
	}

	/**
	 * Send common page data.
	 * 
	 * @param model
	 *            ModelAndView
	 * @param isUserValid
	 *            boolean if false, method don't send User bean (its reset
	 *            validation).
	 * @return filled model.
	 */
	private ModelAndView commonPageHandle(ModelAndView model,
			boolean isUserValid) {
		model.addObject("userList", pageModel.getUserList());
		model.addObject("selectableRoles", pageModel.getRoleList());
		if (isUserValid) {
			model.addObject("EditableUser", pageModel.getEditableUser());
		}
		model.addObject("bShowUserEditForm", pageModel.getEditableUser()!=null);
		return super.baseHandle(model);
	}

	@Override
	public String getPageHeaderHtml() {
		return "adminpage.header.text";
	}
	
	/**
	 * Reset selected user for editing and init User for adding process.
	 * @return
	 */
	@RequestMapping(value = "/admin/onAddUser", method = RequestMethod.POST)
	public ModelAndView onAddUserPOST() {
		pageModel.setEditableUserAsFakeNewUser();
		return commonPageHandle(new ModelAndView("admin"), true);
	}
	
	@RequestMapping(value = "/admin/onAddUser", method = RequestMethod.GET)
	public ModelAndView onAddUserGET() {
		return onAddUserPOST();
	}
	
	/**
	 * This Servlet find user in database and set in form user attributes for editing.
	 * @param request parameter "userid" contain User.id .
	 * @return
	 */
	@RequestMapping(value = "/admin/onEditUser", method = RequestMethod.POST)
	public ModelAndView onEditUserPOST(HttpServletRequest request) {
		String param = request.getParameter("userid");
		if (param == null) {
			return commonPageHandle(new ModelAndView("admin"), true);
		}
		Long userId = null;
		try {
			userId = Long.parseLong(param);
		} catch (NumberFormatException ex) {
			log.error("Error convert userid selected in table of users", ex);
		}
		if (param != null) {
			pageModel.setEditableUserId(userId);	// Set in model userID that selected on page for editing.
		}
		return commonPageHandle(new ModelAndView("admin"), true);
	}
	
	@RequestMapping(value = "/admin/onEditUser", method = RequestMethod.GET)
	public ModelAndView onEditUserGET() {
		pageModel.setEditableUserAsFakeNewUser();
		return commonPageHandle(new ModelAndView("admin"), true);
	}
	
	/**
	 * 1. Reset selecting user for editing.
	 * 2. Delete selected user.
	 * @return
	 */
	@RequestMapping(value = "/admin/onDeleteUser", method = RequestMethod.POST)
	public ModelAndView onDeleteUserPOST(HttpServletRequest request) {
		String param = request.getParameter("userid");
		if (param == null) {
			return commonPageHandle(new ModelAndView("admin"), true);
		}
		Long userId = null;
		try {
			userId = Long.parseLong(param);
		} catch (NumberFormatException ex) {
			log.error("Error convert userid selected in table of users", ex);
		}
		pageModel.deleteUser(userId);
		return commonPageHandle(new ModelAndView("admin"), true);
	}
	
	@RequestMapping(value = "/admin/onDeleteUser", method = RequestMethod.GET)
	public ModelAndView onDeleteUserGET() {
		pageModel.setEditableUserAsFakeNewUser();
		return commonPageHandle(new ModelAndView("admin"), true);
	}
}
