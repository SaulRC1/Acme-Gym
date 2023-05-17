package controllers.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import controllers.AbstractController;
import domain.Client;
import domain.CreditCard;
import domain.Gym;
import domain.Manager;
import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;
import services.ClientService;
import services.ManagerService;
import services.gym.GymService;

@Controller
public class SignUpController extends AbstractController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private GymService gymService;

    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/security/signup", method = RequestMethod.GET)
    public ModelAndView loadSignUpPage() {

	UserAccount userAccount = new UserAccount();

	ModelAndView model = new ModelAndView("security/signup");

	model.addObject("userAccount", userAccount);

	return model;
    }

    @RequestMapping(value = "/security/signup", method = RequestMethod.POST)
    public ModelAndView signUpActor(@RequestParam(value = "username") String username,
	    @RequestParam(value = "password") String password,
	    @RequestParam(value = "authorities", required = false) String authorities,
	    RedirectAttributes redirectAttributes, HttpSession httpSession) {

	Locale locale = LocaleContextHolder.getLocale();

	System.out.println("Current locale: " + locale.getLanguage());

	UserAccount userAccount = new UserAccount();

	System.out.println("username: " + username);
	System.out.println("password: " + password);
	System.out.println("authorities: " + authorities);

	if (authorities == null || authorities.trim().isEmpty()) {
	    ModelAndView model = new ModelAndView("security/signup");

	    if (locale.getLanguage().equals("es"))
		model.addObject("authorityError", "Debe seleccionar si es un cliente o un gerente.");
	    else if (locale.getLanguage().equals("en"))
		model.addObject("authorityError",
			"You must select if you want to register as a client or as a manager.");

	    model.addObject("userAccount", userAccount);

	    return model;
	}

	if (username == null || username.trim().isEmpty()) {

	    ModelAndView model = new ModelAndView("security/signup");

	    if (locale.getLanguage().equals("es"))
		model.addObject("usernameError", "El nombre de usuario no puede ser vacío");
	    else if (locale.getLanguage().equals("en"))
		model.addObject("usernameError", "Username must not be empty");

	    model.addObject("userAccount", userAccount);

	    return model;
	}

	if (username.length() < 5 || username.length() > 32) {

	    ModelAndView model = new ModelAndView("security/signup");

	    if (locale.getLanguage().equals("es"))
		model.addObject("usernameError", "El nombre de usuario tiene que tener entre 5 y 32 caracteres");
	    else if (locale.getLanguage().equals("en"))
		model.addObject("usernameError", "Username must have between 5 and 32 characters");

	    model.addObject("userAccount", userAccount);

	    return model;
	}

	if (password == null || password.trim().isEmpty()) {

	    ModelAndView model = new ModelAndView("security/signup");

	    if (locale.getLanguage().equals("es"))
		model.addObject("paswordError", "Debe de introducir una contraseña");
	    else if (locale.getLanguage().equals("en"))
		model.addObject("passwordError", "You must enter a password");

	    model.addObject("userAccount", userAccount);

	    return model;
	}

	if (password.length() < 5 || password.length() > 32) {

	    ModelAndView model = new ModelAndView("security/signup");

	    if (locale.getLanguage().equals("es"))
		model.addObject("paswordError", "La contraseña debe tener entre 5 y 32 caracteres.");
	    else if (locale.getLanguage().equals("en"))
		model.addObject("passwordError", "Password must have between 5 and 32 characters.");

	    model.addObject("userAccount", userAccount);

	    return model;
	}

	if (this.userAccountRepository.findByUsername(username) != null) {

	    ModelAndView model = new ModelAndView("security/signup");

	    if (locale.getLanguage().equals("es"))
		model.addObject("usernameError", "El usuario ya existe, por favor, elija otro nombre");
	    else if (locale.getLanguage().equals("en"))
		model.addObject("usernameError", "Username already exists, please select another");

	    model.addObject("userAccount", userAccount);

	    return model;
	}

	userAccount.setUsername(username);

	Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

	password = passwordEncoder.encodePassword(password, null);

	userAccount.setPassword(password);

	Authority authority = new Authority();

	if (authorities.equals(Authority.CLIENT))
	    authority.setAuthority(Authority.CLIENT);

	if (authorities.equals(Authority.MANAGER))
	    authority.setAuthority(Authority.MANAGER);

	Collection<Authority> userAuthorities = new ArrayList<Authority>();

	userAuthorities.add(authority);

	userAccount.setAuthorities(userAuthorities);

	System.out.println("User Account: " + userAccount);

	if (authorities.equals(Authority.CLIENT)) {

	    ModelAndView model = new ModelAndView();

	    redirectAttributes.addFlashAttribute("userAccount", userAccount);
	    httpSession.setAttribute("userAccount", userAccount);

	    model.setViewName("redirect:signup_client.do");

	    return model;

	} else if (authorities.equals(Authority.MANAGER)) {
	    ModelAndView model = new ModelAndView("redirect:signup_manager.do");

	    httpSession.setAttribute("userAccount", userAccount);

	    return model;
	}

	return new ModelAndView("security/signup");
    }

    @RequestMapping(value = "/security/signup_client", method = RequestMethod.GET)
    public ModelAndView loadClientSignUpPage(@ModelAttribute(value = "userAccount") UserAccount userAccount) {

	System.out.println("User Account en loadClientSignUpPage: " + userAccount);

	ModelAndView model = new ModelAndView("security/signup_client");

	model.addObject("client", new Client());

	// if (userAccount.getUsername() != null)
	model.addObject("userAccount", userAccount);

	return model;
    }

    @RequestMapping(value = "/security/signup_client", method = RequestMethod.POST)
    public ModelAndView signUpClient(@RequestParam("firstName") String firstName,
	    @RequestParam("lastName") String lastName, @RequestParam("address") String address,
	    @RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber,
	    @RequestParam("postalCode") String postalCode, @RequestParam("city") String city,
	    @RequestParam("country") String country, @RequestParam("creditCard.holder") String creditCardHolder,
	    @RequestParam("creditCard.brand") String creditCardBrand,
	    @RequestParam("creditCard.number") String creditCardNumber,
	    @RequestParam("creditCard.expirationMonth") String creditCardExpirationMonth,
	    @RequestParam("creditCard.expirationYear") String creditCardExpirationYear,
	    @RequestParam("creditCard.CVV") String creditCardCVV, HttpSession httpSession) {

	UserAccount userAccount = (UserAccount) httpSession.getAttribute("userAccount");

	System.out.println("User Account en signUpPage: " + userAccount);

	Client client = new Client();

	client.setUserAccount(userAccount);

	client.setFirstName(firstName);
	client.setLastName(lastName);
	client.setAddress(address);
	client.setEmail(email);
	client.setPhoneNumber(phoneNumber);
	client.setPostalCode(postalCode);
	client.setCity(city);
	client.setCountry(country);

	CreditCard creditCard = new CreditCard();

	creditCard.setHolder(creditCardHolder);
	creditCard.setBrand(creditCardBrand);
	creditCard.setNumber(creditCardNumber);
	creditCard.setExpirationMonth(Integer.parseInt(creditCardExpirationMonth));
	creditCard.setExpirationYear(Integer.parseInt(creditCardExpirationYear));
	creditCard.setCVV(Integer.parseInt(creditCardCVV));

	client.setCreditCard(creditCard);

	userAccount = this.userAccountRepository.save(userAccount);

	client.setUserAccount(userAccount);

	this.clientService.save(client);

	ModelAndView model = new ModelAndView("redirect:login.do");

	return model;
    }

    @RequestMapping(value = "/security/signup_manager", method = RequestMethod.GET)
    public ModelAndView loadManagerSignUpPage(HttpSession httpSession) {

	UserAccount userAccount = (UserAccount) httpSession.getAttribute("userAccount");

	System.out.println("User Account en loadManagerSignUpPage: " + userAccount);

	ModelAndView model = new ModelAndView("security/signup_manager");

	model.addObject("manager", new Manager());

	Collection<Gym> gyms = this.gymService.findActivesGyms();

	model.addObject("gyms", gyms);

	return model;
    }

    @RequestMapping(value = "/security/signup_manager", method = RequestMethod.POST)
    public ModelAndView signUpManager(@RequestParam("firstName") String firstName,
	    @RequestParam("lastName") String lastName, @RequestParam("address") String address,
	    @RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber,
	    @RequestParam("postalCode") String postalCode, @RequestParam("city") String city,
	    @RequestParam("country") String country, HttpSession httpSession,
	    @RequestParam(value = "gyms", required = false) List<Integer> gymIds) {

	if (gymIds == null || gymIds.isEmpty()) {

	    Locale locale = LocaleContextHolder.getLocale();

	    ModelAndView model = new ModelAndView("security/signup_manager");

	    model.addObject("manager", new Manager());

	    Collection<Gym> gyms = this.gymService.findActivesGyms();

	    model.addObject("gyms", gyms);

	    if (locale.getLanguage().equals("es"))
		model.addObject("gymError", "Tiene que seleccionar al menos un gimnasio");
	    else if (locale.getLanguage().equals("en"))
		model.addObject("gymError", "You have to choose at least one gym");

	    return model;
	}

	UserAccount userAccount = (UserAccount) httpSession.getAttribute("userAccount");

	System.out.println("User Account en signUpManager: " + userAccount);

	Manager manager = new Manager();

	manager.setUserAccount(userAccount);

	manager.setFirstName(firstName);
	manager.setLastName(lastName);
	manager.setAddress(address);
	manager.setEmail(email);
	manager.setPhoneNumber(phoneNumber);
	manager.setPostalCode(postalCode);
	manager.setCity(city);
	manager.setCountry(country);

	userAccount = this.userAccountRepository.save(userAccount);

	manager.setUserAccount(userAccount);

	for (int gymId : gymIds) {

	    Gym gym = this.gymService.findOne(gymId);

	    manager.addGym(gym);
	}

	this.managerService.save(manager);

	ModelAndView model = new ModelAndView("redirect:login.do");

	return model;
    }
}
