package ua.pri.services;

import java.util.List;






import com.restfb.types.User;

import ua.pri.ent.Account;
import ua.pri.ent.Role;

import ua.pri.exceptions.RegistrationException;


public interface LoginService {

	public abstract List<Role> listAllRoles();
	
	public abstract Account loadAccount(String email);
	
	public abstract Account login(User user) throws RegistrationException;
	
	public abstract Account login(String code) throws Exception;

}