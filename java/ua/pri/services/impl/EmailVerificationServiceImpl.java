package ua.pri.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.pri.dao.AccountDao;
import ua.pri.dao.AccountVerificationDao;
import ua.pri.ent.Account;
import ua.pri.ent.AccountVerification;
import ua.pri.services.EmailVerificationService;
import ua.pri.services.MailingService;

@Service("emailVerificationService")
public class EmailVerificationServiceImpl implements EmailVerificationService {

	protected static final Logger LOGGER = LogManager
			.getLogger(EmailVerificationServiceImpl.class);


	@Autowired
	private AccountVerificationDao accountVerificationDao;
	
	@Autowired
	private MailingService mailingService;

	@Autowired
	private AccountDao accountDao;
	
	@Value("${host.uri}")
	private String hostName;
	
	protected AccountVerification accVerify = new AccountVerification();
	
	private long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}

	@Override
	@Transactional
	public void startVerification(Account a) throws EmailException {
		Random rand = new Random();
		 String verificationTemplate = "Hello "
				+ a.getFirstName()
				+ ",\n You have recieved this email, because it was provided in registration process on our"
				+ " glorious web testing site. To proceed registration click link below \n";

		long code = (long) Math.abs(rand.nextLong()*0.99);
		String mail = a.getEmail();
		accVerify.setAccount(a);
		accVerify.setCode(code);
		accountVerificationDao.save(accVerify);
		String mailBody = verificationTemplate
				+ " http://"+hostName+"/verifyemail?email=" + mail
				+ "&code=" + code;
		try{
		mailingService.sendEmail(mail,"Account verification for webtester project", mailBody);
		LOGGER.info("Succesfully sent validation email to " + mail);
		}catch(EmailException eme){
			LOGGER.error("Error sending validation email to "+mail+" cause: "+eme.getMessage());
			throw eme;
		}
	}

	/*@Override
	@Transactional
	public boolean verifyCode(Account a, Long code) {
		accVerify = accountVerificationDao.findByAccount(a);

		if (accVerify == null) {
			LOGGER.warn("Email verification fail: no account in verification table "
					+ a.getEmail());
			return false;

			// throw new RegistrationException("registration uptime expired");
		}
		if (accVerify.getCode() != code) {
			return false;
			// throw new RegistrationException("wrong code");
		}
		Account account = accountDao.findById(a.getAccountId());
		account.setEmailVerified(true);
		accountDao.update(account);
		accountVerificationDao.delete(accountVerificationDao.findByAccount(a));
		LOGGER.info("Succesfully validated " + account.getEmail());
		return true;
	}*/

	@Override
	@Transactional
	public boolean verifyCode(String email, Long code) {
		Account a = accountDao.findByEmail(email);
		if (a != null) {
			accVerify = accountVerificationDao.findByAccount(a);

			if (accVerify == null) {
				LOGGER.warn("Email verification fail: no account in verification table "
						+ a.getEmail());
				return false;

				// throw new
				// RegistrationException("registration uptime expired");
			}
			if (accVerify.getCode() != code) {
				return false;
				// throw new RegistrationException("wrong code");
			}
			Account account = accountDao.findById(a.getAccountId());
			account.setEmailVerified(true);
			accountDao.update(account);
			accountVerificationDao.delete(accountVerificationDao
					.findByAccount(a));
			LOGGER.info("Succesfully validated " + account.getEmail());
			return true;
		}
		return false;
	}
	@Scheduled(cron="0 0 0/4 1/1 * ?")
	public void cleanExpired(){
		List<AccountVerification> averifs = accountVerificationDao.getList();
		for(AccountVerification aver : averifs){
			Account account = aver.getAccount();
			if(getDateDiff(account.getCreated(), new Date(), TimeUnit.HOURS)>24){
				accountVerificationDao.delete(aver);
				accountDao.delete(account);
				LOGGER.info("Scheduled check deleted unverified account "+account.getAccountId()+" email: "
				+account.getEmail());
			}
		}
	}

}
