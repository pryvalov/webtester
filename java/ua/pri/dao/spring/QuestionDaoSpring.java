package ua.pri.dao.spring;


import org.springframework.stereotype.Repository;

import ua.pri.dao.QuestionDao;
import ua.pri.ent.Question;
@Repository("questionDao")
//@Transactional(readOnly=false)
public class QuestionDaoSpring extends AbstractDaoSpring<Question> implements QuestionDao {


	@Override
	protected Class<Question> entityClass() {

		return Question.class;
	}
	
	public void merge(Question q) {
		getSession().merge(q);
		}

}
