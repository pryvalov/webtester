package ua.pri.test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import ua.pri.services.impl.EmailVerificationServiceImpl;
import ua.pri.services.impl.LoginServiceImpl;
import ua.pri.services.impl.RegistrationServiceImpl;
import ua.pri.utils.SFactory;

@SuppressWarnings("unused")
public class HibernateTest {
	protected static final Logger logger = LogManager
			.getLogger(HibernateTest.class);
	
	private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}


	/**
	 * @param args
	 * @author Serhio
	 */
	// @SuppressWarnings("deprecation")
	//@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {

			/*ApplicationContext context = new ClassPathXmlApplicationContext(
					"spring.xml");
			AccountDao adao = (AccountDao) context.getBean("accountDao");
			RoleDao rd = (RoleDao) context.getBean("roleDao");
			AccountVerificationDao verDao = (AccountVerificationDao) context.getBean("accountVerificationDao");
			TestDao td = (TestDao) context.getBean("testDao");
			IAccountFactory afactory = (IAccountFactory) context.getBean("accountFactory");
			RegistrationService rservice = (RegistrationService) context.getBean("registrationService");
			EmailVerificationService emailService = (EmailVerificationService) context.getBean("emailVerificationService");
			AdminService adminka = (AdminService) context.getBean("adminService");
			TutorService tutorka = (TutorService) context.getBean("tutorService");*/
			Date d = new Date(System.currentTimeMillis()-(120*60*1000));
			System.out.println(d);
			System.out.println(getDateDiff(d, new Date(), TimeUnit.HOURS));

			
			//AccountDao ad2 = new AccountDaoImpl();
	/*		Test t = new Test();
			t.setName("testing new dao");
			Question q = new Question();
			q.setQuestionText("question text test");
			q.setTest(t);
			Answer a = new Answer();
			a.setAnswerText("answer text test");
			a.setQuestion(q);
			q.getAnswers().add(a);
			t.getQuestions().add(q);
			tutorka.saveTest(t);*/
			
			/*for(Test t1 : tutorka.getAllTests())
				System.out.println(t1.getName()+" "+t1.getIdTest());*/
	/*		
			Test opTest = tutorka.loadTest(37);
			System.out.println(opTest.getName());
			for(Question que : opTest.getQuestions()){
				System.out.println(que.getQuestionText());
//				que.setQuestionText(">>>>>>>>>SOME NEW UPDATED QUESTION<<<<<<<<<<<<");
				for(Answer ans : que.getAnswers()){
					System.out.println("      "+ans.getAnswerText());
//					ans.setAnswerText("SUPER UPDATED ANSWER");
				}
			}*/
			
			
			
		/*	Question q = new Question();
			q.setQuestionText("question saved ++");
			q.setTest(opTest);
			Answer a = new Answer();
			a.setAnswerText("answer saved ++");
			a.setQuestion(q);
			q.getAnswers().add(a);
			opTest.getQuestions().add(q);
			
			tutorka.updateTest(opTest);*/
			

		} finally {
		//	SFactory.close();
				
		}
	}

}
