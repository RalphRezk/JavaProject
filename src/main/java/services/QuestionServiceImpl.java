package services;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dao.IGenericDao;
import model.AbstractQuestion;

@Service
public class QuestionServiceImpl implements QuestionService{

	IGenericDao<AbstractQuestion> qDAO;
	
	@Autowired
	public void setQuestionDAO(IGenericDao<AbstractQuestion> qDAO) {
		this.qDAO = qDAO;
		this.qDAO.setTargetClass(AbstractQuestion.class);
	}
	
	@Transactional
	public Set<AbstractQuestion> listQuestions() {
		Set<AbstractQuestion> questions = new HashSet<AbstractQuestion>();
		questions.addAll(qDAO.listAll());
		return questions;
	}

	@Transactional
	public void saveOrUpdate(AbstractQuestion q) {
		if(q.getId()==0)
			qDAO.create(q);
		else
			qDAO.update(q);
	}
	
	@Transactional
	public AbstractQuestion getQuestionById(long id) {
		return qDAO.findById(id);
	}

	@Transactional
	public void deleteQuestion(long id) {
		qDAO.deleteById(id);
	}

}
