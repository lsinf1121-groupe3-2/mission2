package interpreter.command.binary;

import interpreter.command.AnalyticExpression;
import linkedRBinaryTree.RBinaryTree;

public class MulOperator extends BinaryExpression {

	public MulOperator(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public MulOperator() {
		this("*");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) {
		// TODO Auto-generated method stub

	}

}
