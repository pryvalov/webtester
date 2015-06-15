package ua.pri.services;

import java.util.List;

import ua.pri.ent.Account;
import ua.pri.ent.Role;
import ua.pri.exceptions.InvalidUserInputException;

public interface LoginService {

	public abstract Account login(String email, String password, int role)
			throws InvalidUserInputException;

	public abstract List<Role> listAllRoles();

}