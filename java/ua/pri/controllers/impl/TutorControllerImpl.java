package ua.pri.controllers.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pri.ent.Account;
import ua.pri.ent.Question;
import ua.pri.ent.Test;
import ua.pri.services.TutorService;

@Controller
@RequestMapping("/tutor")
public class TutorControllerImpl {

	private static final Logger LOGGER = LogManager
			.getLogger(TutorControllerImpl.class);

	@Autowired
	TutorService tutorService;

	@RequestMapping("view")
	public String view(Model model, HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		List<Test> tests = tutorService.getUserTests(account);

		model.addAttribute("tests", tests);
		return "tutor/home";
	}

	@RequestMapping("view_editor")
	public String viewEditor(Model model, HttpSession session) {
		Test test = (Test) session.getAttribute("test");

		test = tutorService.loadTest(test.getIdTest());
		session.setAttribute("test", test);
		return "tutor/editor";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getTutorHome(Model model, HttpSession session) {
/*		List<Test> tests = tutorService.getAllTests();
		
		model.addAttribute("tests", tests);*/
		session.setAttribute("test", null);
		session.setAttribute("question", null);
		//session.setAttribute("answer", null);
		return "redirect:view";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editTest(@RequestParam(required = false) String action,
			@RequestParam(required = false) String testId,
			@RequestParam(required = false) Integer idQuestion, Model model,
			HttpSession session) {
		if (testId == null)
			return "tutor/editor";
		try {
			switch (action) {
			case "update":
				Test test = tutorService.loadTest(Integer.valueOf(testId));
				session.setAttribute("test", test);
				if (idQuestion == null)
					return "tutor/editor";
				else {
					Question toEdit = test.getQuestions().get(
							test.getQuestions().indexOf(
									tutorService.findQuestion(idQuestion)));
					session.setAttribute("question", toEdit);
					return "tutor/editor";
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

		} catch (Exception e) {
			model.addAttribute("error",
					"Error loading test, contact administrator in order to slove this issue.");
			LOGGER.info("Test loading crash" + e.getMessage());
			return "error";
		}

	}



	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createTest(HttpSession session) {
		Test test = tutorService.createTest(); 
		test.setAuthor((Account) session.getAttribute("account"));
		tutorService.saveTest(test);
		LOGGER.debug(test.getIdTest() + " id created test");
		session.setAttribute("test", test);
		return "redirect:view_editor";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String createTest(@RequestParam Map<String, String> params,
			HttpSession session) {

		Test test = (Test) session.getAttribute("test");
		test = tutorService.updateTest(params, test);
		session.setAttribute("test", test);
		return "redirect:view_editor"; 

	}


	@RequestMapping(value = "/savequestion", method = RequestMethod.POST)
	public String saveQuestion(@RequestParam Map<String, String> params,
			Model model, HttpSession session) {

		Question question = (Question) session.getAttribute("question");
		Test test = (Test) session.getAttribute("test");
		//session.setAttribute("test", null);

		test = tutorService.updateQuestion(test, question, params);

		//tutorService.updateTest(params, test);

		session.setAttribute("test", test);
		session.setAttribute("question", null);
		return "redirect:view_editor";

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String removeQuestion(
			@RequestParam("idQuestion") Integer idQuestion, HttpSession session) {
		Test test = (Test) session.getAttribute("test");
		tutorService.deleteQuestion(idQuestion, test);

		return "redirect:view_editor";
	}

}
