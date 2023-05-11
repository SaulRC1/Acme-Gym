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

import domain.Annotation;
import domain.Gym;
import domain.Step;
import domain.Training;
import services.TrainingService;
import services.gym.GymService;

@Controller
@RequestMapping("/training")
public class TrainingController extends AbstractController {

	@Autowired
	private TrainingService trainingService;
	@Autowired
	private GymService gymService;

	// Constructors -----------------------------------------------------------

	public TrainingController() {
		super();
	}

	// action1-List ---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Training> trainings;

		trainings = this.trainingService.findAll();

		result = new ModelAndView("training/list");
		result.addObject("trainings", trainings);
		result.addObject("requestURI", "training/list.do");

		return result;
	}

	// Action2-Create
	// ---------------------------------------------------------------
	/**
	 * Metodo para la creacion de un training vacia
	 *
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Training training;
		training = this.trainingService.create();
		result = this.createEditModelAndView(training);
		return result;
	}

	// Action3-Edit ---------------------------------------------------------------
	/**
	 * Metodo para la edicion de un training existente
	 *
	 * @param trainingID
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int trainingID) {
		ModelAndView result;
		Training training;
		training = this.trainingService.findOne(trainingID);
		Assert.notNull(training);
		result = this.createEditModelAndView(training);
		return result;
	}

	// Action4-Save ---------------------------------------------------------------
	/**
	 * Metodo para la insercion de un training en la base de datos
	 *
	 * @param training
	 * @param binding
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Training training, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(training);
		else
			try {
				this.trainingService.save(training);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(training, "training.commit.error");
			}
		return result;
	}

	// Action5-Delete
	// ---------------------------------------------------------------

	/**
	 * Metodo para la eliminacion de un training existente
	 *
	 * @param training
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Training training) {
		ModelAndView result;
		try {
			this.trainingService.delete(training);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(training, "training.commit.error");
		}
		return result;
	}

	// Ancillary
	// Method---------------------------------------------------------------
	/**
	 * Metodo para la actualizacion de modelos y vistas
	 *
	 * @param training
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Training training) {
		ModelAndView result;
		result = this.createEditModelAndView(training, null);
		return result;
	}

	// Core Method---------------------------------------------------------------
	/**
	 * Core de la forma en como actualizar modelos y vistas
	 *
	 * @param training
	 * @param messageCode
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Training training, final String messageCode) {
		ModelAndView result;
		Collection<Gym> gyms;

		gyms = gymService.findAll();

		result = new ModelAndView("training/edit");
		result.addObject("gyms", gyms);
		result.addObject("message", messageCode);
		return result;
	}

}
