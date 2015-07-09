package ua.pri.controllers.impl;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pri.ent.Account;
import ua.pri.exceptions.RegistrationException;
import ua.pri.forms.SignUpForm;
import ua.pri.services.EmailVerificationService;
import ua.pri.services.LoginService;
import ua.pri.services.RegistrationService;

@Controller("signUpController")
public class SignUpControllerImpl {
	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private EmailVerificationService emailVerificationService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getSignUpForm(Model model) {
		model.addAttribute("signUpForm", new SignUpForm());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String sendSignUpForm(@ModelAttribute("SignUpForm") SignUpForm form,
			Model model, HttpSession session) {
		if (session.getAttribute("role") != null
				&& session.getAttribute("role").equals("admin/home")) {
			try {
				registrationService.signUpForm(form, false);
			} catch (RegistrationException e) {
				model.addAttribute("error", e.getMessage());
				return "error";
			}
			model.addAttribute("info", "User created succesfully.");
			return "info";
		}
		try {
			registrationService.signUpForm(form, true);
		} catch (RegistrationException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
		model.addAttribute("info",
				"In order to proceed registration check your email.");
		return "info";
	}

	@RequestMapping(value = "/recovery", method = RequestMethod.GET)
	public String recoverPassword() {
		return "recovery";
	}

	@RequestMapping(value = "/recovery", method = RequestMethod.POST)
	public String sendRecoverMail(@RequestParam("email") String email,
			HttpSession session) {

		try {
			registrationService.passwordRecovery(email);
			session.setAttribute("info", "Email with your details was sent");
			return "info";
		} catch (Exception e) {
			session.setAttribute("error", e.getMessage());
			return "error";
		}

	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String editProfile(@RequestParam(required = false) String edit,
			Model model, HttpSession session) {
		if (edit != null)
			model.addAttribute("edit", "yes");
		return "profile";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateProfile(@RequestParam Map<String, String> params,
			Model model, HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		try {
			account = registrationService.updateProfile(account, params);
		} catch (RegistrationException e) {
			model.addAttribute("exception_text", e.getMessage());
			model.addAttribute("edit", "yes");
			return "profile";
		}
		session.setAttribute("account", account);
		model.addAttribute("info", "Your account was updated");
		return "info";
	}

}
