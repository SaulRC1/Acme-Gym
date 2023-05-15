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
import domain.Client;
import domain.Gym;
import domain.Manager;
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

	@RequestMapping(value = "/listbyGym", method = RequestMethod.GET)
	public ModelAndView listbyGymId(@RequestParam Gym gym) {
		ModelAndView result;
		Collection<Activity> activities;

		activities = gym.getActivities();

		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("gym", gym);
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

		trainers = trainerService.findAll();
		gyms = gymService.findAll();
		clients = clientService.findAll();

		result = new ModelAndView("activity/edit");
		result.addObject("activity", activity);
		result.addObject("trainers", trainers);
		result.addObject("clients", clients);
		result.addObject("gyms", gyms);
		result.addObject("message", messageCode);
		return result;
	}

}
