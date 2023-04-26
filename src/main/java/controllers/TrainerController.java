package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Trainer;
import services.TrainerService;

@Controller
@RequestMapping("/trainer")
public class TrainerController extends AbstractController {

    /**
     * Listado de trainers
     */
    @Autowired
    private TrainerService trainerService;

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
}
