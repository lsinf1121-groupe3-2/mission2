package interpreter.command.binary;

import interpreter.command.AnalyticExpression;
import linkedRBinaryTree.RBinaryTree;

public class DivOperator extends BinaryExpression{

	public DivOperator(String value) {
		super(value);
	}
	
	public DivOperator() {
		this("/");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) {
		// TODO Auto-generated method stub
		
	}

}
