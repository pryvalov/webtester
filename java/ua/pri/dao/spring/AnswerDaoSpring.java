package ua.pri.dao.spring;


import org.springframework.stereotype.Repository;

import ua.pri.dao.AnswerDao;
import ua.pri.ent.Answer;


@Repository("answerDao")
//@Transactional(readOnly=false)
public class AnswerDaoSpring extends AbstractDaoSpring<Answer> implements
		AnswerDao {

	
	@Override
	protected Class<Answer> entityClass() {
		return Answer.class;
	}
	
	
	public void merge(Answer a) {
		getSession().merge(a);
		}

}
