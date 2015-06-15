package ua.pri.services.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.pri.dao.AccountDao;
import ua.pri.dao.RoleDao;
import ua.pri.ent.Account;
import ua.pri.services.IAccountFactory;

@Service("accountFactory")
public class AccountFactory implements IAccountFactory {
	final static Logger LOGGER = LogManager.getLogger(AccountFactory.class);
	private static Account account;
	@Autowired
	@Qualifier("accountDao")
	private  AccountDao accountDao;// = new AccountDaoImpl();
	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao; //= new RoleDaoImpl();
	public AccountDao getAccountDao() {
		return accountDao;
	}
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	public RoleDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	@Override
	@Transactional
	public Account newAccount(String login, String password,
			String email, String firstName) {
		System.out.println(accountDao);
		account = new Account();
		account.setLogin(login);
		account.setPassword(password);
		account.setEmail(email);
		account.setFirstName(firstName);
		account.setEmailVerified(false);
		account.setActive(true);
		account.setCreated(new Date());
		account.getAccountRoles().add(roleDao.student());
		accountDao.save(account);

		return accountDao.findByLogin(login);

	}
	@Override
	@Transactional
	public Account newAccount(String login, String password,
			String email, String firstName, String lastName, String middleName) {
		account = newAccount(login, password, email, firstName);
		account.setLastName(lastName);
		account.setMiddleName(middleName);
		accountDao.update(account);
		return accountDao.findByLogin(login);
	}
	@Override
	@Transactional
	public Account saveForm(Account account){
		account.setEmailVerified(false);
		account.setActive(true);
		account.setCreated(new Date());
		account.getAccountRoles().add(roleDao.student());
		accountDao.save(account);
		return accountDao.findByLogin(account.getLogin());
	}
	
}
