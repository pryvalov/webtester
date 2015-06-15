package ua.pri.controllers.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/student")
public class StudentControllerImpl {

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getStudentHome(){
		return "student/home";
	}

}
