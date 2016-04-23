package shcherbakov.sergey.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shcherbakov.sergey.exception.DataException;
import shcherbakov.sergey.exception.ValidatorException;
import shcherbakov.sergey.model.User;
import shcherbakov.sergey.services.user.UserService;

@Controller
public class UserControleer {
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService uS){
		this.userService = uS;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "redirect:" + "/user/contacts";
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
    }
	
	@RequestMapping(value = "/login/error", method = RequestMethod.GET)
	public String errorLogin(Model model){
		model.addAttribute("error", "*Incorrect email or password");
		
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(Model model) {
		return "register";
    }
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		
		User newUser = new User(login, password, fullName);
		
		try{
			this.userService.addUser(newUser);
		} catch(ValidatorException | DataException e ){
			model.addAttribute("error", e.getMessage());
			model.addAttribute("user", newUser);
			
			return "register";
		} 
		
		return "redirect:" + "/";
    }
}
