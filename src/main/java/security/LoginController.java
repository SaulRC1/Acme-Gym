/*
 * LoginController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Admin;

@Controller
@RequestMapping("/security")
public class LoginController extends AbstractController {

    // Supporting services ----------------------------------------------------

    @Autowired
    LoginService service;

    // Constructors -----------------------------------------------------------

    public LoginController() {
	super();
    }

    // Login ------------------------------------------------------------------

    @RequestMapping("/login")
    public ModelAndView login(@Valid final Credentials credentials, final BindingResult bindingResult,
	    @RequestParam(required = false) final boolean showError) {
	Assert.notNull(credentials);
	Assert.notNull(bindingResult);

	ModelAndView result;

	result = new ModelAndView("security/login");
	result.addObject("credentials", credentials);
	result.addObject("showError", showError);

	return result;
    }

    // LoginFailure -----------------------------------------------------------

    @RequestMapping("/loginFailure")
    public ModelAndView failure() {
	ModelAndView result;

	result = new ModelAndView("redirect:login.do?showError=true");

	return result;
    }

    // LoginFailure -----------------------------------------------------------

    @RequestMapping("/signup")
    public ModelAndView signup() {
	ModelAndView result;

	UserAccount userAccount = new UserAccount();

	result = new ModelAndView("security/signup");
	result.addObject("userAccount", userAccount);

	return result;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, params = "signup")
    public ModelAndView signup(@Valid final UserAccount userAccount, final BindingResult binding) {
	ModelAndView result = new ModelAndView();
	
	// Redireccionar a /client/edit o /manager/edit pasandole el UserAccount
	
	return result;

    }

}
