package ua.pri.ent;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Test extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8679351437759501570L;

	@Id
	@SequenceGenerator(sequenceName="test_seq", name="test_gen")
	@GeneratedValue(generator="test_gen")
	@Column(name="id_test")
	private int idTest;
	
//	@JoinColumn(name="id_account", nullable=false)
//	private int idAccountAuthor;
	@ManyToOne
	private Account author;
	

	private String subject;
	
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date created;
	
	@Temporal(TemporalType.DATE)
	private Date updated;
	
	@Column(name="time")
	private String time;
	
	private boolean active;

	@OneToMany(mappedBy="test", cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	private List<Question> questions = new ArrayList<>();
	
	
	
	
	public Account getAuthor() {
		return author;
	}

	public void setAuthor(Account author) {
		this.author = author;
	}

	
	
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
