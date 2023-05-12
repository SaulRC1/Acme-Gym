
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

import domain.Client;
import domain.Gym;
import domain.Inscription;
import services.ClientService;
import services.InscriptionService;
import services.gym.GymService;

@Controller
@RequestMapping("/inscription")
public class InscriptionController extends AbstractController {

	@Autowired
	private InscriptionService inscriptionService;
	@Autowired
	private GymService gymService;
	@Autowired
	private ClientService clientService;

	// Constructors -----------------------------------------------------------

	public InscriptionController() {
		super();
	}

	// action1-List ---------------------------------------------------------------
	/**
	 * Listado de inscriptions
	 *
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Inscription> inscriptions;

		inscriptions = this.inscriptionService.findAll();

		result = new ModelAndView("inscription/list");
		result.addObject("inscriptions", inscriptions);
		result.addObject("requestURI", "inscription/list.do");

		return result;
	}

	// Action2-Create
	// ---------------------------------------------------------------
	/**
	 * Metodo para la creacion de un inscription vacio
	 *
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Inscription inscription;
		inscription = this.inscriptionService.create();
		result = this.createEditModelAndView(inscription);
		return result;
	}

	// Action3-Edit ---------------------------------------------------------------
	/**
	 * Metodo para la edicion de un inscription existente
	 *
	 * @param inscriptionId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int inscriptionId) {
		ModelAndView result;
		Inscription inscription;
		inscription = this.inscriptionService.findOne(inscriptionId);
		Assert.notNull(inscription);
		result = this.createEditModelAndView(inscription);
		return result;
	}

	// Action4-Save ---------------------------------------------------------------
	/**
	 * Metodo para la insercion de una inscription en la base de datos
	 *
	 * @param inscription
	 * @param binding
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Inscription inscription, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(inscription);
		else
			try {
				this.inscriptionService.save(inscription);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(inscription, "inscription.commit.error");
			}
		return result;
	}

	// Action5-Delete
	// ---------------------------------------------------------------

	/**
	 * Metodo para la eliminacion de una inscription existente
	 *
	 * @param inscription
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Inscription inscription) {
		ModelAndView result;
		try {
			this.inscriptionService.delete(inscription);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(inscription, "inscription.commit.error");
		}
		return result;
	}

	// Ancillary
	// Method---------------------------------------------------------------
	/**
	 * Metodo para la actualizacion de modelos y vistas
	 *
	 * @param inscription
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Inscription inscription) {
		ModelAndView result;
		result = this.createEditModelAndView(inscription, null);
		return result;
	}

	// Core Method---------------------------------------------------------------
	/**
	 * Core de la forma en como actualizar modelos y vistas
	 *
	 * @param inscription
	 * @param messageCode
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Inscription inscription, final String messageCode) {
		ModelAndView result;

		Collection<Gym> gyms;
		Collection<Client> clients;
		
		gyms = this.gymService.findAll();
		clients=this.clientService.findAll();

		result = new ModelAndView("inscription/edit");
		result.addObject("inscription", inscription);
		result.addObject("gyms", gyms);
		result.addObject("clients",clients);
		result.addObject("message", messageCode);
		return result;
	}

}
