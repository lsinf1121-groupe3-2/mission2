package interpreter.command.binary;

import interpreter.command.AnalyticExpression;
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
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) {
		// TODO Auto-generated method stub

	}

}
