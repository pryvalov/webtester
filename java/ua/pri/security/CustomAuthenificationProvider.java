package ua.pri.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ua.pri.dao.AccountDao;
import ua.pri.ent.Account;
import ua.pri.utils.RolesConstants;

@Component("customAuthenticationProvider")
public class CustomAuthenificationProvider extends DaoAuthenticationProvider {

	private static Map<Integer, String> rolesHome = new HashMap<>();

	static {
		rolesHome.put(RolesConstants.ADMIN_ROLE_ID, "admin/home");
		rolesHome.put(RolesConstants.STUDENT_ROLE_ID, "student/home");
		rolesHome.put(RolesConstants.TUTOR_ROLE_ID, "tutor/home");
		rolesHome.put(RolesConstants.ADVANCED_TUTOR_ROLE_ID,
				"advanced_tutor/home");

	}

	@Autowired
	private AccountDao accountDao;

	private static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		super.additionalAuthenticationChecks(userDetails, authentication);
		CurrentAccount account = (CurrentAccount) userDetails;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		int role = Integer.parseInt(request.getParameter("idRole"));
		if (!RolesConstants.role_ids.contains(role))
			throw new AuthenticationException("Please select role ") {
				private static final long serialVersionUID = 9141828180466015708L;
			};
		Account sessionAccount = accountDao.findById((int) (long) account
				.getIdAccount());
		session().setAttribute("account", sessionAccount);
		session().setAttribute("role", rolesHome.get(role));
		account.setRole(role);
	}

	@Override
	@Autowired
	@Qualifier("passwordEncoder")
	public void setPasswordEncoder(Object passwordEncoder) {
		super.setPasswordEncoder(passwordEncoder);
	}

	@Override
	@Autowired
	@Qualifier("authenificationService")
	public void setUserDetailsService(UserDetailsService authenificationService) {
		super.setUserDetailsService(authenificationService);
	}

}
