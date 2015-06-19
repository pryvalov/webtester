package ua.pri.ent;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TestResult extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6207380518564895046L;

	@Id
	@SequenceGenerator(sequenceName="test_result_seq", name="test_result_gen")
	@GeneratedValue(generator="test_result_gen")
	@Column(name="id_test_result")
	private int idTestResult;
	
	@ManyToOne(targetEntity=Test.class)
	@JoinColumn(name="idtest")
	private Test test;
	
	@ManyToOne(targetEntity=Account.class)
	@JoinColumn(name="idaccount")
	private Account account;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String score;

	
	
	
	public int getIdTestResult() {
		return idTestResult;
	}

	public void setIdTestResult(int idTestResult) {
		this.idTestResult = idTestResult;
	}





	public Test getTest() {
		return test;
	}

	public void setTest(Test idTest) {
		this.test = idTest;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account idAccount) {
		this.account = idAccount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
}
