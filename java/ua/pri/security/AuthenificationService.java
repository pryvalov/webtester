package ua.pri.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.pri.dao.AccountDao;
import ua.pri.ent.Account;
import ua.pri.ent.Role;

@Service("authenificationService")
public class AuthenificationService implements UserDetailsService {

	private static final Logger LOGGER = LogManager.getLogger(AuthenificationService.class);
	@Autowired
	private AccountDao accountDao;
	private static final Map<Integer, String> ROLES = new HashMap<Integer, String>();
	static {
		ROLES.put(0, "ADMIN");
		ROLES.put(1, "STUDENT");
		ROLES.put(2, "TUTOR");
		ROLES.put(3, "ADVANCED_TUTOR");
		LOGGER.debug("AuthenificationService loaded");

	}

	static Collection<? extends GrantedAuthority> getGrantedAuthorities(
			Set<Role> roles) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (Role role : roles)
			authorities.add(new SimpleGrantedAuthority(ROLES.get(role
					.getRoleId())));
		return authorities;
	}

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		Account loadedAccount = accountDao.findByEmail(email);
		if (loadedAccount == null)
			throw new UsernameNotFoundException("Username not found " + email);
		return new CurrentAccount(loadedAccount);
	}

}
