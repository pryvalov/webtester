package ua.pri.test;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import ua.pri.dao.*;
import ua.pri.dao.impl.*;
import ua.pri.ent.*;
import ua.pri.exceptions.InvalidUserInputException;
import ua.pri.exceptions.RegistrationException;
import ua.pri.services.AdminService;
import ua.pri.services.EmailVerificationService;
import ua.pri.services.IAccountFactory;
import ua.pri.services.RegistrationService;
import ua.pri.services.TutorService;
import ua.pri.services.impl.ERoles;
import ua.pri.services.impl.EmailVerificationServiceImpl;
import ua.pri.services.impl.LoginServiceImpl;
import ua.pri.services.impl.RegistrationServiceImpl;
import ua.pri.utils.SFactory;

@SuppressWarnings("unused")
public class HibernateTest {
	protected static final Logger logger = LogManager
			.getLogger(HibernateTest.class);


	/**
	 * @param args
	 * @author Serhio
	 */
	// @SuppressWarnings("deprecation")
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {

			ApplicationContext context = new ClassPathXmlApplicationContext(
					"spring.xml");
			AccountDao adao = (AccountDao) context.getBean("accountDao");
			RoleDao rd = (RoleDao) context.getBean("roleDao");
			AccountVerificationDao verDao = (AccountVerificationDao) context.getBean("accountVerificationDao");
			TestDao td = (TestDao) context.getBean("testDao");
			IAccountFactory afactory = (IAccountFactory) context.getBean("accountFactory");
			RegistrationService rservice = (RegistrationService) context.getBean("registrationService");
			EmailVerificationService emailService = (EmailVerificationService) context.getBean("emailVerificationService");
			AdminService adminka = (AdminService) context.getBean("adminService");
			TutorService tutorka = (TutorService) context.getBean("tutorService");
	
			//AccountDao ad2 = new AccountDaoImpl();
			Test t = new Test();
			Question q = new Question();
			q.setQuestionText("testet234234tet 2342343");
			q.getAnswers().add(new Answer());
			t.getQuestions().add(q);
			tutorka.persistTest(t);
			
			
			

		} finally {
		//	SFactory.close();
				
		}
	}

}
