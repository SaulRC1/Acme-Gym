package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Admin;
import domain.Client;
import domain.Manager;
import services.AdminService;
import services.ManagerService;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

	@Autowired
	private AdminService adminService;

	// Constructors -----------------------------------------------------------

	public AdminController() {
		super();
	}

	// action1-List ---------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Admin> admins;

		admins = this.adminService.findAll();

		result = new ModelAndView("admin/list");
		result.addObject("admins", admins);
		result.addObject("requestURI", "admin/list.do");

		return result;
	}

	// Action2-Create ---------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Admin admin;
		
		admin = this.adminService.create();
		
		result = new ModelAndView("admin/create");
		result.addObject("admin", admin);
		return result;
	}

	// Action3-Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int adminId) {
		ModelAndView result;
		Admin admin;
		
		admin = this.adminService.findOne(adminId);
		Assert.notNull(admin);
		
		result = this.createEditModelAndView(admin);
		return result;
	}

	// Action4-Save ---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Admin admin, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(admin);
		else
			try {
			    	System.out.print(admin.getEmail());
				this.adminService.save(admin);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(admin, "admin.commit.error");
			}
		return result;
	}

	// Action5-Delete
	// ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Admin admin) {
		ModelAndView result;
		try {
			this.adminService.delete(admin);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(admin, "admin.commit.error");
		}
		return result;
	}

	// Ancillary
	// Method---------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Admin admin) {
		ModelAndView result;
		result = this.createEditModelAndView(admin, null);
		return result;
	}

	// Core Method---------------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(final Admin admin, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("admin/edit");
		result.addObject("admin", admin);
		result.addObject("message", messageCode);
		return result;
	}

}
