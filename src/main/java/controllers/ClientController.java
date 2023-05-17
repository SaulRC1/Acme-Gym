
package controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Activity;
import domain.Client;
import domain.CreditCard;
import domain.Gym;
import services.ActivityService;
import services.ClientService;
import services.gym.GymService;

@Controller
@RequestMapping("/client")
public class ClientController extends AbstractController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private GymService gymService;

    public ClientController() {
	super();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
	ModelAndView result;
	Collection<Client> clients;

	clients = this.clientService.findAll();

	result = new ModelAndView("client/list");
	result.addObject("clients", clients);
	result.addObject("requestURI", "client/list.do");

	return result;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
	ModelAndView result;
	Client client;
	client = this.clientService.create();
	result = this.createEditModelAndView(client);
	result.addObject("cancelUrl", "'welcome/index.do'");
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int clientId) {
	ModelAndView result;
	Client client;
	client = this.clientService.findOne(clientId);
	Assert.notNull(client);
	result = this.createEditModelAndView(client);
	result.addObject("cancelUrl", "'client/list.do'");
	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@RequestParam("id") final int clientId, @RequestParam("firstName") final String firstName,
	    @RequestParam("lastName") final String lastName, @RequestParam("address") final String address,
	    @RequestParam("email") final String email, @RequestParam("phoneNumber") final String phoneNumber,
	    @RequestParam("postalCode") final String postalCode, @RequestParam("city") final String city,
	    @RequestParam("country") final String country,
	    @RequestParam("creditCard.holder") final String creditCardHolder,
	    @RequestParam("creditCard.brand") final String creditCardBrand,
	    @RequestParam("creditCard.number") final String creditCardNumber,
	    @RequestParam("creditCard.expirationMonth") final String creditCardExpirationMonth,
	    @RequestParam("creditCard.expirationYear") final String creditCardExpirationYear,
	    @RequestParam("creditCard.CVV") final String creditCardCVV,
	    @RequestParam("activities") final List<String> activities) {
	ModelAndView result;
	Client client;
	Activity activity;

	client = this.clientService.findOne(clientId);
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

	client.getActivities().clear();

	for (String activitiesaux : activities) {
	    activity = this.activityService.findByName(activitiesaux);
	    client.addActivity(activity);
	}

	this.clientService.save(client);

	result = new ModelAndView("redirect:../welcome/index.do");

	return result;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(final Client client) {
	ModelAndView result;
	try {
	    this.clientService.delete(client);
	    result = new ModelAndView("redirect:list.do");
	} catch (final Throwable oops) {
	    result = this.createEditModelAndView(client, "client.commit.error");
	}
	return result;
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public ModelAndView editProfile(@RequestParam final int userAccountId) {
	ModelAndView result;
	Client client;

	client = this.clientService.findByUserAccountId(userAccountId);
	Assert.notNull(client);

	result = this.createEditModelAndView(client);
	result.addObject("cancelUrl", "'welcome/index.do'");
	return result;
    }

    protected ModelAndView createEditModelAndView(final Client client) {
	ModelAndView result;
	result = this.createEditModelAndView(client, null);
	return result;
    }

    protected ModelAndView createEditModelAndView(final Client client, final String messageCode) {
	ModelAndView result;
	Collection<Activity> activities;
	Collection<Gym> gyms;

	activities = this.activityService.findAll();
	gyms = this.gymService.findAll();

	result = new ModelAndView("client/edit");
	result.addObject("client", client);
	result.addObject("activities", activities);
	result.addObject("gyms", gyms);

	result.addObject("message", messageCode);
	return result;
    }

}
