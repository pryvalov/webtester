package ua.pri.services;

import org.apache.commons.mail.EmailException;

public interface MailingService {

	public abstract void sendEmail(String address, String subj, String text)
			throws EmailException;

}