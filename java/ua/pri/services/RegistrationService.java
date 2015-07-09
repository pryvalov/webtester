package ua.pri.services;

import java.util.Map;

import org.apache.commons.mail.EmailException;

import ua.pri.ent.Account;
import ua.pri.exceptions.InvalidUserInputException;
import ua.pri.exceptions.RegistrationException;
import ua.pri.forms.SignUpForm;

public interface RegistrationService {
	
	public abstract void passwordRecovery(String email) throws InvalidUserInputException , EmailException;
	
	public abstract Account signUpForm(SignUpForm form, boolean send_email) throws RegistrationException;
	
	public abstract Account updateProfile(Account account, Map<String, String> params) throws RegistrationException;

}