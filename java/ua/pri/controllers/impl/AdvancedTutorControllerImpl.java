package ua.pri.controllers.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import ua.pri.ent.Account;
import ua.pri.ent.Answer;
import ua.pri.ent.Question;
import ua.pri.ent.Test;
import ua.pri.services.TutorService;

@Controller
@RequestMapping("/advanced_tutor")
public class AdvancedTutorControllerImpl {
	
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
		List<Test> tests = tutorService.getAllTests();
		session.setAttribute("tests", tests);
		session.setAttribute("test", null);
		session.setAttribute("question", null);
		session.setAttribute("answer", null);

		
		return "advanced_tutor/home";
	}
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String editTest(@RequestParam(required=false) String action, @RequestParam(required=false) String testId,
			@RequestParam(required=false) String idQuestion, HttpSession session){
		if (testId==null)
		return "advanced_tutor/editor";
		try{
			switch (action){
			case "update": 
				Test test = tutorService.loadTest(Integer.valueOf(testId));
				session.setAttribute("test", test);
				if(idQuestion==null)
				return "advanced_tutor/editor";
				else
				{
					Question toEdit = test.getQuestions().get(test.getQuestions().indexOf(tutorService.findQuestion(idQuestion)));
					//LOGGER.info(" loaded question:  " + toEdit.getQuestionText() + " ");
					session.setAttribute("question", toEdit);
					return "advanced_tutor/editor";
				}
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
		
		return "advanced_tutor/home";
		}
		session.setAttribute("error", "403: You are not authorized");
		return "error";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String createTest(HttpSession session){
		Test test = tutorService.createTest();
		session.setAttribute("test", test);
		return "advanced_tutor/create";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String createTest(@RequestParam Map<String, String> params, HttpSession session){
		
	/*	for(Map.Entry<String, String> entry : params.entrySet())
			LOGGER.info(entry.getKey()+ " "+entry.getValue());*/
		Test test = (Test) session.getAttribute("test");
		test = tutorService.updateTest(params, test);
		session.setAttribute("test", test);
		return "advanced_tutor/editor";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String addQuestions(@RequestParam() Map<String, String> params, HttpSession session){
		if(session.getAttribute("account")!=null)
		{
		Test test = tutorService.createTest(params.get("name"), params.get("subj"), params.get("time"), (Account)session.getAttribute("account"));
		session.setAttribute("test", test);
		return "advanced_tutor/editor";
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
		tutorService.updateTest(test); ///////////////////TEST METHOD///////////////////////////
		return "advanced_tutor/home";
		}
		session.setAttribute("error", "You are not logged in, <a href=\"/wtapp/login\">log in</a> in order to create test.");
		return "error";
	}
	
	@RequestMapping(value="savequestion", method=RequestMethod.POST)
	public String saveQuestion(@RequestParam Map<String, String> params, HttpSession session){
/*		for(Map.Entry<String, String> entry: params.entrySet())
			LOGGER.info(entry.getKey()+" ====== "+entry.getValue());
*/		Question question = (Question) session.getAttribute("question");
		Test test = (Test) session.getAttribute("test");
		question = tutorService.updateQuestion(question, params);
		LOGGER.info("changed text of question = "+question.getQuestionText());
			for(Answer ans : question.getAnswers())
				LOGGER.info("answer id: "+ans.getIdAnswer()+"  answer text: "+ans.getAnswerText()+" correct" + ans.isCorrect() );
		test.getQuestions().remove(question);
		test.getQuestions().add(question);
		session.setAttribute("test", test);
		session.setAttribute("question", null);
		return "advanced_tutor/editor";
		
	}
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String removeQuestion(@RequestParam("idQuestion")String idQuestion, HttpSession session){
		Test test = (Test) session.getAttribute("test");
		test.getQuestions().remove(tutorService.findQuestion(idQuestion));
		session.setAttribute("test", test);
		return "tutor/editor";
		
	}

}
