package visitor;

import model.*;

public abstract class QuestionVisitor {
	public void visit(AbstractQuestion q) {
		q.accept(this);
	}
	public abstract void process(MultipleChoiceQuestion q);
}
