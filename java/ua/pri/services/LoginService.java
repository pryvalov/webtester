package ua.pri.services;

import java.util.List;

import ua.pri.ent.Account;
import ua.pri.ent.Role;
import ua.pri.exceptions.InvalidUserInputException;
import ua.pri.forms.LoginForm.Roles;

public interface LoginService {

	public abstract Account login(String email, String password, Roles _role)
			throws InvalidUserInputException;

	public abstract List<Role> listAllRoles();

}