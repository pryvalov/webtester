package ua.pri.controllers.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

import ua.pri.ent.Account;
import ua.pri.security.SecurityUtils;
import ua.pri.services.LoginService;

@Controller
public class FacebookControllerImpl extends AbstractController implements InitializingBean{
	
	private static final Logger LOGGER = LogManager.getLogger(FacebookControllerImpl.class);
	
	@Value("${facebook.clientId}")
	private String facebookClientId;
	
	@Value("${facebook.secretKey}")
	private String facebookKey;
	
	@Value("${host.uri}")
	private String applicationHost;
	
	@Autowired
	private LoginService loginService;
	
	private String facebookRefer;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		facebookRefer = "https://graph.facebook.com/oauth/authorize?client_id="+facebookClientId+
				"&redirect_uri=http://"+applicationHost+"/fromfb&scope=email,user_location,user_birthday";
		LOGGER.info(facebookRefer);
		
	}
	
	@RequestMapping(value={"/fbLogin", "/fbSignup"}, method=RequestMethod.GET)
	public String fbLogin() throws Exception {
		return "redirect:"+facebookRefer;
	}
	
	@RequestMapping(value="/fromfb", method=RequestMethod.GET)
	public String fromfb(HttpServletRequest request, HttpSession session, @RequestParam(required=false) String code) throws Exception{
		User user = getFbUser(code);
		Account account = loginService.login(user);
		session.setAttribute("account", account);
/*		session.setAttribute("_role", Roles.Student);*/
		session.setAttribute("role", "student/home");
		SecurityUtils.autheificate(account);
		return "redirect:student/home";
	}
	
	@SuppressWarnings("deprecation")
	protected User getFbUser(String code) throws IOException{
		String url = "https://graph.facebook.com/oauth/access_token?client_id="
                + facebookClientId + "&redirect_uri=http://" + applicationHost + "/fromfb?referrer="
                + facebookRefer + "&client_secret=" + facebookKey + "&code=" + code;
        URLConnection connection = new URL(url).openConnection();
        InputStream in = null;
        try {
        	in = connection.getInputStream();
        	Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\Z");
            String out = scanner.next();
            String[] auth1 = out.split("=");
            String[] auth2 = auth1[1].split("&");
            FacebookClient facebookClient = new DefaultFacebookClient(auth2[0]);
            User user = facebookClient.fetchObject("me", User.class);
            scanner.close();
            return user;
        }finally {
        	try {
				in.close();
			} catch (Exception e) {
				LOGGER.error("error closing "+e.getMessage());
			}
        }
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
