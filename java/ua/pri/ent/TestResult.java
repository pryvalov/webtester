package ua.pri.ent;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TestResult {
	
	@Id
	@SequenceGenerator(sequenceName="test_result_seq", name="test_result_gen")
	@GeneratedValue(generator="test_result_gen")
	@Column(name="id_test_result")
	private int idTestResult;
	
	@JoinColumn(name="id_test", nullable=false)
	private int idTest;
	
	@JoinColumn(name="id_account", nullable=false)
	private int idAccount;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String score;

	
	
	
	public int getIdTestResult() {
		return idTestResult;
	}

	public void setIdTestResult(int idTestResult) {
		this.idTestResult = idTestResult;
	}

	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
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
