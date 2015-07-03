package ua.pri.utils;


import ua.pri.ent.Account;

public interface IAccountFactory {

	public abstract Account newAccount(String login, String password,
			String email, String firstName);

	public abstract Account newAccount(String login, String password,
			String email, String firstName, String lastName, String middleName);

	public abstract Account saveForm(Account account);
	
	public abstract Account newAccount();

}