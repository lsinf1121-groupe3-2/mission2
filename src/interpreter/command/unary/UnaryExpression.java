package interpreter.command.unary;

import linkedRBinaryTree.RBinaryTree;
import interpreter.command.AnalyticExpression;
import interpreter.exception.UnexpectedOperatorException;

/**
 * 
 * @author Tanguy
 *
 */
public abstract class UnaryExpression extends AnalyticExpression{

	public UnaryExpression(String value) {
		super(value);
	}

	public abstract void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException;
	
}
