package interpreter.command.unary;

import interpreter.command.AnalyticExpression;
import interpreter.command.unary.UnaryDerivate;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.RBinaryTree;

public class CosOperator extends UnaryExpression {

	public CosOperator(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public CosOperator() {
		this("cos");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException {
		 {
			if(!(analyticExpressionsTree.root().element() instanceof UnaryDerivate)){
				throw new UnexpectedOperatorException();
			}
			if(!(analyticExpressionsTree.leftTree().root().element() instanceof CosOperator))
			{
				throw new UnexpectedOperatorException();
			}
		}
	}

}
