package ua.pri.services.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restfb.types.User;

import ua.pri.dao.AccountDao;
import ua.pri.dao.RoleDao;
import ua.pri.ent.Account;
import ua.pri.ent.Role;
import ua.pri.exceptions.InvalidUserInputException;
import ua.pri.exceptions.RegistrationException;
import ua.pri.forms.SignUpForm;
import ua.pri.forms.LoginForm.Roles;
import ua.pri.services.LoginService;
import ua.pri.services.RegistrationService;

@Service("loginService")
public class LoginServiceImpl implements  LoginService {
	
	protected final static Logger LOGGER = LogManager.getLogger(LoginServiceImpl.class);
	
	@Autowired
	protected AccountDao accountDao;
	
	@Autowired
	protected RoleDao roleDao;
	
	@Autowired
	RegistrationService registrationService;
	
	@Override
	@Transactional
	public Account loadAccount(String email){
		return accountDao.findByEmail(email);
	}

	
	@Override
	@Transactional
	public Account login(String email, String password, Roles _role)
			throws InvalidUserInputException {
		int idRole = 1;
		switch(_role){
		case Administrator : idRole=0; break;
		case Student : idRole=1; break;
		case Tutor : idRole=2; break;
		case Advanced_tutor : idRole=3; break;
		}
		
		Role role = roleDao.findById(idRole);
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

		LOGGER.info(email+" succefully logged in as "+role.getRoleName());
		return account;
	}
	

	@Override
	@Transactional
	public List<Role> listAllRoles() {
		return roleDao.getList();
	}

	@Override
	@Transactional
	public Account login(User user) throws RegistrationException {
		Account account = accountDao.findByEmail(user.getEmail());
		if(account!=null)return account;
		else{
			SignUpForm form = new SignUpForm();
			form.setEmail(user.getEmail());
			form.setLogin(user.getEmail());
			form.setFirstName(user.getFirstName());
			form.setLastName(user.getLastName());
			UUID pwd = UUID.randomUUID();
			form.setPassword(pwd.toString());
			return registrationService.signUpForm(form, false);
		}
	}


}
