package ua.pri.services;

import org.apache.commons.mail.EmailException;

import ua.pri.ent.Account;
import ua.pri.exceptions.InvalidUserInputException;
import ua.pri.exceptions.RegistrationException;

public interface RegistrationService {

	public abstract Account proceedRegistration(String login, String password,
			String email, String firstName) throws RegistrationException;

	public abstract Account proceedRegistration(String login, String password,
			String email, String firstName, String lastName, String middleName)
			throws RegistrationException;
	public abstract Account signUpForm(Account account) throws RegistrationException;
	
	public abstract void passwordRecovery(String email) throws InvalidUserInputException , EmailException;

}