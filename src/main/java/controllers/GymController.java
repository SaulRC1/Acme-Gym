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
import domain.Annotation;
import domain.Client;
import domain.Gym;
import domain.Inscription;
import domain.Manager;
import domain.Trainer;
import domain.Training;
import services.ActivityService;
import services.AdminService;
import services.AnnotationService;
import services.InscriptionService;
import services.ManagerService;
import services.TrainerService;
import services.TrainingService;
import services.gym.GymService;

@Controller
@RequestMapping("/gym")
public class GymController extends AbstractController {

	@Autowired
	private GymService gymService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private TrainerService trainerService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private InscriptionService inscriptionService;
	@Autowired
	private AnnotationService annotattionService;
	@Autowired
	private TrainingService trainingService;

	// Constructors -----------------------------------------------------------

	public GymController() {
		super();
	}

	// action1-List ---------------------------------------------------------------
	/**
	 * Listado de gyms
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Gym> gyms;

		gyms = this.gymService.findAll();

		result = new ModelAndView("gym/list");
		result.addObject("gyms", gyms);
		result.addObject("requestURI", "gym/list.do");

		return result;
	}

	// Action2-Create
	// ---------------------------------------------------------------
	/**
	 * Metodo para la creacion de un gym vacio
	 *
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Gym gyms;
		gyms = this.gymService.create();
		result = this.createEditModelAndView(gyms);
		return result;
	}

	// Action3-Edit ---------------------------------------------------------------
	/**
	 * Metodo para la edicion de un gym existente
	 *
	 * @param gymID
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int gymID) {
		ModelAndView result;
		Gym gym;
		gym = this.gymService.findOne(gymID);
		Assert.notNull(gym);
		result = this.createEditModelAndView(gym);
		return result;
	}

	// Action4-Save ---------------------------------------------------------------
	/**
	 * Metodo para la insercion de un gym en la base de datos
	 *
	 * @param gym
	 * @param binding
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Gym gym, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(gym);
		else
			try {
				this.gymService.save(gym);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(gym, "gym.commit.error");
			}
		return result;
	}

	// Action5-Delete
	// ---------------------------------------------------------------

	/**
	 * Metodo para la eliminacion de un gym existente
	 *
	 * @param gym
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Gym gym) {
		ModelAndView result;
		try {
			this.gymService.delete(gym);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(gym, "gym.commit.error");
		}
		return result;
	}

	// Ancillary
	// Method---------------------------------------------------------------
	/**
	 * Metodo para la actualizacion de modelos y vistas
	 *
	 * @param gym
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Gym gym) {
		ModelAndView result;
		result = this.createEditModelAndView(gym, null);
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
	protected ModelAndView createEditModelAndView(final Gym gym, final String messageCode) {
		ModelAndView result;
		Collection<Manager> managers;
		Collection<Trainer> trainers;
		Collection<Activity> activities;
		Collection<Inscription> inscriptions;
		Collection<Annotation> annotations;
		Collection<Training> trainings;

		managers = managerService.findAll();
		trainers = trainerService.findAll();
		activities = activityService.findAll();
		inscriptions = inscriptionService.findAll();
		annotations = annotattionService.findAll();
		trainings = trainingService.findAll();

		result = new ModelAndView("gym/edit");
		result.addObject("gym", gym);

		result.addObject("managers", managers);
		result.addObject("trainers", trainers);
		result.addObject("activities", activities);
		result.addObject("inscriptions", inscriptions);
		result.addObject("annotations", annotations);
		result.addObject("trainings", trainings);

		result.addObject("message", messageCode);
		return result;
	}

}