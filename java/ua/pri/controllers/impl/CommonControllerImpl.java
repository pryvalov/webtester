package ua.pri.controllers.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("commonController")
public class CommonControllerImpl {
	
	@RequestMapping("/")
	public String redirectLoginPage(){
		return "login";
	}
}
