package ua.pri.services.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import ua.pri.services.MailingService;

@Service("mailingService")
public class MailingServiceImpl implements MailingService {
	
	@Override
	public void sendEmail(String address, String subj, String text) throws EmailException{
		Email email = new SimpleEmail();
		email.setHostName("smtp.mail.yahoo.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(
				"my_email_verification@yahoo.com", "sourceitsourceit"));
		email.setSSLOnConnect(true);
		email.setFrom("my_email_verification@yahoo.com");
		email.setSubject(subj);
		email.setMsg(text);
		email.addTo(address);
		email.send();
		
	}

}
