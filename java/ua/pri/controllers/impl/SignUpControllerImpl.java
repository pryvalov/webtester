package ua.pri.controllers.impl;

import javax.servlet.http.HttpSession;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pri.exceptions.RegistrationException;
import ua.pri.forms.SignUpForm;
import ua.pri.services.EmailVerificationService;
import ua.pri.services.RegistrationService;

@Controller("signUpController")
public class SignUpControllerImpl {
	@Autowired
	protected RegistrationService registrationService;
	

	@Autowired
	protected EmailVerificationService emailVerificationService;
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String getSignUpForm(Model model){
		model.addAttribute("signUpForm", new SignUpForm());
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String sendSignUpForm(@ModelAttribute("SignUpForm") SignUpForm form, Model model){
		try {
			registrationService.signUpForm(form);
		} catch (RegistrationException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
		model.addAttribute("info", "Young lord, in order to proceed registration check your email. May the force be with you.");
		return "info";
	}
	
	@RequestMapping(value="/recovery", method=RequestMethod.GET)
	public String recoverPassword(){
		return "recovery";
	}
	@RequestMapping(value="/recovery", method=RequestMethod.POST)
	public String sendRecoverMail(@RequestParam("email") String email, HttpSession session){
		
		try {
			registrationService.passwordRecovery(email);
			session.setAttribute("info", "Email with your details was sent");
			return "info";
		} catch (Exception e) {
			session.setAttribute("error", e.getMessage());
			return "error";
		} 
		
		
	}
	
	
}
