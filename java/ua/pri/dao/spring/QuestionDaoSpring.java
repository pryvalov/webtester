package ua.pri.dao.spring;

import org.springframework.stereotype.Repository;

import ua.pri.dao.QuestionDao;
import ua.pri.ent.Question;

@Repository("questionDao")
public class QuestionDaoSpring extends AbstractDaoSpring<Question> implements
		QuestionDao {

	@Override
	protected Class<Question> entityClass() {

		return Question.class;
	}

	public void merge(Question q) {
		getSession().merge(q);
	}
	
	@Override
	public void delete(Question e) {
		e.getTest().getQuestionsSize();
		getSession().delete(e);
	}

}
