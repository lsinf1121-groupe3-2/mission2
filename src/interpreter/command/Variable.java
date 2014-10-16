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
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws DerivateOperatorExpectedException {
		if(analyticExpressionsTree.root().element().getValue()!="D"){
			throw new DerivateOperatorExpectedException();
		}
		analyticExpressionsTree.setElement(new Number("1"));
		analyticExpressionsTree.setLeft(null);
		analyticExpressionsTree.setRight(null);
	}

}
