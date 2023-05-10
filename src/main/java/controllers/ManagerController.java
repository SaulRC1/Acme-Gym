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

	// Constructors -----------------------------------------------------------

	public ManagerController() {
		super();
	}

	// action1-List ---------------------------------------------------------------
	/**
	 * Listado de managers
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Manager> managers;

		managers = this.managerService.findAll();

		result = new ModelAndView("manager/list");
		result.addObject("managers", managers);
		result.addObject("requestURI", "manager/list.do");

		return result;
	}

	// Action2-Create
	// ---------------------------------------------------------------
	/**
	 * Metodo para la creacion de un manager vacio
	 *
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Manager manager;
		manager = this.managerService.create();
		result = this.createEditModelAndView(manager);
		return result;
	}

	// Action3-Edit ---------------------------------------------------------------
	/**
	 * Metodo para la edicion de un manager existente
	 *
	 * @param managerID
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int managerID) {
		ModelAndView result;
		Manager manager;
		manager = this.managerService.findOne(managerID);
		Assert.notNull(manager);
		result = this.createEditModelAndView(manager);
		return result;
	}

	// Action4-Save ---------------------------------------------------------------
	/**
	 * Metodo para la insercion de un manager en la base de datos
	 *
	 * @param manager
	 * @param binding
	 * @return
	 */
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

	// Action5-Delete
	// ---------------------------------------------------------------

	/**
	 * Metodo para la eliminacion de un manager existente
	 *
	 * @param manager
	 * @return
	 */
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

	// Ancillary
	// Method---------------------------------------------------------------
	/**
	 * Metodo para la actualizacion de modelos y vistas
	 *
	 * @param admin
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Manager manager) {
		ModelAndView result;
		result = this.createEditModelAndView(manager, null);
		return result;
	}

	// Core Method---------------------------------------------------------------
	/**
	 * Core de la forma en como actualizar modelos y vistas
	 *
	 * @param gym
	 * @param messageCode
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Manager manager, final String messageCode) {
		ModelAndView result;
		Collection<Gym> gyms;
		Collection<Admin> admins;

		gyms = gymService.findAll();
		admins = adminService.findAll();
		
		result = new ModelAndView("manager/edit");
		result.addObject("manager", manager);
		result.addObject("gyms", gyms);
		result.addObject("admins", admins);
		result.addObject("message", messageCode);
		return result;
	}
}
