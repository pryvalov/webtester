package ua.pri.services.impl;


import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restfb.types.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import ua.pri.dao.AccountDao;
import ua.pri.dao.RoleDao;
import ua.pri.ent.Account;
import ua.pri.ent.Role;
import ua.pri.exceptions.RegistrationException;
import ua.pri.forms.SignUpForm;
import ua.pri.services.LoginService;
import ua.pri.services.RegistrationService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	protected final static Logger LOGGER = LogManager
			.getLogger(LoginServiceImpl.class);

	@Value("${host.uri}")
	private String host;

	@Autowired
	protected AccountDao accountDao;

	@Autowired
	protected RoleDao roleDao;

	@Autowired
	RegistrationService registrationService;

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

	@Override
	@Transactional
	public Account loadAccount(String email) {
		return accountDao.findByEmail(email);
	}

	@Override
	@Transactional
	public List<Role> listAllRoles() {
		return roleDao.getList();
	}

	@Override
	@Transactional
	public Account login(User user) throws RegistrationException {
		Account account = accountDao.findByEmail(user.getEmail());
		if (account != null)
			return account;
		else {
			SignUpForm form = new SignUpForm();
			form.setEmail(user.getEmail());
			form.setLogin(user.getEmail());
			form.setFirstName(user.getFirstName());
			form.setLastName(user.getLastName());
			UUID pwd = UUID.randomUUID();
			form.setPassword(pwd.toString());
			return registrationService.signUpForm(form, false);
		}
	}

	@Override
	@Transactional
	public Account login(String code) throws Exception {

		String token = getToken(code);
		JSONObject parsedToken = (JSONObject) new JSONParser().parse(token);
		String details = getDetails((String) parsedToken.get("access_token"),
				"" + parsedToken.get("user_id"));
		LOGGER.debug("details: " + details);
		String email = (String) parsedToken.get("email");
		Account account = accountDao.findByEmail(email);
		if (account != null)
			return account;

		JSONObject parsed = (JSONObject) new JSONParser().parse(details);
		JSONArray detailsArr = (JSONArray) parsed.get("response");

		JSONObject parsed2 = (JSONObject) detailsArr.get(0);

		assert (parsed2.get("first_name") == null);
		String parsedName = (String) parsed2.get("first_name");

		String encodedName = StringEscapeUtils.escapeHtml(parsedName);
		
		String parsedLastName = (String) parsed2.get("last_name");
		String encodedLastName = StringEscapeUtils.escapeHtml(parsedLastName);
		
		SignUpForm form = new SignUpForm();
		form.setEmail(email);
		form.setLogin(email);
		form.setFirstName(encodedName);
		form.setLastName(encodedLastName);

		LOGGER.debug("first name: " + form.getFirstName() + " last name: "
				+ form.getLastName());
		UUID pwd = UUID.randomUUID();
		form.setPassword(pwd.toString());
		return registrationService.signUpForm(form, false);
	}

}
