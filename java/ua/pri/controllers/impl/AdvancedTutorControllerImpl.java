package ua.pri.controllers.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/advanced_tutor")
public class AdvancedTutorControllerImpl {
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getAdvancedTutorHome(){
		return "advanced_tutor/home";
	}

}
