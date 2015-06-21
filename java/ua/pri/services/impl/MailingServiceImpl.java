package ua.pri.services.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ua.pri.services.MailingService;

@Service("mailingService")
public class MailingServiceImpl implements MailingService {
	@Value("${smtp.hostname}")
	private String smtpServer;
	
	@Value("${smtp.fromaddress}")
	private String fromAddress;
	
	@Value("${smtp.password}")
	private String password;
	
	@Value("${smtp.port}")
	private Integer port;
	
	
	
	@Override
	public void sendEmail(String address, String subj, String text) throws EmailException{
		Email email = new SimpleEmail();
		email.setHostName(smtpServer);
		email.setSmtpPort(port);
		email.setAuthenticator(new DefaultAuthenticator(
				fromAddress, password));
		email.setSSLOnConnect(true);
		email.setFrom(fromAddress);
		email.setSubject(subj);
		email.setMsg(text);
		email.addTo(address);
		email.send();
		
	}

}
