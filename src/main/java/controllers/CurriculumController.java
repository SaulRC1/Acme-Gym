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

    public CurriculumController() {
	super();
    }

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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
	ModelAndView result;
	Curriculum curriculum;
	curriculum = this.curriculumService.create();
	result = this.createEditModelAndView(curriculum);
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int curriculumId) {
	ModelAndView result;
	Curriculum curriculum;
	curriculum = this.curriculumService.findOne(curriculumId);
	Assert.notNull(curriculum);
	result = this.createEditModelAndView(curriculum);
	return result;
    }

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

    protected ModelAndView createEditModelAndView(final Curriculum curriculum) {
	ModelAndView result;
	result = this.createEditModelAndView(curriculum, null);
	return result;
    }

    protected ModelAndView createEditModelAndView(final Curriculum curriculum, final String messageCode) {
	ModelAndView result;

	result = new ModelAndView("curriculum/edit");
	result.addObject("curriculum", curriculum);
	result.addObject("message", messageCode);
	return result;
    }

}
