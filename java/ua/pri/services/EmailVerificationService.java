package ua.pri.services;

import org.apache.commons.mail.EmailException;

import ua.pri.ent.Account;


public interface EmailVerificationService {
	
	public abstract void startVerification(Account a) throws EmailException;

	public abstract boolean verifyCode(Account a, Long code);
	public abstract boolean verifyCode(String email, Long code);
	public abstract void cleanExpired();

}