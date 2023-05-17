
package controllers;

import java.time.DayOfWeek;
import java.time.LocalTime;
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
import domain.Client;
import domain.Gym;
import domain.Trainer;
import services.ActivityService;
import services.ClientService;
import services.TrainerService;
import services.gym.GymService;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private GymService gymService;
    @Autowired
    private ClientService clientService;

    public ActivityController() {
	super();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
	ModelAndView result;
	Collection<Activity> activities;

	activities = this.activityService.findAll();

	result = new ModelAndView("activity/list");
	result.addObject("activities", activities);
	result.addObject("requestURI", "activity/list.do");

	return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
	ModelAndView result;
	Activity activity;
	activity = this.activityService.create();
	result = this.createEditModelAndView(activity);
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int activityId) {
	ModelAndView result;
	Activity activity;
	activity = this.activityService.findOne(activityId);
	Assert.notNull(activity);
	result = this.createEditModelAndView(activity);
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid final Activity activity, final BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors())
	    result = this.createEditModelAndView(activity);
	else
	    try {
		this.activityService.save(activity);
		result = new ModelAndView("redirect:list.do");
	    } catch (final Throwable oops) {
		result = this.createEditModelAndView(activity, "activity.commit.error");
	    }
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(final Activity activity) {
	ModelAndView result;
	try {
	    this.activityService.delete(activity);
	    result = new ModelAndView("redirect:list.do");
	} catch (final Throwable oops) {
	    result = this.createEditModelAndView(activity, "activity.commit.error");
	}
	return result;
    }

    @RequestMapping(value = "/listByGymId", method = RequestMethod.GET)
    public ModelAndView listbyGym(@RequestParam final int gymId) {
	ModelAndView result;
	Collection<Activity> activities;
	Gym gym;

	gym = this.gymService.findOne(gymId);
	activities = gym.getActivities();

	result = new ModelAndView("activity/list");
	result.addObject("activities", activities);
	result.addObject("gymId", gymId);
	result.addObject("requestURI", "activity/list.do");

	return result;
    }

    @RequestMapping(value = "/listByKeyword", method = RequestMethod.GET)
    public ModelAndView listByKeyword(@RequestParam final String keyword) {
	ModelAndView result;
	Collection<Activity> activities;

	if (keyword == "") {
	    activities = this.activityService.findAll();
	} else {
	    activities = this.activityService.findByKeyword(keyword);
	}

	result = new ModelAndView("activity/list");
	result.addObject("activities", activities);
	result.addObject("requestURI", "activity/list.do");

	return result;
    }

    @RequestMapping(value = "/listByGymAndKeyword", method = RequestMethod.GET)
    public ModelAndView listByGymAndKeyword(@RequestParam final int gymId, final String keyword) {
	ModelAndView result;
	Collection<Activity> activities;

	if (keyword == "") {
	    Gym gym = this.gymService.findOne(gymId);
	    activities = gym.getActivities();
	} else {
	    activities = this.activityService.findByGymIdAndKeyword(gymId, keyword);
	}

	result = new ModelAndView("activity/list");
	result.addObject("activities", activities);
	result.addObject("gymId", gymId);
	result.addObject("requestURI", "activity/list.do");

	return result;
    }

    // Revisar el tema de pedir una hora y un dia
    @RequestMapping(value = "/listByHourOrDay", method = RequestMethod.GET)
    public ModelAndView listbyHourOrDay(@RequestParam final Collection<DayOfWeek> days, final LocalTime time) {
	ModelAndView result;
	Collection<Activity> activities;

	activities = this.activityService.findByDayRange(days, time);

	result = new ModelAndView("activity/list");
	result.addObject("activities", activities);
	result.addObject("requestURI", "activity/list.do");

	return result;
    }

    @RequestMapping(value = "/listAvailableActivities", method = RequestMethod.GET)
    public ModelAndView listAvailableActivities() {
	ModelAndView result;
	Collection<Activity> activities;

	activities = this.activityService.findAvailableActivities();

	result = new ModelAndView("activity/list");
	result.addObject("activities", activities);
	result.addObject("requestURI", "activity/list.do");

	return result;
    }

    @RequestMapping(value = "/cancelActivity", method = RequestMethod.POST, params = "cancel")
    public ModelAndView cancelActivity(@RequestParam final int activityId) {
	ModelAndView result;
	Collection<Activity> activities;
	Activity activity;

	activity = this.activityService.findOne(activityId);
	activity.setActive(false);
	this.activityService.save(activity);
	activities = this.activityService.findAvailableActivities();
	result = new ModelAndView("activity/list");
	result.addObject("activities", activities);
	result.addObject("requestURI", "activity/list.do");

	return result;
    }

    @RequestMapping(value = "/activateActivity", method = RequestMethod.POST, params = "activate")
    public ModelAndView activateActivity(@RequestParam final int activityId) {
	ModelAndView result;
	Collection<Activity> activities;
	Activity activity;

	activity = this.activityService.findOne(activityId);
	activity.setActive(true);
	this.activityService.save(activity);

	activities = this.activityService.findAvailableActivities();
	result = new ModelAndView("activity/list");
	result.addObject("activities", activities);
	result.addObject("requestURI", "activity/list.do");

	return result;
    }

    protected ModelAndView createEditModelAndView(final Activity activity) {
	ModelAndView result;
	result = this.createEditModelAndView(activity, null);
	return result;
    }

    protected ModelAndView createEditModelAndView(final Activity activity, final String messageCode) {
	ModelAndView result;
	Collection<Trainer> trainers;
	Collection<Gym> gyms;
	Collection<Client> clients;

	trainers = this.trainerService.findAll();
	gyms = this.gymService.findAll();
	clients = this.clientService.findAll();

	result = new ModelAndView("activity/edit");
	result.addObject("activity", activity);
	result.addObject("trainers", trainers);
	result.addObject("clients", clients);
	result.addObject("gyms", gyms);
	result.addObject("message", messageCode);
	return result;
    }

}
