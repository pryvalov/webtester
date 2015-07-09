package ua.pri.controllers.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.pri.ent.Account;
import ua.pri.security.SecurityUtils;
import ua.pri.services.LoginService;


@Controller
public class VkControllerImpl {

	@Value("${host.uri}")
	private String host;
	
	private static final Logger LOGGER = LogManager
			.getLogger(VkControllerImpl.class);

	@Autowired
	private LoginService loginService;

	

	@RequestMapping(value = "/vklogin", method = RequestMethod.GET)
	public String vklogin() {
		return "redirect:https://oauth.vk.com/authorize?client_id=4987458 &scope=email&redirect_uri=http://"
				+ host + "/fromvk&display=page&v=5.34&response_type=code";
	}

	@RequestMapping(value = "/fromvk", method = RequestMethod.GET)
	public String fromvk(HttpServletRequest request, HttpSession session,
			Model model, @RequestParam(required = false) String code) {

		if (code != null) {
			try{
				
				Account account = loginService.login(code);//TODO: CharsetEncoding: html codes for russian letters - to fix
				/////////////////////////////////////////////spring encoding filter? 
				LOGGER.debug("vk login ok, logged first name: "+account.getFirstName());
				session.setAttribute("account", account);
				session.setAttribute("role", "student/home");
				SecurityUtils.autheificate(account);
				return "redirect:student/home";

			} catch (Exception e) {
				model.addAttribute("error", e.getMessage());
				return "error";
			}

		}
		return "info";
	}

}
