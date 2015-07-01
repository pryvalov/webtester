package ua.pri.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ua.pri.ent.Account;

public class SecurityUtils {

	public static CurrentAccount getCurrentAccount() {
		Object principal = null;
		try {
			principal = SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
		} catch (Exception e) {
			return null;
		}
		if (principal instanceof CurrentAccount)
			return (CurrentAccount) principal;

		return null;
	}

	public static long getCurrentAccountId() {
		CurrentAccount account = getCurrentAccount();
		return account == null ? -1L : account.getIdAccount();
	}

	public static void autheificate(Account account) {
		Authentication auth = new UsernamePasswordAuthenticationToken(
				new CurrentAccount(account), account.getPassword(),
				AuthenificationService.getGrantedAuthorities(account
						.getAccountRoles()));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
