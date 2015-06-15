package ua.pri.dao;


import ua.pri.ent.Account;
import ua.pri.ent.AccountVerification;

public interface AccountVerificationDao extends AbstractDao<AccountVerification>{
	public AccountVerification findByAccount(Account acc);

}