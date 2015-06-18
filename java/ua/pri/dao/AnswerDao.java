package ua.pri.dao;

import ua.pri.ent.Answer;

public interface AnswerDao extends AbstractDao<Answer> {
	
	public abstract void merge(Answer a);

}
