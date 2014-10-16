package derivator;


import interpreter.command.AnalyticExpression;
import interpreter.command.unary.UnaryDerivate;
import interpreter.exception.OperatorNotFoundException;
import interpreter.exception.ParentExpectedException;
import interpreter.exception.UnknowOperatorException;

import linkedRBinaryTree.LinkedRBinaryTree;
import linkedRBinaryTree.RBinaryTree;

public class Derivator {
	
	private RBinaryTree<AnalyticExpression> analyticExpressionsDerivatedTree;
	private boolean FoundUnaryDerivate = true; 
	
	public Derivator(){
		this.analyticExpressionsDerivatedTree = new LinkedRBinaryTree<AnalyticExpression>();
	}

	public RBinaryTree<AnalyticExpression> derivate(RBinaryTree<AnalyticExpression> analyticExpressionBinaryTree) throws NullPointerException {
	
		if(analyticExpressionBinaryTree == null) 
			throw new NullPointerException();
		
		else InitDerivatedTree(analyticExpressionsDerivatedTree, analyticExpressionBinaryTree);
	
		while(FoundUnaryDerivate) {
			if(analyticExpressionsDerivatedTree.search(new UnaryDerivate()) == null )
				FoundUnaryDerivate = false;
		}
		
		return analyticExpressionsDerivatedTree;
	}
	
	private void InitDerivatedTree(RBinaryTree<AnalyticExpression> TreeDerivated, RBinaryTree<AnalyticExpression> Tree) {
		UnaryDerivate derivate = new UnaryDerivate();
		TreeDerivated.setElement(derivate);
		TreeDerivated.setLeft(Tree);
	}
	
	
}
