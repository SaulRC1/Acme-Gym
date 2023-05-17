package controllers.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

@Controller
public class SignUpController extends AbstractController {

    @RequestMapping(value = "/security/signup", method = RequestMethod.GET)
    public ModelAndView loadSignUpPage() {

	return new ModelAndView("security/signup");
    }

}
