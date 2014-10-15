package interpreter.command;

import linkedRBinaryTree.RBinaryTree;

public class Variable extends AnalyticExpression{

	public Variable(String value) {
		super(value);
		
	}
	
	public Variable() {
		this("x");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) {
		// TODO Auto-generated method stub
		
	}

}
