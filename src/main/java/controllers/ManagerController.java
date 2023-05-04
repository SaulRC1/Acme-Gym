package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Manager;
import services.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController extends AbstractController {

	@Autowired
	private ManagerService managerService;

	// Constructors -----------------------------------------------------------

	public ManagerController() {
		super();
	}

	// action1-List ---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		Collection<Manager> managers;

		managers = this.managerService.findAll();

		result = new ModelAndView("manager/list");
		result.addObject("managers", managers);
		result.addObject("requestURI", "manager/list.do");

		return result;
	}
}
