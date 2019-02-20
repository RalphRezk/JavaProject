package controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.AbstractQuestion;
import services.OptionService;
import services.QuestionService;
import visitor.QuestionSave;

@RestController
@RequestMapping("api/public")
public class QuestionRest {
	
	@Autowired
	private QuestionService qService;
	@Autowired
	private OptionService optionService;
	
	@RequestMapping(value="/questions",method=RequestMethod.GET)
	public ResponseEntity<Set<AbstractQuestion>> getQuestions() {
		Set<AbstractQuestion> questions = qService.listQuestions();
		return new ResponseEntity<Set<AbstractQuestion>>(questions,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/question",method=RequestMethod.POST)
	public ResponseEntity<Set<AbstractQuestion>> addQuestion(@RequestBody AbstractQuestion question) {
		if(question!=null) {
			QuestionSave questionSave = new QuestionSave(qService, optionService);
			questionSave.visit(question);
			return getQuestions();
		}
		return new ResponseEntity<Set<AbstractQuestion>>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/question/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Set<AbstractQuestion>> deleteQuestion(@PathVariable("id") long id) {
		qService.deleteQuestion(id);
		return new ResponseEntity<Set<AbstractQuestion>>(qService.listQuestions(),HttpStatus.OK);
		
	}
}
