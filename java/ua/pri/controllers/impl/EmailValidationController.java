package ua.pri.controllers.impl;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.pri.services.EmailVerificationService;

@Controller("emailValidationController")
public class EmailValidationController {
	
	//private static final Logger LOGGER = LogManager.getLogger(EmailValidationController.class);
	
	@Autowired
	protected EmailVerificationService emailVerificationService;
	
	@RequestMapping(value="/verifyemail", method=RequestMethod.GET)
	public ModelAndView validateEmail(@RequestParam("email") String email, @RequestParam("code") String code){
			try {
				Long longCode = Long.valueOf(code);
				if(emailVerificationService.verifyCode(email, longCode)){
				ModelAndView mav = new ModelAndView("info");
				mav.addObject("info", "Verification completed, you can login <a href=\"login\">here</a>>");
				return mav;
				}
				ModelAndView mav = new ModelAndView("error");
				mav.addObject("error", "email verification fail: expired or bad link!");
				return mav;
				
			} catch (Exception e) {
				ModelAndView mav = new ModelAndView("error");
				mav.addObject("error", e.getMessage());
				return mav;
			}
	}
}
