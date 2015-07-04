package ua.pri.controllers.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pri.ent.Account;
import ua.pri.ent.Question;
import ua.pri.ent.Test;
import ua.pri.ent.TestResult;
import ua.pri.services.StudentService;
@Controller
@RequestMapping("/student")
public class StudentControllerImpl {
	
	private Iterator<Question> iter ;
	

	//private static final Logger LOGGER = LogManager.getLogger(StudentControllerImpl.class);
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
		Test test = studentService.loadTest(Integer.valueOf(testId));
		session.setAttribute("test", test);
		if(print.equals("1"))	
			return "student/print";
		
		if(print.equals("0")){
			session.setAttribute("question", null);
			return "student/quiz";
		
		}
		else{
			model.addAttribute("error", "wrong print value format");
			return "error";
		}
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/quiz", method=RequestMethod.POST)
	public String nextQuestion(@RequestParam Map<String, String> params, Model model, HttpSession session){
		Test test = (Test) session.getAttribute("test");
		List<Question> questions = test.getQuestions();
		Account student = (Account) session.getAttribute("account");
		int score = session.getAttribute("score")==null? 0 : (int) session.getAttribute("score"); 
		
		
		
		if(session.getAttribute("iterator")==null){
		iter = questions.iterator();
		score = 0;
		}
		if(session.getAttribute("iterator")!=null){
		iter = (Iterator<Question>) session.getAttribute("iterator");
		score += studentService.checkAnswer(params);
		}
		
		if(iter.hasNext()){
			session.setAttribute("question", iter.next());
			session.setAttribute("score", score);
			session.setAttribute("iterator", iter);
		}
		else{
			session.setAttribute("question", null);
			session.setAttribute("score", null);
			session.setAttribute("iterator", null);
			model.addAttribute("info", "Total score: "+score+" points.");
			TestResult testResult = studentService.makeTestResult(student, test);
			studentService.saveResult(testResult, score);
		}
		
		return "student/quiz";
		
	}
	@RequestMapping(value="/results", method=RequestMethod.GET)
	public String showResults(Model model, HttpSession session){
		Account account = (Account) session.getAttribute("account");
		List<TestResult> results = studentService.getUesrResults(account);
		model.addAttribute("results", results);
		return "student/result";
	}

}
