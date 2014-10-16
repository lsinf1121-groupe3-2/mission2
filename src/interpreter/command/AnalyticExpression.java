package interpreter.command;

import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.RBinaryTree;

/**
 * @author Tanguy
 */
public abstract class AnalyticExpression {
	protected String value;

	public AnalyticExpression(String value){
		this.value = value;
	}
	
	public abstract void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean equals(Object a){
		if(!(a instanceof AnalyticExpression))
			return false;
		return this.value.equalsIgnoreCase(((AnalyticExpression)a).getValue());
	}
	
	public String toString(){
		return this.value;
	}
}
