package ua.pri.dao.spring;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.pri.dao.QuestionDao;
import ua.pri.ent.Question;
@Repository("questionDao")
@Transactional(readOnly=false)
public class QuestionDaoSpring extends AbstractDaoSpring<Question> implements QuestionDao {


	@Override
	protected Class<Question> entityClass() {

		return Question.class;
	}

}
