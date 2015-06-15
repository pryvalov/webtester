package ua.pri.controllers.impl;

import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.pri.ent.Account;
import ua.pri.exceptions.InvalidUserInputException;
import ua.pri.exceptions.RegistrationException;
import ua.pri.services.EmailVerificationService;
import ua.pri.services.RegistrationService;

@Controller("signUpController")
public class SignUpControllerImpl {
	@Autowired
	protected RegistrationService registrationService;
	

	@Autowired
	protected EmailVerificationService emailVerificationService;
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String getSignUpForm(){
//		ModelAndView mav = new ModelAndView("signup");
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ModelAndView sendSignUpForm(@ModelAttribute("account") Account account){
		try {
			registrationService.signUpForm(account);
		} catch (RegistrationException e) {
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("error", e.getMessage());
			return mav;
		}
		ModelAndView mav = new ModelAndView("info");
		mav.addObject("info", "Young lord, in order to proceed registration check your email. May the force be with you.");
		return mav;
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
