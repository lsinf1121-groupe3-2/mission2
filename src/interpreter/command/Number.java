package interpreter.command;

import linkedRBinaryTree.RBinaryTree;

public class Number extends AnalyticExpression{

	public Number(String value) {
		super(value);
		
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throw DerivateOperatorExpectedException {
		if(analyticExpressionsTree.root().element().getValue()!="D"){
			throw new DerivateOperatorExpectedException();
		}
		analyticExpressionsTree.setElement(new Number("0"));
		analyticExpressionsTree.setLeft(null);
		analyticExpressionsTree.setRight(null);
	}

}
