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
public class Answer {
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
	

}