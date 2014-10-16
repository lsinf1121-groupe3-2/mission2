package interpreter.command;

import interpreter.command.unary.UnaryDerivate;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.RBinaryTree;

/**
 * @author Tanguy
 */
public class Number extends AnalyticExpression{

	public Number(String value) {
		super(value);
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException {
		if(!(analyticExpressionsTree.root().element() instanceof UnaryDerivate)) {
			throw new UnexpectedOperatorException();
		}
		analyticExpressionsTree.setElement(new Number("0"));
		analyticExpressionsTree.setLeft(null);
		analyticExpressionsTree.setRight(null);
	}

}
