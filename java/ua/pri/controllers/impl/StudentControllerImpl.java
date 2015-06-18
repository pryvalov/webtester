package ua.pri.controllers.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pri.ent.Test;
import ua.pri.services.StudentService;
@Controller
@RequestMapping("/student")
public class StudentControllerImpl {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(StudentControllerImpl.class);
	@Autowired
	StudentService studentService;

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getStudentHome(Model model){
		List<Test> tests = studentService.listTests();
		model.addAttribute("tests", tests);
		return "student/home";
	}
	
	@RequestMapping(value="/load",  method=RequestMethod.GET)
	public String loadTest(@RequestParam("testId") String testId, @RequestParam("print") String print, Model model, HttpSession session){
		
		if(print.equals("1")){
			
			Test test = studentService.loadTest(Integer.valueOf(testId));
			session.setAttribute("test", test);
		return "student/print";
		}
		if(print.equals("0")){
			
			session.setAttribute("error", "not supported yet");
			return "error";
		
		}
		else{
			
			session.setAttribute("error", "wrong print value format");
			return "error";
		}
	}

}
