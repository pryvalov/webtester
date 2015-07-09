package ua.pri.services;

import java.util.List;
import java.util.Map;

import ua.pri.ent.Account;


public interface AdminService {

	public abstract void addUser(String login, String pwd, String email,
			String firstName);
	public abstract void delete(String login);

	public abstract void deactivate(String login);

	public abstract void activate(String login);

	public abstract void updateUser(Account a);
	
	public abstract void saveUser(Account a);
	
	public abstract Account getUser(String login);
	
	public abstract Account updateUser(Account a, Map<String, String> params);

	public abstract List<Account> list();
	
	public abstract List<Account> listCustom(int offset,int limit,String orderBy,boolean asc);

}