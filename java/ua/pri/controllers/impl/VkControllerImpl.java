package ua.pri.controllers.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

@Controller
public class VkControllerImpl {

	@Value("${host.uri}")
	private String host;

	private static final Logger LOGGER = LogManager
			.getLogger(VkControllerImpl.class);

	@Autowired
	private LoginService loginService;

	private String getToken(String code) {
		Client client = Client.create();
		WebResource service = client
				.resource("https://oauth.vk.com/access_token?client_id=4987458&client_secret=z0cLILogFSPeSiyjkTKZ&"
						+ "code="
						+ code
						+ "&redirect_uri=http://"
						+ host
						+ "/fromvk");
		Builder requestBuilder = service
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE);
		ClientResponse response = requestBuilder.get(ClientResponse.class);

		return response.getEntity(String.class);

	}

	private String getDetails(String token, String user_id) {
		try {
			Client client = Client.create();
			WebResource service = client
					.resource("https://api.vk.com/method/users.get?user_id="
							+ user_id + "&v=5.34&access_token=" + token);
			Builder requestBuilder = service
					.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE);
			ClientResponse response = requestBuilder.get(ClientResponse.class);
			String resp = response.getEntity(String.class);
			return resp;

		} catch (Exception e) {
			LOGGER.error(" error " + e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/vklogin", method = RequestMethod.GET)
	public String vklogin() {
		return "redirect:https://oauth.vk.com/authorize?client_id=4987458 &scope=email&redirect_uri=http://"
				+ host + "/fromvk&display=page&v=5.34&response_type=code";
	}

	@RequestMapping(value = "/fromvk", method = RequestMethod.GET)
	public String fromvk(HttpServletRequest request, HttpSession session,
			Model model, @RequestParam(required = false) String code) {

		if (code != null) {
			String token = getToken(code);
			JSONObject parsedToken;
			String details;
			try {
				parsedToken = (JSONObject) new JSONParser().parse(token);
				details = getDetails((String) parsedToken.get("access_token"),
						"" + parsedToken.get("user_id"));
				LOGGER.debug("details: " + details);

				Account account = loginService.login(
						(String) parsedToken.get("email"), details);
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
