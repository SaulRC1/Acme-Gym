
package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
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
import domain.Client;
import domain.Gym;
import domain.Inscription;
import domain.Manager;
import domain.Trainer;
import domain.Training;
import services.ActivityService;
import services.AnnotationService;
import services.ClientService;
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
    private ClientService clientService;
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

	gyms = this.gymService.findActivesGyms();

	result = new ModelAndView("gym/list");
	result.addObject("activedGyms", gyms);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/enrollmentList", method = RequestMethod.GET)
    public ModelAndView enrollmentList(@RequestParam final int userAccountId) {
	ModelAndView result;
	Collection<Gym> gyms;
	Client client;

	gyms = this.gymService.findActivesGyms();
	client = this.clientService.findByUserAccountId(userAccountId);

	Collection<Inscription> inscriptions;

	if (client.getInscriptions() != null)
	    inscriptions = client.getInscriptions();
	else
	    inscriptions = new ArrayList<Inscription>();

	Inscription lastInscription = null;

	boolean isEnrolled = false;

	for (Inscription inscription : inscriptions)
	    if (inscription.getSignOutDate() == null) {
		lastInscription = inscription;
		isEnrolled = true;
		break;
	    }

	Gym enrolledGym = lastInscription != null ? lastInscription.getGym() : null;

	result = new ModelAndView("gym/list");
	result.addObject("activedGyms", gyms);
	result.addObject("enrolledGym", enrolledGym);
	result.addObject("client", client);
	result.addObject("isEnrolled", isEnrolled);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/enroll", method = RequestMethod.GET)
    public ModelAndView enroll(@RequestParam final int userAccountId, final int gymId) {
	ModelAndView result;
	Collection<Gym> gyms;

	gyms = this.gymService.findActivesGyms();

	Client client = this.clientService.findByUserAccountId(userAccountId);

	Gym gym = this.gymService.findOne(gymId);

	Inscription inscription = this.inscriptionService.create();

	inscription.setClient(client);
	inscription.setGym(gym);
	inscription.setSignUpDate(LocalDate.now());

	inscription = this.inscriptionService.save(inscription);

	client.addInscription(inscription);

	gym.addInscription(inscription);

	this.clientService.save(client);
	this.gymService.save(gym);

	result = new ModelAndView("gym/list");
	result.addObject("activedGyms", gyms);
	result.addObject("enrolledGym", gym);
	result.addObject("isEnrolled", true);
	result.addObject("userAccountId", userAccountId);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/unsubscribe", method = RequestMethod.GET)
    public ModelAndView unsubscribe(@RequestParam final int userAccountId) {
	ModelAndView result;
	Collection<Gym> gyms;
	Client client;

	gyms = this.gymService.findActivesGyms();

	client = this.clientService.findByUserAccountId(userAccountId);

	Inscription lastInscription = this.inscriptionService.findLastInscription(client);

	lastInscription.setSignOutDate(LocalDate.now());

	this.inscriptionService.save(lastInscription);

	result = new ModelAndView("gym/list");
	result.addObject("activedGyms", gyms);
	result.addObject("enrolledGym", lastInscription.getGym());
	result.addObject("isEnrolled", false);
	result.addObject("userAccountId", userAccountId);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/listActivesUnactives", method = RequestMethod.GET)
    public ModelAndView listActivesUnactives(@RequestParam final int userAccountId) {
	ModelAndView result;
	Collection<Gym> activedGyms;
	Collection<Gym> unactivedGyms;

	Manager manager;

	manager = this.managerService.findByUserAccountId(userAccountId);

	unactivedGyms = this.gymService.findAll();
	activedGyms = this.gymService.findActivesGyms();
	unactivedGyms.removeAll(activedGyms);

	for (Gym gym : activedGyms)
	    if (!gym.getManagers().contains(manager))
		activedGyms.remove(gym);

	for (Gym gym : unactivedGyms)
	    if (!gym.getManagers().contains(manager))
		unactivedGyms.remove(gym);

	result = new ModelAndView("gym/list");
	result.addObject("activedGyms", activedGyms);
	result.addObject("unactivedGyms", unactivedGyms);
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
	Gym gym;
	Collection<Activity> activities;

	Collection<Gym> activedGyms;
	Collection<Gym> unactivedGyms;

	gym = this.gymService.findOne(gymId);
	gym.setActive(false);
	this.gymService.save(gym);

	activities = gym.getActivities();
	for (final Activity activity : activities) {
	    activity.setActive(false);
	    this.activityService.save(activity);
	}

	unactivedGyms = this.gymService.findAll();
	activedGyms = this.gymService.findActivesGyms();
	unactivedGyms.removeAll(activedGyms);

	result = new ModelAndView("gym/list");
	result.addObject("activedGyms", activedGyms);
	result.addObject("unactivedGyms", unactivedGyms);
	result.addObject("gym", gym);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/listByActivityId", method = RequestMethod.GET)
    public ModelAndView listbyActivityId(@RequestParam final int activityId) {
	ModelAndView result;
	final Collection<Gym> gyms = new ArrayList<>();

	final Activity activity = this.activityService.findOne(activityId);
	gyms.add(activity.getGym());

	result = new ModelAndView("gym/list");
	result.addObject("activedGyms", gyms);
	result.addObject("activity", activity);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/activateGym", method = RequestMethod.GET)
    public ModelAndView activatelGym(@RequestParam final int gymId) {
	ModelAndView result;
	Gym gym;
	Collection<Activity> activities;

	Collection<Gym> activedGyms;
	Collection<Gym> unactivedGyms;

	gym = this.gymService.findOne(gymId);
	gym.setActive(true);
	this.gymService.save(gym);

	activities = gym.getActivities();
	for (final Activity activity : activities) {
	    activity.setActive(true);
	    this.activityService.save(activity);
	}

	unactivedGyms = this.gymService.findAll();
	activedGyms = this.gymService.findActivesGyms();
	unactivedGyms.removeAll(activedGyms);

	result = new ModelAndView("gym/list");
	result.addObject("activedGyms", activedGyms);
	result.addObject("unactivedGyms", unactivedGyms);
	result.addObject("gym", gym);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/manageActivities", method = RequestMethod.GET)
    public ModelAndView manageActivities(@RequestParam final int gymId) {
	ModelAndView result;
	Gym gym;
	Collection<Activity> activities;
	final Collection<Activity> activedActivities = new ArrayList<>();
	final Collection<Activity> unactivedActivities = new ArrayList<>();

	gym = this.gymService.findOne(gymId);
	activities = gym.getActivities();

	for (final Activity activity : activities)
	    if (activity.isActive())
		activedActivities.add(activity);
	    else
		unactivedActivities.add(activity);

	result = new ModelAndView("gym/manageActivities");
	result.addObject("activedActivities", activedActivities);
	result.addObject("unactivedActivities", unactivedActivities);
	result.addObject("gymId", gym.getId());

	return result;
    }

    @RequestMapping(value = "/activateActivity", method = RequestMethod.GET)
    public ModelAndView activateActivity(@RequestParam final int gymId, @RequestParam final int activityId) {
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

	gyms = this.gymService.findActivesGyms();

	result = new ModelAndView("gym/list");
	result.addObject("gyms", gyms);
	result.addObject("gym", gym);
	result.addObject("requestURI", "gym/list.do");

	return result;
    }

    @RequestMapping(value = "/manageTrainers", method = RequestMethod.GET)
    public ModelAndView manageTrainers(@RequestParam final int gymId) {
	ModelAndView result;
	final Collection<Trainer> linkTrainers;
	Collection<Trainer> unlinkTrainers;
	Gym gym;

	gym = this.gymService.findOne(gymId);
	linkTrainers = gym.getTrainers();
	unlinkTrainers = this.trainerService.findTrainerByNoGymAssigned();

	result = new ModelAndView("gym/manageTrainers");
	result.addObject("linkTrainers", linkTrainers);
	result.addObject("unlickTrainers", unlinkTrainers);
	result.addObject("gym", gym);
	result.addObject("gymId", gym.getId());
	return result;
    }

    @RequestMapping(value = "/link", method = RequestMethod.GET)
    public ModelAndView link(@RequestParam final int gymId, @RequestParam final int trainerId) {
	ModelAndView result;
	final Collection<Trainer> linkTrainers;
	final Collection<Trainer> unlinkTrainers;
	Gym gym;
	Trainer trainer;

	gym = this.gymService.findOne(gymId);
	trainer = this.trainerService.findOne(trainerId);

	trainer.setGym(gym);
	this.trainerService.save(trainer);
	gym.addTrainer(trainer);

	this.gymService.save(gym);

	linkTrainers = gym.getTrainers();
	unlinkTrainers = this.trainerService.findTrainerByNoGymAssigned();

	result = new ModelAndView("gym/manageTrainers");
	result.addObject("linkTrainers", linkTrainers);
	result.addObject("unlinkTrainers", unlinkTrainers);
	result.addObject("gym", gym);
	result.addObject("gymId", gym.getId());
	return result;
    }

    @RequestMapping(value = "/unlink", method = RequestMethod.GET)
    public ModelAndView unlink(@RequestParam final int gymId, @RequestParam final int trainerId) {
	ModelAndView result;
	final Collection<Trainer> linkTrainers;
	final Collection<Trainer> unlinkTrainers;
	Gym gym;
	Trainer trainer;

	gym = this.gymService.findOne(gymId);
	trainer = this.trainerService.findOne(trainerId);

	gym.removeTrainer(trainer);
	trainer.setGym(null);

	this.trainerService.save(trainer);
	this.gymService.save(gym);

	linkTrainers = gym.getTrainers();
	unlinkTrainers = this.trainerService.findTrainerByNoGymAssigned();

	result = new ModelAndView("gym/manageTrainers");
	result.addObject("linkTrainers", linkTrainers);
	result.addObject("unlinkTrainers", unlinkTrainers);
	result.addObject("gym", gym);
	result.addObject("gymId", gym.getId());
	return result;
    }

    @RequestMapping(value = "/unactivateActivity", method = RequestMethod.GET)
    public ModelAndView unactiveActivity(@RequestParam final int gymId, @RequestParam final int activityId) {
	ModelAndView result;
	Gym gym;
	Activity activity;
	Collection<Activity> activities;
	final Collection<Activity> activedActivities = new ArrayList<>();
	final Collection<Activity> unactivedActivities = new ArrayList<>();

	gym = this.gymService.findOne(gymId);
	activity = this.activityService.findOne(activityId);

	activity.setActive(false);
	this.activityService.save(activity);

	activities = gym.getActivities();

	for (final Activity activityAux : activities)
	    if (activityAux.isActive())
		activedActivities.add(activityAux);
	    else
		unactivedActivities.add(activityAux);

	result = new ModelAndView("gym/manageActivities");
	result.addObject("activedActivities", activedActivities);
	result.addObject("unactivedActivities", unactivedActivities);
	result.addObject("gymId", gym.getId());

	return result;
    }

    @RequestMapping(value = "/manageTrainings", method = RequestMethod.GET)
    public ModelAndView manageTraining(@RequestParam final int gymId) {
	ModelAndView result;
	Gym gym;
	Collection<Training> trainings;

	gym = this.gymService.findOne(gymId);
	trainings = gym.getTrainings();

	result = new ModelAndView("gym/manageTrainings");
	result.addObject("trainings", trainings);
	result.addObject("gym", gym);
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
