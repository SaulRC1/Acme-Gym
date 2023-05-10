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

import domain.Activity;
import domain.Admin;
import domain.Client;
import domain.Gym;
import domain.Inscription;
import domain.Manager;
import services.ActivityService;
import services.ClientService;
import services.gym.GymService;

@Controller
@RequestMapping("/client")
public class ClientController extends AbstractController {

	@Autowired
	private ClientService clientService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private GymService gymService;

	// Constructors -----------------------------------------------------------

	public ClientController() {
		super();
	}

	// Action1-List ---------------------------------------------------------------
	/**
	 * Metodo para listar clientes
	 *
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Client> clients;

		clients = this.clientService.findAll();

		result = new ModelAndView("client/list");
		result.addObject("clients", clients);
		result.addObject("requestURI", "client/list.do");

		return result;
	}

	// Action2-Create
	// ---------------------------------------------------------------
	/**
	 * Metodo para la creacion de un cliente vacio
	 *
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Client client;
		client = this.clientService.create();
		result = this.createEditModelAndView(client);
		return result;
	}

	// Action3-Edit ---------------------------------------------------------------
	/**
	 * Metodo para la edicion de un cliente existente pasando id del cliente a crear
	 *
	 * @param clientID
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int clientID) {
		ModelAndView result;
		Client client;
		client = this.clientService.findOne(clientID);
		Assert.notNull(client);
		result = this.createEditModelAndView(client);
		return result;
	}

	// Action4-Save ---------------------------------------------------------------
	/**
	 * Metodo para la insercion de un cliente en la base de datos
	 *
	 * @param client
	 * @param binding
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Client client, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(client);
		else
			try {
				this.clientService.save(client);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(client, "client.commit.error");
			}
		return result;
	}

	// Action5-Delete
	// ---------------------------------------------------------------

	/**
	 * Metodo para la eliminacion de un cliente existente
	 *
	 * @param client
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Client client) {
		ModelAndView result;
		try {
			this.clientService.delete(client);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(client, "client.commit.error");
		}
		return result;
	}

	// Ancillary
	// Method---------------------------------------------------------------
	/**
	 * Metodo para la actualizacion de modelos y vistas
	 * 
	 * @param client
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Client client) {
		ModelAndView result;
		result = this.createEditModelAndView(client, null);
		return result;
	}

	// Core Method---------------------------------------------------------------
	/**
	 * Core de la forma en como actualizar modelos y vistas
	 * 
	 * @param client
	 * @param messageCode
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Client client, final String messageCode) {
		ModelAndView result;
		Collection<Activity> activities;
		Collection<Gym> gyms;

		activities = activityService.findAll();
		gyms=gymService.findAll();
		
		result = new ModelAndView("client/edit");
		result.addObject("client", client);
		result.addObject("activities", activities);
		result.addObject("gyms", gyms);

		result.addObject("message", messageCode);
		return result;
	}

}
