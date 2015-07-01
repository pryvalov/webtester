package ua.pri.services;

import java.util.List;



import com.restfb.types.User;

import ua.pri.ent.Account;
import ua.pri.ent.Role;
import ua.pri.exceptions.InvalidUserInputException;
import ua.pri.exceptions.RegistrationException;
import ua.pri.forms.LoginForm.Roles;

public interface LoginService {

	public abstract Account login(String email, String password, Roles _role)
			throws InvalidUserInputException;

	public abstract List<Role> listAllRoles();
	
	public abstract Account loadAccount(String email);
	
	public abstract Account login(User user) throws RegistrationException;

}