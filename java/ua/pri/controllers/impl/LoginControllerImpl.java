package ua.pri.controllers.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.pri.ent.Account;
import ua.pri.exceptions.InvalidUserInputException;
import ua.pri.forms.LoginForm;
import ua.pri.services.LoginService;
import ua.pri.utils.RolesConstants;

@Controller("loginController")
public class LoginControllerImpl {
	
	private static Map<Integer, String> rolesHome = new HashMap<>();
	static{
		rolesHome.put(RolesConstants.ADMIN, "admin/home");
		rolesHome.put(RolesConstants.STUDENT, "student/home");
		rolesHome.put(RolesConstants.TUTOR, "tutor/home");
		rolesHome.put(RolesConstants.ADVANCED_TUTOR, "advanced_tutor/home");
	}
	
	
	@Autowired
	LoginService loginService;
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginForm(){
		return "login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String proceedLogin(@ModelAttribute("loginForm") LoginForm loginForm, Model model, HttpSession session) throws InvalidUserInputException{
		Account acc = null;
		try{
		acc = loginService.login(loginForm.getEmail(), loginForm.getPassword(), loginForm.getRole());
		session.setAttribute("account", acc);
		session.setAttribute("role", rolesHome.get(loginForm.getRole()));
		return "redirect:"+rolesHome.get(loginForm.getRole());
		}catch(Exception e){
			session.setAttribute("error", e.getMessage());
			return "error";
		}
		
		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.setAttribute("account", null);
		return "redirect:login";
	}
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String homeLoginButton(){
		return "login";
	}

}
