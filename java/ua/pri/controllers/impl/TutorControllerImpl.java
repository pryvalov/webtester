package ua.pri.controllers.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

//import org.apache.catalina.tribes.tipis.AbstractReplicatedMap.MapEntry;
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
		session.setAttribute("test", null);

		
		return "tutor/home";
	}
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String editTest(@RequestParam(required=false) String action, @RequestParam(required=false) String testId, HttpSession session){
		if (testId==null)
		return "tutor/editor";
		try{
			switch (action){
			case "update": 
				Test test = tutorService.loadTest(Integer.valueOf(testId));
				session.setAttribute("test", test);
				return "tutor/editor";
			case "delete":	
				tutorService.delete(Integer.valueOf(testId));
				break;
			case "activate":
				tutorService.activate(Integer.valueOf(testId));
				break;
			case "deactivate":
				tutorService.deActivate(Integer.valueOf(testId));
				break;
			}

			return "redirect:home";	
		
		
		}catch(Exception e){
			session.setAttribute("error", "Error loading test, contact administrator in order to slove this issue.");
			LOGGER.info("Test loading crash"+e.getMessage());
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
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String createTest(HttpSession session){
		Test test = tutorService.createTest();
		session.setAttribute("test", test);
		return "tutor/create";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String createTest(@RequestParam Map<String, String> params, HttpSession session){
		
		for(Map.Entry<String, String> entry : params.entrySet())
			LOGGER.info(entry.getKey()+ " "+entry.getValue());
		Test test = (Test) session.getAttribute("test");
		test = tutorService.updateTest(params, test);
		session.setAttribute("test", test);
		return "tutor/editor";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String addQuestions(@RequestParam() Map<String, String> params, HttpSession session){
		if(session.getAttribute("account")!=null)
		{
		Test test = tutorService.createTest(params.get("name"), params.get("subj"), params.get("time"), (Account)session.getAttribute("account"));
		session.setAttribute("test", test);
		return "tutor/editor";
		}
		session.setAttribute("error", "You are not logged in, <a href=\"/wtapp/login\">log in</a> in order to create test.");
		return "error";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveTest(@RequestParam() Map<String, String> params, HttpSession session){
		for(Map.Entry<String, String> entry : params.entrySet())
			LOGGER.info(entry.getKey()+" "+ entry.getValue());
		
		if(session.getAttribute("account")!=null){

		Test test = (Test)session.getAttribute("test");

		LOGGER.info(test.getQuestions().size()+ " < questions,  "+params.get("name") +" "+ params.get("subj")+" "+ params.get("time"));
		test = tutorService.createTest(test, params.get("name"), params.get("subj"), params.get("time"), (Account)session.getAttribute("account"));
		LOGGER.info(test.getQuestions().size()+ " < questions,  "+test.getAuthor().getFirstName() +" "+ test.getName());
		int id = test.getIdTest();
		if(id == 0)
		tutorService.saveTest(test);
		else
		tutorService.mergeTest(test);
		return "tutor/home";
		}
		session.setAttribute("error", "You are not logged in, <a href=\"/wtapp/login\">log in</a> in order to create test.");
		return "error";
	}


}
