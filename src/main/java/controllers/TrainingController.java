
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

import domain.Gym;
import domain.Training;
import services.TrainingService;
import services.gym.GymService;

@Controller
@RequestMapping("/training")
public class TrainingController extends AbstractController {

	@Autowired
	private TrainingService	trainingService;
	@Autowired
	private GymService		gymService;


	public TrainingController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Training> trainings;

		trainings = this.trainingService.findAll();

		result = new ModelAndView("training/list");
		result.addObject("trainings", trainings);
		result.addObject("requestURI", "training/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Training training;
		training = this.trainingService.create();
		result = this.createEditModelAndView(training);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int trainingId) {
		ModelAndView result;
		Training training;
		training = this.trainingService.findOne(trainingId);
		Assert.notNull(training);
		result = this.createEditModelAndView(training);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Training training, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(training);
		else
			try {
				this.trainingService.save(training);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(training, "training.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Training training) {
		ModelAndView result;
		try {
			this.trainingService.delete(training);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(training, "training.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/listByGymId", method = RequestMethod.GET)
	public ModelAndView listbyGymId(@RequestParam final int gymId) {
		ModelAndView result;
		Collection<Training> trainings;
		Gym gym;
		gym = this.gymService.findOne(gymId);

		trainings = gym.getTrainings();

		result = new ModelAndView("training/list");
		result.addObject("trainings", trainings);
		result.addObject("gym", gym);
		result.addObject("requestURI", "training/list.do");

		return result;
	}

	@RequestMapping(value = "/listBykeyword", method = RequestMethod.GET)
	public ModelAndView listbykeyword(@RequestParam final String keyword) {
		ModelAndView result;
		Collection<Training> trainings;

		trainings = this.trainingService.findTrainingsByKeyword(keyword);

		result = new ModelAndView("training/list");
		result.addObject("trainings", trainings);
		result.addObject("requestURI", "training/list.do");

		return result;
	}

	protected ModelAndView createEditModelAndView(final Training training) {
		ModelAndView result;
		result = this.createEditModelAndView(training, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Training training, final String messageCode) {
		ModelAndView result;
		Collection<Gym> gyms;

		gyms = this.gymService.findAll();

		result = new ModelAndView("training/edit");
		result.addObject("gyms", gyms);
		result.addObject("message", messageCode);
		return result;
	}

}
