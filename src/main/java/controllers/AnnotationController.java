
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
import domain.Annotation;
import domain.Gym;
import domain.Training;
import services.ActivityService;
import services.AnnotationService;
import services.TrainingService;
import services.gym.GymService;

@Controller
@RequestMapping("/annotation")
public class AnnotationController extends AbstractController {

    @Autowired
    private AnnotationService annotationService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private GymService gymService;
    @Autowired
    private TrainingService trainingService;

    // Constructors -----------------------------------------------------------

    public AnnotationController() {
	super();
    }

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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
	ModelAndView result;
	Annotation annotation;
	annotation = this.annotationService.create();
	result = this.createEditModelAndView(annotation);
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int annotationId) {
	ModelAndView result;
	Annotation annotation;
	annotation = this.annotationService.findOne(annotationId);
	Assert.notNull(annotation);
	result = this.createEditModelAndView(annotation);
	return result;
    }

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

    protected ModelAndView createEditModelAndView(final Annotation annotation) {
	ModelAndView result;
	result = this.createEditModelAndView(annotation, null);
	return result;
    }

    protected ModelAndView createEditModelAndView(final Annotation annotation, final String messageCode) {
	ModelAndView result;

	Collection<Training> trainings;
	Collection<Gym> gyms;
	Collection<Activity> activities;

	trainings = this.trainingService.findAll();
	gyms = this.gymService.findAll();
	activities = this.activityService.findAll();

	result = new ModelAndView("annotation/edit");
	result.addObject("annotation", annotation);
	result.addObject("trainings", trainings);
	result.addObject("gyms", gyms);
	result.addObject("activities", activities);

	result.addObject("message", messageCode);
	return result;
    }

}
