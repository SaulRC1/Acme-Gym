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
import domain.Curriculum;
import domain.Gym;
import domain.Manager;
import domain.SocialIdentity;
import domain.Trainer;
import services.AdminService;
import services.CurriculumService;
import services.ManagerService;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController extends AbstractController {

	@Autowired
	private CurriculumService curriculumService;

	// Constructors -----------------------------------------------------------

	public CurriculumController() {
		super();
	}

	// action1-List ---------------------------------------------------------------
	/**
	 * Listado de curriculums
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Curriculum> curriculums;

		curriculums = this.curriculumService.findAll();

		result = new ModelAndView("curriculum/list");
		result.addObject("curriculums", curriculums);
		result.addObject("requestURI", "curriculum/list.do");

		return result;
	}

	// Action2-Create
	// ---------------------------------------------------------------
	/**
	 * Metodo para la creacion de un curriculum vacio
	 *
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Curriculum curriculum;
		curriculum = this.curriculumService.create();
		result = this.createEditModelAndView(curriculum);
		return result;
	}

	// Action3-Edit ---------------------------------------------------------------
	/**
	 * Metodo para la edicion de un curriculumn existente
	 *
	 * @param curriculumId
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int curriculumId) {
		ModelAndView result;
		Curriculum curriculum;
		curriculum = this.curriculumService.findOne(curriculumId);
		Assert.notNull(curriculum);
		result = this.createEditModelAndView(curriculum);
		return result;
	}

	// Action4-Save ---------------------------------------------------------------
	/**
	 * Metodo para la insercion de un curriculum en la base de datos
	 *
	 * @param curriculum
	 * @param binding
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Curriculum curriculum, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(curriculum);
		else
			try {
				this.curriculumService.save(curriculum);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(curriculum, "admin.commit.error");
			}
		return result;
	}

	// Action5-Delete
	// ---------------------------------------------------------------

	/**
	 * Metodo para la eliminacion de un curriculum existente
	 *
	 * @param curriculum
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Curriculum curriculum) {
		ModelAndView result;
		try {
			this.curriculumService.delete(curriculum);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(curriculum, "curriculum.commit.error");
		}
		return result;
	}

	// Ancillary
	// Method---------------------------------------------------------------
	/**
	 * Metodo para la actualizacion de modelos y vistas
	 *
	 * @param curriculum
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Curriculum curriculum) {
		ModelAndView result;
		result = this.createEditModelAndView(curriculum, null);
		return result;
	}

	// Core Method---------------------------------------------------------------
	/**
	 * Core de la forma en como actualizar modelos y vistas
	 *
	 * @param curriculum
	 * @param messageCode
	 * @return
	 */
	protected ModelAndView createEditModelAndView(final Curriculum curriculum, final String messageCode) {
		ModelAndView result;
		
		result = new ModelAndView("curriculum/edit");
		result.addObject("curriculum", curriculum);
		result.addObject("message", messageCode);
		return result;
	}

}
