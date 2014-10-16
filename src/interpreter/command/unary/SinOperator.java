package interpreter.command.unary;

import interpreter.command.AnalyticExpression;
import linkedRBinaryTree.RBinaryTree;

public class SinOperator extends UnaryExpression {

	public SinOperator(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public SinOperator() {
		this("sin");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) {
		// TODO Auto-generated method stub

	}

}
