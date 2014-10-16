package interpreter.command.binary;

import interpreter.command.AnalyticExpression;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.RBinaryTree;

public class AddOperator extends BinaryExpression {

	public AddOperator(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public AddOperator() {
		this("+");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException {
		if(!(analyticExpressionsTree.root().element() instanceof UnaryDerivate)){
			throw new UnexpectedOperatorException();
		}
		

	}

}
