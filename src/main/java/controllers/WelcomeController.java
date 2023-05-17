/*
 * WelcomeController.java
 *
 * Copyright (C) 2018 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Manager;
import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;
import services.ManagerService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    public WelcomeController() {
	super();
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(@RequestParam(required = false, defaultValue = "John Doe") final String name) {

	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	if (authentication != null && authentication.isAuthenticated()) {

	    String username = "John Doe";
	    UserAccount userAccount = null;

	    if (authentication.getPrincipal() instanceof String)
		username = (String) authentication.getPrincipal();

	    else if (authentication.getPrincipal() instanceof UserAccount) {

		userAccount = (UserAccount) authentication.getPrincipal();
		username = userAccount.getUsername();
	    }

	    Authority managerAuthority = new Authority();

	    managerAuthority.setAuthority(Authority.MANAGER);

	    if (userAccount != null && userAccount.getAuthorities().contains(managerAuthority)) {

		UserAccount userAccountFromDB = this.userAccountRepository.findByUsername(userAccount.getUsername());
		Manager manager = this.managerService.findByUserAccountId(userAccountFromDB.getId());

		if (manager.isBanned()) {

		    ModelAndView bannedManagerModel = new ModelAndView("welcome/bannedManager");

		    bannedManagerModel.addObject("name", userAccount.getUsername() + " ");

		    return bannedManagerModel;
		}

	    }

	    ModelAndView result;
	    SimpleDateFormat formatter;
	    String moment;

	    formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    moment = formatter.format(new Date());

	    result = new ModelAndView("welcome/index");
	    result.addObject("name", username);
	    result.addObject("moment", moment);

	    return result;
	}

	ModelAndView result;
	SimpleDateFormat formatter;
	String moment;

	formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	moment = formatter.format(new Date());

	result = new ModelAndView("welcome/index");
	result.addObject("name", name);
	result.addObject("moment", moment);

	return result;
    }
}
