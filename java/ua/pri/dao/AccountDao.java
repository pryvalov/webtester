package ua.pri.dao;

import java.io.Serializable;
import java.util.List;

import ua.pri.ent.Account;

public interface AccountDao extends AbstractDao<Account>{

	public abstract Account findById(Serializable id);

	public abstract Account findByLogin(String _login);

	public abstract Account findByEmail(String _email);

	public abstract void setActive(int id);

	public abstract void setEmailVerified(int id);

	public abstract List<Account> getList(int offset, int limit);

	public abstract List<Account> getList(int offset, int limit,
			String orderBy, boolean asc);

}