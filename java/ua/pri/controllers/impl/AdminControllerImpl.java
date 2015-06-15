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
import org.springframework.web.servlet.ModelAndView;

import ua.pri.ent.Account;
import ua.pri.services.AdminService;
@Controller
@RequestMapping("/admin")
public class AdminControllerImpl {
	
	private static final Logger LOGGER = LogManager.getLogger(AdminControllerImpl.class);
	
	private static boolean flag = true;
	@Autowired
	protected AdminService adminService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView getAdminHome(@RequestParam(required=false) String sort){
		
		List<Account> accs = null;
		ModelAndView mav = new ModelAndView("admin/home");
		if(sort!=null && sort.length()>2){
			accs = adminService.listCustom(0, 20, sort, flag);
			flag = !flag;
		}
		else
			accs = adminService.list();
		mav.addObject("accounts", accs);
		return mav;
	}

	@RequestMapping(value="/action", method=RequestMethod.GET)
	public String action(@RequestParam(required=false) String login, @RequestParam(required=false) String action, HttpSession session){
		switch (action){
		case "update": 
			session.setAttribute("user", adminService.getUser(login));
			return "admin/update";
		case "delete":	
			adminService.delete(login);
			break;
		case "activate":
			adminService.activate(login);
			break;
		case "deactivate":
			adminService.deactivate(login);
			break;
		}

		return "redirect:home";
	}
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@RequestParam Map<String,String> params, HttpSession session){
		Account a = (Account)session.getAttribute("user");
		if(a==null){
			LOGGER.error("Error updating user");
			return "/error";
		}
		a = adminService.updateUser(a, params);
	
		adminService.updateUser(a);
		return "redirect:home";
	}
}
