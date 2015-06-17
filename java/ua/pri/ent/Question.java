package ua.pri.ent;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;




@Entity
public class Question extends AbstractEntity {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 7753624271482332225L;


@Id
@SequenceGenerator(sequenceName="question_seq", name="question_gen")
@GeneratedValue(generator="question_gen")
@Column(name="idQuestion")
private int idQuestion;


@ManyToOne(cascade=CascadeType.PERSIST, targetEntity=Test.class)
@JoinColumn(name="id_test")
private Test test;

@Column(name="question_text")
private String questionText;

@OneToMany(mappedBy="question", cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
private List<Answer> answers = new ArrayList<>();




public int getIdQuestion() {
	return idQuestion;
}
public void setIdQuestion(int idQuestion) {
	this.idQuestion = idQuestion;
}


public Test getTest() {
	return test;
}
public void setTest(Test test) {
	this.test = test;
}
public String getQuestionText() {
	return questionText;
}
public void setQuestionText(String questionText) {
	this.questionText = questionText;
}



public List<Answer> getAnswers() {
	return answers;
}
public void setAnswers(List<Answer> answers) {
	this.answers = answers;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + idQuestion;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Question other = (Question) obj;
	if (idQuestion != other.idQuestion)
		return false;
	return true;
}






}
