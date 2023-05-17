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
import domain.Gym;
import domain.Manager;
import services.AdminService;
import services.ManagerService;
import services.gym.GymService;

@Controller
@RequestMapping("/manager")
public class ManagerController extends AbstractController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private GymService gymService;
    @Autowired
    private AdminService adminService;

    public ManagerController() {
	super();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
	ModelAndView result;
	Collection<Manager> bannedmanagers;
	Collection<Manager> unbannedmanagers;

	bannedmanagers = this.managerService.findAll();
	unbannedmanagers = this.managerService.findByBannedFalse();
	bannedmanagers.removeAll(unbannedmanagers);

	result = new ModelAndView("manager/list");
	result.addObject("bannedManagers", bannedmanagers);
	result.addObject("unbannedManagers", unbannedmanagers);
	result.addObject("requestURI", "manager/list.do");

	return result;
    }

    @RequestMapping(value = "/banned", method = RequestMethod.GET)
    public ModelAndView banned(@RequestParam final int managerId) {
	ModelAndView result;
	Manager manager;
	Collection<Manager> bannedmanagers;
	Collection<Manager> unbannedmanagers;

	manager = this.managerService.findOne(managerId);
	manager.setBanned(true);
	this.managerService.save(manager);

	bannedmanagers = this.managerService.findAll();
	unbannedmanagers = this.managerService.findByBannedFalse();
	bannedmanagers.removeAll(unbannedmanagers);

	result = new ModelAndView("manager/list");
	result.addObject("bannedManagers", bannedmanagers);
	result.addObject("unbannedManagers", unbannedmanagers);
	result.addObject("requestURI", "manager/list.do");

	return result;
    }

    @RequestMapping(value = "/unbanned", method = RequestMethod.GET)
    public ModelAndView unbanned(@RequestParam final int managerId) {
	ModelAndView result;
	Manager manager;
	Collection<Manager> bannedmanagers;
	Collection<Manager> unbannedmanagers;

	manager = this.managerService.findOne(managerId);
	manager.setBanned(false);
	this.managerService.save(manager);

	bannedmanagers = this.managerService.findAll();
	unbannedmanagers = this.managerService.findByBannedFalse();
	bannedmanagers.removeAll(unbannedmanagers);

	result = new ModelAndView("manager/list");
	result.addObject("bannedManagers", bannedmanagers);
	result.addObject("unbannedManagers", unbannedmanagers);
	result.addObject("requestURI", "manager/list.do");

	return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
	ModelAndView result;
	Manager manager;
	manager = this.managerService.create();
	result = this.createEditModelAndView(manager);
	result.addObject("cancelUrl", "'welcome/index.do'");
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int managerId) {
	ModelAndView result;
	Manager manager;
	manager = this.managerService.findOne(managerId);
	Assert.notNull(manager);
	result = this.createEditModelAndView(manager);
	result.addObject("cancelUrl", "'manager/list.do'");
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid final Manager manager, final BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors())
	    result = this.createEditModelAndView(manager);
	else
	    try {
		this.managerService.save(manager);
		result = new ModelAndView("redirect:list.do");
	    } catch (final Throwable oops) {
		result = this.createEditModelAndView(manager, "manager.commit.error");
	    }
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(final Manager manager) {
	ModelAndView result;
	try {
	    this.managerService.delete(manager);
	    result = new ModelAndView("redirect:list.do");
	} catch (final Throwable oops) {
	    result = this.createEditModelAndView(manager, "manager.commit.error");
	}
	return result;
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public ModelAndView editProfile(@RequestParam final int userAccountId) {
	ModelAndView result;
	Manager manager;

	manager = this.managerService.findByUserAccountId(userAccountId);
	Assert.notNull(manager);

	result = this.createEditModelAndView(manager);
	result.addObject("cancelUrl", "'welcome/index.do'");
	return result;
    }

    protected ModelAndView createEditModelAndView(final Manager manager) {
	ModelAndView result;
	result = this.createEditModelAndView(manager, null);
	return result;
    }

    protected ModelAndView createEditModelAndView(final Manager manager, final String messageCode) {
	ModelAndView result;
	Collection<Gym> gyms;
	Collection<Admin> admins;

	gyms = this.gymService.findAll();
	admins = this.adminService.findAll();

	result = new ModelAndView("manager/edit");
	result.addObject("manager", manager);
	result.addObject("gyms", gyms);
	result.addObject("admins", admins);
	result.addObject("message", messageCode);
	return result;
    }
}
