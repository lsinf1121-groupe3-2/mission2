package interpreter.command.binary;

import interpreter.command.AnalyticExpression;
import linkedRBinaryTree.RBinaryTree;

public class ExpOperator extends BinaryExpression {

	public ExpOperator(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public ExpOperator() {
		this("^");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) {
		// TODO Auto-generated method stub

	}

}
