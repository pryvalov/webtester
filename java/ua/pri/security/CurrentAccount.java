package ua.pri.security;

import org.springframework.security.core.userdetails.User;

import ua.pri.ent.Account;

public class CurrentAccount extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7435126573416539389L;
	private final long idAccount;
	private int role;

	public CurrentAccount(Account account) {
		super(account.getEmail(), account.getPassword(), account.isActive(), true, true,
				account.isEmailVerified(), AuthenificationService.getGrantedAuthorities(account
						.getAccountRoles()));
		this.idAccount = account.getAccountId();
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public long getIdAccount() {
		return idAccount;
	}

}
