package interpreter.command;

import interpreter.command.unary.UnaryDerivate;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.RBinaryTree;

/**
 * @author Tanguy
 */
public class Variable extends AnalyticExpression{

	public Variable(String value) {
		super(value);
		
	}
	
	public Variable() {
		this("x");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException {
		if(!(analyticExpressionsTree.root().element() instanceof UnaryDerivate)){
			throw new UnexpectedOperatorException();
		}
		analyticExpressionsTree.setElement(new Number("1"));
		analyticExpressionsTree.setLeft(null);
		analyticExpressionsTree.setRight(null);
	}

}
