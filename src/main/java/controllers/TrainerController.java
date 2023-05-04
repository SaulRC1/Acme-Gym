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
import domain.Curriculum;
import domain.Gym;
import domain.Trainer;
import services.ActivityService;
import services.TrainerService;

@Controller
@RequestMapping("/trainer")
public class TrainerController extends AbstractController {

	@Autowired
	private TrainerService trainerService;
	private ActivityService activityService;

	// Constructors -----------------------------------------------------------

	public TrainerController() {
		super();
	}

	// action1-List ---------------------------------------------------------------
	/**
	 * Metodo para listar trainers
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Trainer> trainers;

		trainers = this.trainerService.findAll();

		result = new ModelAndView("trainer/list");
		result.addObject("trainers", trainers);
		result.addObject("requestURI", "trainer/list.do");

		return result;
	}

	// Action2-Create
	// ---------------------------------------------------------------
	/**
	 * Metodo para la creacion de un trainer vacio
	 *
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Trainer trainer;
		trainer = this.trainerService.create();
		result = this.createEditModelAndView(trainer);
		return result;
	}
	
	// Action3-Edit ---------------------------------------------------------------
		/**
		 * Metodo para la edicion de un trainer existente
		 * @param trainerID
		 * @return
		 */
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam final int trainerID) {
			ModelAndView result;
			Trainer trainer;
			trainer = this.trainerService.findOne(trainerID);
			Assert.notNull(trainer);
			result = this.createEditModelAndView(trainer);
			return result;
		}

		// Action4-Save ---------------------------------------------------------------
		/**
		 * Metodo para la insercion de un trainer en la base de datos
		 *
		 * @param admin
		 * @param binding
		 * @return
		 */
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final Trainer trainer, final BindingResult binding) {
			ModelAndView result;
			if (binding.hasErrors())
				result = this.createEditModelAndView(trainer);
			else
				try {
					this.trainerService.save(trainer);
					result = new ModelAndView("redirect:list.do");
				} catch (final Throwable oops) {
					result = this.createEditModelAndView(trainer, "trainer.commit.error");
				}
			return result;
		}

		// Action5-Delete
		// ---------------------------------------------------------------

		/**
		 * Metodo para la eliminacion de un trainer existente
		 *
		 * @param trainer
		 * @return
		 */
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(final Trainer trainer) {
			ModelAndView result;
			try {
				this.trainerService.delete(trainer);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(trainer, "trainer.commit.error");
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
		protected ModelAndView createEditModelAndView(final Trainer trainer) {
			ModelAndView result;
			result = this.createEditModelAndView(trainer, null);
			return result;
		}

		// Core Method---------------------------------------------------------------
		/**
		 * Core de la forma en como actualizar modelos y vistas
		 *
		 * @param admin
		 * @param messageCode
		 * @return
		 */
		protected ModelAndView createEditModelAndView(final Trainer trainer, final String messageCode) {
			ModelAndView result;
			Curriculum curriculum;
			Gym gym;
			Collection<Activity> activities;
			
			activities=activityService.findAll();
			if(trainer.getFirstName()==null) {
				curriculum=null;
				gym=null;
				
			}else {
				curriculum=trainer.getCurriculum();
				activities=trainer.getActivities();
				gym=trainer.getGym();
			}
			
			result=new ModelAndView("trainer/edit");
			result.addObject("trainer",trainer);
			result.addObject("curriculum",curriculum);
			result.addObject("gym",gym);
			result.addObject("activities",activities);
			return result;
		}
}
