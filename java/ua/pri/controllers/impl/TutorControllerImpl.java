package ua.pri.controllers.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.tipis.AbstractReplicatedMap.MapEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pri.ent.Account;
import ua.pri.ent.Test;
import ua.pri.services.TutorService;
@Controller
@RequestMapping("/tutor")
public class TutorControllerImpl {
	
	private static final Logger LOGGER = LogManager.getLogger(TutorControllerImpl.class);
	
	@Autowired
	TutorService tutorService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getTutorHome(HttpSession session){
		Account a = (Account) session.getAttribute("account");
			if(a==null){
				session.setAttribute("error", "you are not logged in!");
				return "error";
			}
		List<Test> tests = tutorService.getUserTests(a);
		session.setAttribute("tests", tests);

		
		return "tutor/home";
	}
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String editTest(@RequestParam(required=false) String testId, HttpSession session){
		if (testId==null)
		return "tutor/editor";
		try{
		Test test = tutorService.loadTest(Integer.valueOf(testId));

		session.setAttribute("test", test);
		return "tutor/editor";
		}catch(Exception e){
			session.setAttribute("error", "Error loading test, contact administrator in order to slove this issue.");
			return "error";
		}
		
	}
	
	@RequestMapping(value="/submit", method=RequestMethod.POST)
	public String submitTest(@RequestParam() Map<String, String> params, HttpSession session){
		if(session.getAttribute("account")!=null){
		tutorService.updateTest(params, (Account)session.getAttribute("account"));
		
		return "tutor/home";
		}
		session.setAttribute("error", "403: You are not authorized");
		return "error";
	}


}
