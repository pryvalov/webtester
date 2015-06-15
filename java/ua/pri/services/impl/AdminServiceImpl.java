package ua.pri.services.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pri.dao.AccountDao;
import ua.pri.dao.AccountVerificationDao;
import ua.pri.dao.RoleDao;
import ua.pri.ent.Account;
import ua.pri.ent.Role;
import ua.pri.services.AdminService;
import ua.pri.services.IAccountFactory;

//import ua.pri.ent.Role;
/*
 * Simple service for administration purposes
 * allows to manage users
 * activate/deactivate
 * add/remove user roles
 * update info(no realization yet)
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	protected AccountDao accountDao;// = new AccountDaoImpl();
	
	@Autowired
	protected RoleDao roleDao;// = new RoleDaoImpl();
	
	@Autowired
	protected AccountVerificationDao accountVerificationDao;// = new AccountVerificationDaoImpl();
	
	@Autowired
	protected IAccountFactory accountFactory;

	@Override
	public void addUser(String login, String pwd, String email, String firstName) {
		accountFactory.newAccount(login, pwd, email, firstName);
	}

	@Override
	public void deactivate(String login) {
		Account acc = accountDao.findByLogin(login);
		acc.setActive(false);
		accountDao.update(acc);
	}
	
	@Override
	public void delete(String login) {
		Account acc = accountDao.findByLogin(login);
		accountDao.delete(acc);
	}

	@Override
	public void activate(String login) {
		Account acc = accountDao.findByLogin(login);
		acc.setActive(true);
		accountDao.update(acc);
	}

	@Override
	public void addRoles(String login, ERoles role) {
		Account acc = accountDao.findByLogin(login);
		switch (role) {
		case ADMIN:
			acc.getAccountRoles().add(roleDao.findById(0));
			break;
		case TUTOR:
			acc.getAccountRoles().add(roleDao.findById(2));
			break;
		case ADVANCED_TUTOR:
			acc.getAccountRoles().add(roleDao.findById(3));
			break;
		case STUDENT:
			acc.getAccountRoles().add(roleDao.student());
			break;
		}
		accountDao.update(acc);

	}

	@Override
	public void removeRoles(String login, ERoles role) {
		Account acc = accountDao.findByLogin(login);
		switch (role) {
		case ADMIN:
			acc.getAccountRoles().remove(roleDao.findById(0));
			break;
		case TUTOR:
			acc.getAccountRoles().remove(roleDao.findById(2));
			break;
		case ADVANCED_TUTOR:
			acc.getAccountRoles().remove(roleDao.findById(3));
			break;
		case STUDENT:
			acc.getAccountRoles().remove(roleDao.student());
			break;
		}
		accountDao.update(acc);
	}
	
	public Account updateUser(Account a, Map<String, String> params){
		
		a.setLogin(params.get("login")==null?a.getLogin() : params.get("login"));
		a.setEmail(params.get("email")==null?a.getEmail() : params.get("email"));
		a.setFirstName(params.get("firstName")==null?a.getFirstName() : params.get("firstName"));
		a.setLastName(params.get("lastName")==null?a.getLastName() : params.get("lastName"));
		a.setMiddleName(params.get("middleName")==null?a.getFirstName() : params.get("middleName"));
		

		
		List<String> roles = Arrays.asList(params.get("adminRole"),params.get("studentRole"),params.get("tutorRole"),params.get("advancedTutorRole"));
		
		Set<Role> rolesSet = new HashSet<Role>();
		for(int i = 0; i<roles.size(); i++){
			if(roles.get(i)!=null&&roles.get(i)!="")
				rolesSet.add(roleDao.findById(Integer.parseInt(roles.get(i))));
		}
		a.setAccountRoles(rolesSet);
		return a;
	}

	@Override
	public void updateUser(Account a) {
		accountDao.update(a);
	}
	
	@Override
	public List<Account> list(){
		return accountDao.getList();
	}
	@Override
	public List<Account> listCustom(int offset,int limit,String orderBy,boolean asc){
		return accountDao.getList(offset, limit, orderBy, asc);
	}

	@Override
	public Account getUser(String login) {
		return accountDao.findByLogin(login);
	}
	@Override
	public  Account createUser(){
		return new Account();
	}
	@Override
	public void saveUser (Account a){
		accountFactory.saveForm(a);
	}

}
