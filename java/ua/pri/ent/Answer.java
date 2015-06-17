package ua.pri.ent;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Answer extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8171661773875321733L;

	@Id
	@SequenceGenerator(sequenceName="answer_seq", name="answer_gen")
	@GeneratedValue(generator="answer_gen")
	@Column(name="id_answer")
	private int idAnswer;
	
	@ManyToOne(cascade=CascadeType.PERSIST, targetEntity=Question.class)
	@JoinColumn(name="id_question")
	private Question question;
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	@Column(name="answer_text")
	private String answerText;
	
	private boolean correct;
	
	
	public int getIdAnswer() {
		return idAnswer;
	}
	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}

	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAnswer;
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
		Answer other = (Answer) obj;
		if (idAnswer != other.idAnswer)
			return false;
		return true;
	}
	

}
