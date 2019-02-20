package visitor;

import model.MultipleChoiceQuestion;
import model.Option;
import services.OptionService;
import services.QuestionService;

public class QuestionSave extends QuestionVisitor{
	
	private QuestionService questionService;
	private OptionService optionService;
	
	

	public QuestionSave(QuestionService questionService, OptionService optionService) {
		super();
		this.questionService = questionService;
		this.optionService = optionService;
	}

	@Override
	public void process(MultipleChoiceQuestion q) {
		for(Option option:q.getOptions()) {
			if(option.getId()==0)
				optionService.create(option);
			else
				optionService.update(option);
		}
		questionService.saveOrUpdate(q);
	}

}
