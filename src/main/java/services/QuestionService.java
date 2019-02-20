package services;

import java.util.Set;

import model.AbstractQuestion;

public interface QuestionService {
	public Set<AbstractQuestion> listQuestions();
	public void saveOrUpdate(AbstractQuestion q);
	public AbstractQuestion getQuestionById(long id);
	public void deleteQuestion(long id);
}
