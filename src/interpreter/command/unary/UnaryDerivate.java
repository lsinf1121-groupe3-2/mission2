package interpreter.command.unary;

import interpreter.command.AnalyticExpression;
import linkedRBinaryTree.RBinaryTree;

public class UnaryDerivate extends UnaryExpression {

	public UnaryDerivate(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public UnaryDerivate() {
		this("D");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) {
		// TODO Auto-generated method stub
		
	}	

}
