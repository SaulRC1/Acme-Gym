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
import domain.Actor;
import domain.Admin;
import domain.Annotation;
import domain.Client;
import domain.Gym;
import domain.Manager;
import domain.Trainer;
import domain.Training;
import services.ActivityService;
import services.AdminService;
import services.AnnotationService;
import services.ManagerService;
import services.TrainingService;
import services.gym.GymService;

@Controller
@RequestMapping("/annotation")
public class AnnotationController extends AbstractController {

	@Autowired
	private AnnotationService annotationService;
	private ActivityService activityService;
	private GymService gymService;
	private TrainingService trainingService;

	// Constructors -----------------------------------------------------------

	public AnnotationController() {
		super();
	}

	// action1-List ---------------------------------------------------------------
	/**
	 * Listado de annotations
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Annotation> annotations;

		annotations = this.annotationService.findAll();

		result = new ModelAndView("annotation/list");
		result.addObject("annotations", annotations);
		result.addObject("requestURI", "annotation/list.do");

		return result;
	}

	// Action2-Create
	// ---------------------------------------------------------------
	/**
	 * Metodo para la creacion de un annotation vacio
	 *
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Annotation annotation;
		annotation = this.annotationService.create();
		result = this.createEditModelAndView(annotation);
		return result;
	}

	// Action3-Edit ---------------------------------------------------------------
	/**
	 * Metodo para la edicion de una annotation existente 
	 *
	 * @param annotationID
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int annotationID) {
		ModelAndView result;
		Annotation annotation;
		annotation = this.annotationService.findOne(annotationID);
		Assert.notNull(annotation);
		result = this.createEditModelAndView(annotation);
		return result;
	}

	// Action4-Save ---------------------------------------------------------------
	/**
	 * Metodo para la insercion de una annotation en la base de datos
	 *
	 * @param annotation
	 * @param binding
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Annotation annotation, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(annotation);
		else
			try {
				this.annotationService.save(annotation);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(annotation, "annotation.commit.error");
			}
		return result;
	}

	// Action5-Delete
	// ---------------------------------------------------------------

	/**
	 * Metodo para la eliminacion de una annotation existente
	 *
	 * @param annotation
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Annotation annotation) {
		ModelAndView result;
		try {
			this.annotationService.delete(annotation);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(annotation, "annotation.commit.error");
		}
		return result;
	}

	// Ancillary
	// Method---------------------------------------------------------------
	/**
	 * Metodo para la actualizacion de modelos y vistas
	 *
	 * @param annotation
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Annotation annotation) {
		ModelAndView result;
		result = this.createEditModelAndView(annotation, null);
		return result;
	}

	// Core Method---------------------------------------------------------------
	/**
	 * Core de la forma en como actualizar modelos y vistas
	 *
	 * @param annotation
	 * @param messageCode
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Annotation annotation, final String messageCode) {
		ModelAndView result;
		
		Actor actor=null;
		Training training=null;
		Gym gym=null;
		Activity activity=null;
		
		Collection<Training> trainings;
		Collection<Gym> gyms;
		Collection<Activity> activities;

		if (annotation.getText() != null) {
			actor=annotation.getActor();
			training=annotation.getTraining();
			gym=annotation.getGym();
		}else {
			trainings=trainingService.findAll();
			gyms=gymService.findAll();
			activities=activityService.findAll();	
		}
		
		result = new ModelAndView("annotation/edit");
		result.addObject("annotation", annotation);
		result.addObject("actor", actor);
		result.addObject("trainings", trainings);
		result.addObject("gyms", gyms);
		result.addObject("activities", activities);

		result.addObject("message", messageCode);
		return result;
	}

}
