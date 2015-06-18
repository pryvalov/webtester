package ua.pri.dao;

import ua.pri.ent.Question;

public interface QuestionDao extends AbstractDao<Question>{
	
	public abstract void merge(Question q);

}
