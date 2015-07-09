package ua.pri.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.pri.components.IAccountFactory;
import ua.pri.dao.AccountDao;
import ua.pri.dao.AccountVerificationDao;
import ua.pri.dao.RoleDao;
import ua.pri.ent.Account;
import ua.pri.ent.Role;
import ua.pri.services.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	protected AccountDao accountDao;

	@Autowired
	protected RoleDao roleDao;

	@Autowired
	protected AccountVerificationDao accountVerificationDao;

	@Autowired
	protected IAccountFactory accountFactory;

	@Override
	public void addUser(String login, String pwd, String email, String firstName) {
		accountFactory.newAccount(login, pwd, email, firstName);
	}

	@Override
	@Transactional
	public void deactivate(String login) {
		Account acc = accountDao.findByLogin(login);
		acc.setActive(false);
		accountDao.update(acc);
	}

	@Override
	@Transactional
	public void delete(String login) {
		Account acc = accountDao.findByLogin(login);
		accountDao.delete(acc);
	}

	@Override
	@Transactional
	public void activate(String login) {
		Account acc = accountDao.findByLogin(login);
		acc.setActive(true);
		accountDao.update(acc);
	}

	@Transactional
	public Account updateUser(Account a, Map<String, String> params) {

		a.setLogin(params.get("login") == null ? a.getLogin() : params
				.get("login"));
		a.setEmail(params.get("email") == null ? a.getEmail() : params
				.get("email"));
		a.setFirstName(params.get("firstName") == null ? a.getFirstName()
				: params.get("firstName"));
		a.setLastName(params.get("lastName") == null ? a.getLastName() : params
				.get("lastName"));
		a.setMiddleName(params.get("middleName") == null ? a.getFirstName()
				: params.get("middleName"));

		List<String> roles = Arrays.asList(params.get("adminRole"),
				params.get("studentRole"), params.get("tutorRole"),
				params.get("advancedTutorRole"));

		Set<Role> rolesSet = new HashSet<Role>();
		for (int i = 0; i < roles.size(); i++) {
			if (!StringUtils.isBlank(roles.get(i)))
				rolesSet.add(roleDao.findById(i));
		}
		if (rolesSet.size() == 0)
			rolesSet.add(roleDao.student());
		if (rolesSet.contains(roleDao.findById(3)))
			rolesSet.add(roleDao.findById(2));
		a.setAccountRoles(rolesSet);
		updateUser(a);
		return a;
	}

	@Override
	@Transactional
	public void updateUser(Account a) {
		a.setUpdated(new Date());
		accountDao.update(a);
	}
	
	@Override
	public void saveUser(Account a) {
		accountFactory.saveForm(a);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Account> list() {
		return accountDao.getList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Account> listCustom(int offset, int limit, String orderBy,
			boolean asc) {
		return accountDao.getList(offset, limit, orderBy, asc);
	}

	@Override
	@Transactional(readOnly=true)
	public Account getUser(String login) {
		return accountDao.findByLogin(login);
	}

	

}
