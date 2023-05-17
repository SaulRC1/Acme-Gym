package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Step;
import domain.Training;
import services.TrainingService;

@Controller
@RequestMapping("/step")
public class StepController extends AbstractController {

    @Autowired
    private TrainingService trainingService;

    public StepController() {
	super();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam final int trainingId) {
	final ModelAndView result;
	final Training training;
	Collection<Step> steps;

	training = this.trainingService.findOne(trainingId);
	steps = training.getSteps();

	result = new ModelAndView("step/list");
	result.addObject("steps", steps);
	result.addObject("requestURI", "manager/list.do");

	return result;
    }
}