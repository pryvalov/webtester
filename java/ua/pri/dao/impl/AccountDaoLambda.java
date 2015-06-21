package ua.pri.dao.impl;

import ua.pri.ent.Account;

public class AccountDaoLambda extends AbstractDaoLambda<Account> {

	@Override
	protected Class<Account> entityClass() {
		
		return Account.class;
	}

}
