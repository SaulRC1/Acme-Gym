
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
import domain.Annotation;
import domain.Gym;
import domain.Inscription;
import domain.Manager;
import domain.Trainer;
import domain.Training;
import services.ActivityService;
import services.AnnotationService;
import services.InscriptionService;
import services.ManagerService;
import services.TrainerService;
import services.TrainingService;
import services.gym.GymService;

@Controller
@RequestMapping("/gym")
public class GymController extends AbstractController {

    @Autowired
    private GymService gymService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private AnnotationService annotattionService;
    @Autowired
    private TrainingService trainingService;

    public GymController() {
	super();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
	ModelAndView result;
	Collection<Gym> gyms;

	gyms = this.gymService.findAll();

	result = new ModelAndView("gym/list");
	result.addObject("gyms", gyms);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/listActives", method = RequestMethod.GET)
    public ModelAndView listActives() {
	ModelAndView result;
	Collection<Gym> gyms;

	gyms = this.gymService.findAvailableGyms();

	result = new ModelAndView("gym/list");
	result.addObject("gyms", gyms);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
	ModelAndView result;
	Gym gyms;
	gyms = this.gymService.create();
	result = this.createEditModelAndView(gyms);
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int gymId) {
	ModelAndView result;
	Gym gym;
	gym = this.gymService.findOne(gymId);
	Assert.notNull(gym);
	result = this.createEditModelAndView(gym);
	result.addObject("cancelUrl", "'gym/list.do'");
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid final Gym gym, final BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors())
	    result = this.createEditModelAndView(gym);
	else
	    try {
		this.gymService.save(gym);
		result = new ModelAndView("redirect:list.do");
	    } catch (final Throwable oops) {
		result = this.createEditModelAndView(gym, "gym.commit.error");
	    }
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(final Gym gym) {
	ModelAndView result;
	try {
	    this.gymService.delete(gym);
	    result = new ModelAndView("redirect:list.do");
	} catch (final Throwable oops) {
	    result = this.createEditModelAndView(gym, "gym.commit.error");
	}
	return result;
    }

    @RequestMapping(value = "/cancelGym", method = RequestMethod.GET)
    public ModelAndView cancelGym(@RequestParam final int gymId) {
	ModelAndView result;
	Collection<Gym> gyms;
	Gym gym;
	Collection<Activity> activities;

	gym = this.gymService.findOne(gymId);

	gym.setActive(false);

	this.gymService.save(gym);

	activities = gym.getActivities();

	for (final Activity activity : activities) {
	    activity.setActive(false);
	    this.activityService.save(activity);
	}

	gyms = this.gymService.findAvailableGyms();

	result = new ModelAndView("gym/list");
	result.addObject("gyms", gyms);
	result.addObject("gym", gym);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/activateGym", method = RequestMethod.POST, params = "activate")
    public ModelAndView activatelGym(@RequestParam final int gymId) {
	ModelAndView result;
	Collection<Gym> gyms;
	Gym gym;
	Collection<Activity> activities;

	gym = this.gymService.findOne(gymId);

	gym.setActive(true);

	this.gymService.save(gym);

	activities = gym.getActivities();

	for (final Activity activity : activities) {
	    activity.setActive(true);
	    this.activityService.save(activity);
	}

	gyms = this.gymService.findAvailableGyms();

	result = new ModelAndView("gym/list");
	result.addObject("gyms", gyms);
	result.addObject("gym", gym);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView details(@RequestParam final int gymId) {
	ModelAndView result;
	Gym gym;
	Collection<Activity> activities;

	gym = this.gymService.findOne(gymId);
	activities = this.activityService.findAll();

	activities.removeAll(gym.getActivities());

	result = new ModelAndView("gym/details");
	result.addObject("activities", activities);
	result.addObject("gymActivities", gym.getActivities());
	result.addObject("gymId", gym.getId());

	return result;
    }

    @RequestMapping(value = "/linkActivity", method = RequestMethod.GET)
    public ModelAndView linkActivity(@RequestParam final int gymId, @RequestParam final int activityId) {
	ModelAndView result;
	Gym gym;
	Activity activity;
	Collection<Activity> activities;

	activities = this.activityService.findAll();

	gym = this.gymService.findOne(gymId);
	activity = this.activityService.findOne(activityId);

	if (!gym.getActivities().contains(activity)) {
	    gym.addActivity(activity);
	    this.gymService.save(gym);
	}

	activities.removeAll(gym.getActivities());

	result = new ModelAndView("gym/details");
	result.addObject("activities", activities);
	result.addObject("gymActivities", gym.getActivities());
	result.addObject("gymId", gym.getId());

	return result;
    }

    @RequestMapping(value = "/unlinkActivity", method = RequestMethod.GET)
    public ModelAndView unlinkActivity(@RequestParam final int gymId, @RequestParam final int activityId) {
	ModelAndView result;
	Gym gym;
	Activity activity;
	Collection<Activity> activities;

	activities = this.activityService.findAll();

	gym = this.gymService.findOne(gymId);
	activity = this.activityService.findOne(activityId);

	if (gym.getActivities().contains(activity)) {
	    gym.removeActivity(activity);
	    this.gymService.save(gym);
	}

	activities.removeAll(gym.getActivities());

	result = new ModelAndView("gym/details");
	result.addObject("activities", activities);
	result.addObject("gymActivities", gym.getActivities());
	result.addObject("gymId", gym.getId());

	return result;
    }

    protected ModelAndView createEditModelAndView(final Gym gym) {
	ModelAndView result;
	result = this.createEditModelAndView(gym, null);
	return result;
    }

    protected ModelAndView createEditModelAndView(final Gym gym, final String messageCode) {
	ModelAndView result;
	Collection<Manager> managers;
	Collection<Trainer> trainers;
	Collection<Activity> activities;
	Collection<Inscription> inscriptions;
	Collection<Annotation> annotations;
	Collection<Training> trainings;

	managers = this.managerService.findAll();
	trainers = this.trainerService.findAll();
	activities = this.activityService.findAll();
	inscriptions = this.inscriptionService.findAll();
	annotations = this.annotattionService.findAll();
	trainings = this.trainingService.findAll();

	result = new ModelAndView("gym/edit");
	result.addObject("gym", gym);

	result.addObject("managers", managers);
	result.addObject("trainers", trainers);
	result.addObject("activities", activities);
	result.addObject("inscriptions", inscriptions);
	result.addObject("annotations", annotations);
	result.addObject("trainings", trainings);

	result.addObject("message", messageCode);
	return result;
    }

}
