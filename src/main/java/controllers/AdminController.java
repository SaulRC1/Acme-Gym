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

import domain.Admin;
import domain.Client;
import domain.Manager;
import services.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    @Autowired
    private AdminService adminService;

    // Constructors -----------------------------------------------------------

    public AdminController() {
	super();
    }

    // action1-List ---------------------------------------------------------------

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView action1() {
	ModelAndView result;
	Collection<Admin> admins;

	admins = this.adminService.findAll();// Falta incluir el metodo findAll en el servicio

	result = new ModelAndView("admin/list");
	result.addObject("admins", admins);
	result.addObject("requestURI", "admin/list.do");

	return result;
    }

    // Action2-Create
    // ---------------------------------------------------------------
    /**
     * Metodo para la creacion de un admin vacio
     *
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
	ModelAndView result;
	Admin admin;
	admin = this.adminService.create();// Falta incluir el metodo create en el servicio (lo crea vacio)
	result = this.createEditModelAndView(admin);
	return result;
    }

    // Action3-Edit ---------------------------------------------------------------
    /**
     * Metodo para la edicion de un admin existente pasando admin del cliente a
     * crear
     *
     * @param adminID
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam final int adminID) {
	ModelAndView result;
	Admin admin;
	admin = this.adminService.findOne(adminID);// Falta incluir el metodo findOne en el servicio (lo encuentra
						   // por ID)
	Assert.notNull(admin);
	result = this.createEditModelAndView(admin);
	return result;
    }

    // Action4-Save ---------------------------------------------------------------
    /**
     * Metodo para la insercion de un admin en la base de datos
     *
     * @param admin
     * @param binding
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid final Admin admin, final BindingResult binding) {
	ModelAndView result;
	if (binding.hasErrors())
	    result = this.createEditModelAndView(admin);
	else
	    try {
		this.adminService.save(admin);// Falta incluir el metodo save en el servicio (dado un cliente lo
					      // inyecta en la base de datos)
		result = new ModelAndView("redirect:list.do");
	    } catch (final Throwable oops) {
		result = this.createEditModelAndView(admin, "admin.commit.error");
	    }
	return result;
    }

    // Action5-Delete
    // ---------------------------------------------------------------

    /**
     * Metodo para la eliminacion de un admin existente
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
    public ModelAndView delete(final Client client) {
	ModelAndView result;
	try {
	    this.adminService.delete(client);// Falta incluir el metodo delete en el servicio (borra el cliente dado)
	    result = new ModelAndView("redirect:list.do");
	} catch (final Throwable oops) {
	    result = this.createEditModelAndView(admin, "admin.commit.error");
	}
	return result;
    }

    // Ancillary
    // Method---------------------------------------------------------------
    /**
     * Metodo para la actualizacion de modelos y vistas
     *
     * @param admin
     * @return
     */
    protected ModelAndView createEditModelAndView(final Admin admin) {
	ModelAndView result;
	result = this.createEditModelAndView(admin, null);
	return result;
    }

    // Core Method---------------------------------------------------------------
    /**
     * Core de la forma en como actualizar modelos y vistas
     *
     * @param admin
     * @param messageCode
     * @return
     */
    protected ModelAndView createEditModelAndView(final Admin admin, final String messageCode) {
	ModelAndView result;
	Collection<Manager> managers;

	if (admin.getFirstName() == null)
	    managers = null;
	else
	    managers = admin.getManagers();
	result = new ModelAndView("admin/edit");
	result.addObject("admin", admin);
	result.addObject("managers", managers);
	result.addObject("message", messageCode);
	return result;
    }

}
