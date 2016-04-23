package shcherbakov.sergey.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shcherbakov.sergey.exception.ValidatorException;
import shcherbakov.sergey.model.Contact;
import shcherbakov.sergey.services.contact.ContactService;

@Controller
public class ContactController {
	@Autowired
	private ContactService contactService;
	
	public void setContactService(ContactService contactService){
		this.contactService = contactService;
	}
	
	@RequestMapping(value = "/user/contacts", method = RequestMethod.GET)
    public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
		
        model.addAttribute("contact", new Contact());
        model.addAttribute("contacts", this.contactService.listContact(login));
		return "contacts";
    }
	
	@RequestMapping(value = "/user/addContact", method = RequestMethod.GET)
    public String addContactPage(Model model) {
		return "addContact";
    }
	
	@RequestMapping(value = "/user/addContact", method = RequestMethod.POST)
    public String addContact(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();

	    String surname = request.getParameter("surname");
		String name = request.getParameter("name");
		String patronymic = request.getParameter("patronymic");
		String mobilePhone = request.getParameter("mobilePhone");
		String homePhone = request.getParameter("homePhone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		
		Contact contact = new Contact(surname, name, patronymic, mobilePhone, homePhone, address, email);
		
		try{
		this.contactService.addContact(contact, login);
		} catch(ValidatorException e){
			model.addAttribute("error", e.getMessage());
			model.addAttribute("contact", contact);
			
			return "addContact";
		}
		return "addContact";
    }
	
	@RequestMapping(value = "/user/editContact/{idContact}", method = RequestMethod.GET)
    public String editContactPage(@PathVariable String idContact, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
		
	    Contact contact = this.contactService.getContact(idContact, login);
	    
	    //if(contact == null)
	    //	return "redirect:" + "/";
	    
		model.addAttribute("contact", contact);
		
		return "editContact";
    }
	
	@RequestMapping(value = "/user/editContact/{idContact}", method = RequestMethod.POST)
    public String editContact(@PathVariable String idContact, HttpServletRequest request, Model model) {		
		String surname = request.getParameter("surname");
		String name = request.getParameter("name");
		String patronymic = request.getParameter("patronymic");
		String mobilePhone = request.getParameter("mobilePhone");
		String homePhone = request.getParameter("homePhone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		
		Contact contact = new Contact(idContact, surname, name, patronymic, mobilePhone, homePhone, address, email);
		
		try{
		this.contactService.editContact(contact);
		 } catch(ValidatorException e){
				model.addAttribute("error", e.getMessage());
				model.addAttribute("contact", contact);
				
				return "editContact";
			}
		
		return "redirect:" + "/";
    }
	
	@RequestMapping(value = "/user/deleteContact/{idContact}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable String idContact, Model model) {		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
		
		this.contactService.deleteContact(idContact, login);
		
		return "redirect:" + "/";
    }
	
	@RequestMapping(value = "users/contacts/search", method = RequestMethod.GET)
    public String listContactSearch(HttpServletRequest request, Model model) {		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
	    
	    String surname = request.getParameter("surname");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		model.addAttribute("contact", new Contact());
        model.addAttribute("contacts", this.contactService.listContactSearch(surname, name, phone, login));
        
		return "contacts";
    }
}
