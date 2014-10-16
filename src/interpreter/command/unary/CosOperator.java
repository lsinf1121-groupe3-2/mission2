package interpreter.command.unary;

import interpreter.command.AnalyticExpression;
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
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) {
		// TODO Auto-generated method stub

	}

}
