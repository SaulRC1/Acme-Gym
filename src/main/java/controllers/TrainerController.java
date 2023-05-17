
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
import domain.Gym;
import domain.Trainer;
import services.ActivityService;
import services.TrainerService;
import services.gym.GymService;

@Controller
@RequestMapping("/trainer")
public class TrainerController extends AbstractController {

    @Autowired
    private TrainerService trainerService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private GymService gymService;

    public TrainerController() {
	super();
    }

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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
	ModelAndView result;
	Trainer trainer;
	trainer = this.trainerService.create();
	result = this.createEditModelAndView(trainer);
	result.addObject("cancelUrl", "'welcome/index.do'");
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int trainerId) {
	ModelAndView result;
	Trainer trainer;
	trainer = this.trainerService.findOne(trainerId);
	Assert.notNull(trainer);
	result = this.createEditModelAndView(trainer);
	result.addObject("cancelUrl", "'trainer/list.do'");
	return result;
    }

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

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public ModelAndView editProfile(@RequestParam final int userAccountId) {
	ModelAndView result;
	Trainer trainer;

	trainer = this.trainerService.findByUserAccountId(userAccountId);
	Assert.notNull(trainer);

	result = this.createEditModelAndView(trainer);
	result.addObject("cancelUrl", "'welcome/index.do'");
	return result;
    }

    @RequestMapping(value = "/listByActivityId", method = RequestMethod.GET)
    public ModelAndView listbyActivityId(@RequestParam final int activityId) {
	ModelAndView result;
	Collection<Trainer> trainers;

	final Activity activity = this.activityService.findOne(activityId);
	trainers = activity.getTrainers();

	result = new ModelAndView("trainer/list");
	result.addObject("trainers", trainers);
	result.addObject("activity", activity);
	result.addObject("requestURI", "trainer/list.do");

	return result;
    }

    @RequestMapping(value = "/listByNameOrSurname", method = RequestMethod.GET)
    public ModelAndView listbyNameOrSurname(@RequestParam final String keyword) {
	ModelAndView result;
	Collection<Trainer> trainers;

	trainers = this.trainerService.findByNameOrSurname(keyword);

	result = new ModelAndView("trainer/list");
	result.addObject("trainers", trainers);
	result.addObject("requestURI", "trainer/list.do");

	return result;
    }

    protected ModelAndView createEditModelAndView(final Trainer trainer) {
	ModelAndView result;
	result = this.createEditModelAndView(trainer, null);
	return result;
    }

    protected ModelAndView createEditModelAndView(final Trainer trainer, final String messageCode) {
	ModelAndView result;
	Collection<Gym> gyms;
	Collection<Activity> activities;

	gyms = this.gymService.findAll();
	activities = this.activityService.findAll();

	result = new ModelAndView("trainer/edit");
	result.addObject("trainer", trainer);
	result.addObject("gyms", gyms);
	result.addObject("activities", activities);
	return result;
    }
}
