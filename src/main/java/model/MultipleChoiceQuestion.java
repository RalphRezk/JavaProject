package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import utility.QuestionUtility;
import visitor.QuestionVisitor;

import javax.persistence.DiscriminatorType;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("MultipleChoice")
public class MultipleChoiceQuestion extends AbstractQuestion {

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "multiplechoice_options", joinColumns = {
			@JoinColumn(name= "parent" )
		},inverseJoinColumns = {
			@JoinColumn(name = "child")
		}
	)
	private List<Option> options = new ArrayList<Option>();

	
	public MultipleChoiceQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return QuestionUtility.MULTIPLE_CHOICE_QUESTION;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
	public int getTotalCorrect() {
		int total =0;
		for(Option option:this.options)
			if(option.isValid())
				total++;
		return total;
	}

	@Override
	public void accept(QuestionVisitor q) {
		q.process(this);
	}

	
}
