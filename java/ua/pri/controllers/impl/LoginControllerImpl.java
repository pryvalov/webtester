package ua.pri.controllers.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.pri.ent.Role;
import ua.pri.services.LoginService;
import ua.pri.utils.RolesConstants;

@Controller("loginController")
public class LoginControllerImpl implements InitializingBean {

	private static Map<Integer, String> rolesHome = new HashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		rolesHome.put(RolesConstants.ADMIN_ROLE_ID, "admin/home");
		rolesHome.put(RolesConstants.STUDENT_ROLE_ID, "student/home");
		rolesHome.put(RolesConstants.TUTOR_ROLE_ID, "tutor/home");
		rolesHome.put(RolesConstants.ADVANCED_TUTOR_ROLE_ID,
				"advanced_tutor/home");

	}

	@Autowired
	LoginService loginService;

	@ModelAttribute("roles")
	protected Role[] getRoles() {
		return loginService.listAllRoles().toArray(
				new Role[loginService.listAllRoles().size()]);
	}


	@RequestMapping(value = { "/login", "/loginFailed" }, method = RequestMethod.GET)
	public String getLoginForm(Model model) {
		// model.addAttribute("loginForm", new LoginForm());
		return "login";
	}


	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String myInfo(Model model, HttpSession session) {
		//CurrentAccount currentAccount = SecurityUtils.getCurrentAccount();
//		Account account  = loginService.loadAccount(currentAccount.getUsername());
//		session.setAttribute("account", account);
//		session.setAttribute("role", rolesHome.get(currentAccount.getRole()));
		return "redirect:" + session.getAttribute("role");//rolesHome.get(currentAccount.getRole());
	}

}
