package ua.pri.services.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import ua.pri.dao.AccountDao;
import ua.pri.dao.RoleDao;
import ua.pri.ent.Account;
import ua.pri.ent.Role;
import ua.pri.exceptions.InvalidUserInputException;
import ua.pri.listeners.TestEvent;

import ua.pri.services.LoginService;
@Service("loginService")
public class LoginServiceImpl implements  LoginService, ApplicationEventPublisherAware {
	protected final static Logger LOGGER = LogManager.getLogger(LoginServiceImpl.class);
	@Autowired
	protected AccountDao accountDao;// = new AccountDaoImpl();
	@Autowired
	protected RoleDao roleDao;

	
	private ApplicationEventPublisher publisher;
	
	@Override
	public Account login(String email, String password, int _role)
			throws InvalidUserInputException {
		Role role = roleDao.findById(_role);
		if (StringUtils.isBlank(email) || StringUtils.isBlank(password))
			throw new InvalidUserInputException("empty fields");
		Account account = accountDao.findByEmail(email);
		if (account == null)
			throw new InvalidUserInputException("invalid email or password");
		if (!StringUtils.equals(account.getPassword(), password))
			throw new InvalidUserInputException("invalid email or password");
		if (!account.isActive())
			throw new InvalidUserInputException("account inactive");
		if (!account.isEmailVerified())
			throw new InvalidUserInputException("email not verified");
		if (!account.getAccountRoles().contains(role))
			throw new InvalidUserInputException("access as "
					+ role.getRoleName() + " prohobited");
		TestEvent testEvent = new TestEvent(this);
		publisher.publishEvent(testEvent);
		LOGGER.info(email+" succefully logged in as "+role.getRoleName());
		return account;
	}
	
//	public static Account loginStatic(String email, String password, Role role){
//		LoginService loginService = new LoginServiceImpl();
//		try{
//		return loginService.login(email, password, role);
//		}catch(InvalidUserInputException e){
//			LOGGER.error(e.getMessage());
//		}
//		return null;
//	}

	@Override
	public List<Role> listAllRoles() {
		return roleDao.getList();
	}

@Override
public void setApplicationEventPublisher(
		ApplicationEventPublisher applicationEventPublisher) {
	this.publisher = applicationEventPublisher;
	
}

}
