package interpreter.command;

import linkedRBinaryTree.RBinaryTree;

/**
 * 
 * @author Tanguy
 *
 */
public abstract class AnalyticExpression {
	protected String value;

	public AnalyticExpression(String value){
		this.value = value;
	}
	
	public abstract void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree);
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
