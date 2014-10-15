package interpreter.command.binary;

import linkedRBinaryTree.RBinaryTree;
import interpreter.command.AnalyticExpression;

/**
 * 
 * @author Tanguy
 *
 */
public abstract class BinaryExpression extends AnalyticExpression{

	public BinaryExpression(String value) {
		super(value);
		
	}
	
	public abstract void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree);

}
