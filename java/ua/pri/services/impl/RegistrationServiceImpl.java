package ua.pri.services.impl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.pri.components.IAccountFactory;
import ua.pri.dao.AccountDao;
import ua.pri.ent.Account;
import ua.pri.exceptions.InvalidUserInputException;
import ua.pri.exceptions.RegistrationException;
import ua.pri.forms.SignUpForm;
import ua.pri.services.EmailVerificationService;
import ua.pri.services.MailingService;
import ua.pri.services.RegistrationService;

@Service("registrationService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RegistrationServiceImpl implements RegistrationService {
	private static final Logger LOGGER = LogManager
			.getLogger(RegistrationServiceImpl.class);
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private EmailVerificationService emailVerificationService;

	@Autowired
	private IAccountFactory accountFactory;

	@Autowired
	private MailingService mailingService;

	protected void validateEmail(String email) throws RegistrationException {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		if (!matcher.matches())
			throw new RegistrationException("Wrong email format.");
		if (accountDao.findByEmail(email) != null)
			throw new RegistrationException("There is account with this email");

	}

	protected void sendVerificationEmail(Account a) {
		try {
			emailVerificationService.startVerification(a);
		} catch (EmailException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Transactional
	protected boolean validateInput(String login, String password, String email)
			throws RegistrationException {
		if (accountDao.findByLogin(login) != null)
			throw new RegistrationException("Login not available");
		validateEmail(email);
		if (password.length() < 4)
			throw new RegistrationException("too weak password");
		return true;
	}

	@Override
	@Transactional
	public Account signUpForm(SignUpForm form, boolean send_email)
			throws RegistrationException {

		Account newUser = null;
		try {
			if (validateInput(form.getLogin(), form.getPassword(),
					form.getEmail())) {
				newUser = accountFactory.newAccount();

				newUser.setEmail(form.getEmail());
				newUser.setLogin(form.getLogin());
				newUser.setFirstName(form.getFirstName());
				newUser.setLastName(form.getLastName());
				newUser.setMiddleName(form.getMiddleName());
				newUser.setPassword(form.getPassword());
				LOGGER.debug("New user before save first name: "
						+ newUser.getFirstName() + " last name: "
						+ newUser.getLastName());
				accountDao.save(newUser);
				LOGGER.info("sign up success for " + newUser.getLogin() + " "
						+ newUser.getEmail());
			}
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
			throw new RegistrationException(e);
		}

		if (send_email)
			sendVerificationEmail(newUser);

		return newUser;

	}

	@Transactional
	public void passwordRecovery(String email)
			throws InvalidUserInputException, EmailException {
		Account account = accountDao.findByEmail(email);
		if (account == null)
			throw new InvalidUserInputException("No user with this email.");

		String message = "Hello "
				+ account.getFirstName()
				+ " somebody has started password recovery procedure for yours webtester account "
				+ account.getLogin()
				+ ".\n So here is your password: "
				+ account.getPassword()
				+ "\n If it was not you, who started procedure"
				+ " feel free to ignore this email. \n Best regards. \n Webtester project.";

		mailingService.sendEmail(account.getEmail(), "Password recovery",
				message);
		LOGGER.info("Sent recovery email for " + email);
	}

	@Transactional
	public Account updateProfile(Account account, Map<String, String> params)
			throws RegistrationException {
		if (!params.get("email").equals(account.getEmail())) {
			validateEmail(params.get("email"));
		}
		if (!params.get("login").equals(account.getLogin())) {
			if (accountDao.findByLogin(params.get("login")) != null)
				throw new RegistrationException("Login not available");
		}
		if (!StringUtils.isBlank(params.get("password"))
				&& params.get("password").length() < 4) {
			throw new RegistrationException("Weak password: 4 symbols minimum.");
		}

		Account original = accountDao.findById(account.getAccountId());
		original.setEmail(params.get("email"));
		original.setLogin(params.get("login"));
		original.setFirstName(params.get("firstName"));
		original.setLastName(params.get("lastName"));
		original.setMiddleName(params.get("middleName"));

		if (!StringUtils.isBlank(params.get("password")))
			original.setPassword(params.get("password"));

		return original;

	}

}
