package ua.pri.services.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;




import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import ua.pri.dao.AccountDao;
import ua.pri.ent.Account;
import ua.pri.exceptions.InvalidUserInputException;
import ua.pri.exceptions.RegistrationException;
import ua.pri.forms.SignUpForm;
import ua.pri.services.EmailVerificationService;
import ua.pri.services.IAccountFactory;
import ua.pri.services.MailingService;
import ua.pri.services.RegistrationService;

@Service("registrationService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RegistrationServiceImpl implements RegistrationService {
	private static final Logger LOGGER = LogManager.getLogger(RegistrationServiceImpl.class);
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;
	
	@Autowired
	private AccountDao accountDao;// = new AccountDaoImpl();
	
	@Autowired
	private EmailVerificationService emailVerificationService;// = new EmailVerificationServiceImpl();
	
	@Autowired
	private IAccountFactory accountFactory;
	
	@Autowired
	private MailingService mailingService;
	
	protected boolean validateEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();

	}
	protected void sendVerificationEmail(Account a){
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
			throw new RegistrationException("login not available");
		if (!validateEmail(email))
			throw new RegistrationException("wrong email format");
		if (accountDao.findByEmail(email) != null)
			throw new RegistrationException("there is account with this email");
		if (password.length() < 4)
			throw new RegistrationException("too weak password");
		return true;
	}

	@Override
	public Account proceedRegistration(String login, String password,
			String email, String firstName) throws RegistrationException {
		Account a = null;
		if (validateInput(login, password, email))
			a = accountFactory.newAccount(login, password, email, firstName);
		sendVerificationEmail(a);
		return a;

	}

	@Override
	public Account proceedRegistration(String login, String password,
			String email, String firstName, String lastName, String middleName)
			throws RegistrationException {
		Account a = null;
		if (validateInput(login, password, email))
			a = accountFactory.newAccount(login, password, email, firstName,
					lastName, middleName);
		sendVerificationEmail(a);
		return a;

	}

	@Override
	@Transactional
	public Account signUpForm(SignUpForm form) throws RegistrationException {
		
		Account newUser = null;
		try {
			if (validateInput(form.getLogin(), form.getPassword(), form.getEmail())){
				newUser = accountFactory.newAccount();
		
				newUser.setEmail(form.getEmail());
				newUser.setLogin(form.getLogin());
				newUser.setFirstName(form.getFirstName());
				newUser.setLastName(form.getLastName());
				newUser.setMiddleName(form.getMiddleName());
				newUser.setPassword(form.getPassword());
				accountDao.save(newUser);
			sendVerificationEmail(newUser);
			}
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
			throw new RegistrationException(e);
		}
		LOGGER.info("sign up success for "+newUser.getLogin()+" "+newUser.getEmail());
		return newUser;
	}
	
	
	@Override
	@Transactional
	public Account signUpForm(SignUpForm form, boolean send_email) throws RegistrationException {
		if(send_email)
			return signUpForm(form);
		Account newUser = null;
		try {
			if (validateInput(form.getLogin(), form.getPassword(), form.getEmail())){
				newUser = accountFactory.newAccount();
		
				newUser.setEmail(form.getEmail());
				newUser.setLogin(form.getLogin());
				newUser.setFirstName(form.getFirstName());
				newUser.setLastName(form.getLastName());
				newUser.setMiddleName(form.getMiddleName());
				newUser.setPassword(form.getPassword());
				accountDao.save(newUser);
			}
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
			throw new RegistrationException(e);
		}
		LOGGER.info("sign up success for "+newUser.getLogin()+" "+newUser.getEmail());
		return newUser;
	}
	
	@Transactional
	public void passwordRecovery(String email) throws InvalidUserInputException, EmailException{
		Account account = accountDao.findByEmail(email);
			if(account==null)
				throw new InvalidUserInputException("No user with this email.");
			
			String message = "Hello "+account.getFirstName()+" somebody has started password recovery procedure for yours webtester account "+
					account.getLogin()+".\n So here is your password: "+account.getPassword()+"\n If it was not you, who started procedure"+
					" feel free to ignore this email. \n Best regards. \n Webtester project.";
			
		mailingService.sendEmail(account.getEmail(), "Password recovery", message);
		LOGGER.info("Sent recovery email for "+email);
	}

}
