package derivator;

/**
 * @author Jonathan & Tanguy
 */

import interpreter.command.AnalyticExpression;
import interpreter.command.unary.UnaryDerivate;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.LinkedRBinaryTree;
import linkedRBinaryTree.RBinaryTree;

public class Derivator {
	
	private RBinaryTree<AnalyticExpression> analyticExpressionsDerivatedTree;
	
	public Derivator(){
		this.analyticExpressionsDerivatedTree = new LinkedRBinaryTree<AnalyticExpression>();
	}

	public RBinaryTree<AnalyticExpression> derivate(RBinaryTree<AnalyticExpression> analyticExpressionBinaryTree) throws NullPointerException, UnexpectedOperatorException {
	
		if(analyticExpressionBinaryTree == null) 
			throw new NullPointerException();
		else
			initDerivatedTree(analyticExpressionBinaryTree);
		
		RBinaryTree<AnalyticExpression> searchResult;
		while((searchResult = analyticExpressionsDerivatedTree.search(new UnaryDerivate())) != null) {
			if(searchResult.leftTree() == null){
				throw new NullPointerException("left tree expected but null found at" + searchResult.toString());
			}
			else
				searchResult.leftTree().root().element().derivate(searchResult);
		}
		
		return analyticExpressionsDerivatedTree;
	}
	
	private void initDerivatedTree(RBinaryTree<AnalyticExpression> tree) {
		this.analyticExpressionsDerivatedTree = new LinkedRBinaryTree<AnalyticExpression>();
		UnaryDerivate derivate = new UnaryDerivate();
		analyticExpressionsDerivatedTree.setElement(derivate);
		analyticExpressionsDerivatedTree.setLeft(tree);
	}
	
	
}
