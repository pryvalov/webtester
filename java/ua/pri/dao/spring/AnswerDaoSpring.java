package ua.pri.dao.spring;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.pri.dao.AnswerDao;
import ua.pri.ent.Answer;
@Repository("answerDao")
@Transactional(readOnly=false)
public class AnswerDaoSpring extends AbstractDaoSpring<Answer> implements
		AnswerDao {

	
	@Override
	protected Class<Answer> entityClass() {
		// TODO Auto-generated method stub
		return Answer.class;
	}

}
