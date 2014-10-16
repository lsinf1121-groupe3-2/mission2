package derivator;


import interpreter.command.AnalyticExpression;
import interpreter.command.unary.UnaryDerivate;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.LinkedRBinaryTree;
import linkedRBinaryTree.RBinaryTree;

public class Derivator {
	
	private RBinaryTree<AnalyticExpression> analyticExpressionsDerivatedTree;
	private boolean foundUnaryDerivate = true; 
	
	public Derivator(){
		this.analyticExpressionsDerivatedTree = new LinkedRBinaryTree<AnalyticExpression>();
	}

	public RBinaryTree<AnalyticExpression> derivate(RBinaryTree<AnalyticExpression> analyticExpressionBinaryTree) throws NullPointerException, UnexpectedOperatorException {
	
		if(analyticExpressionBinaryTree == null) 
			throw new NullPointerException();
		else
			initDerivatedTree(analyticExpressionsDerivatedTree, analyticExpressionBinaryTree);
		
		while(foundUnaryDerivate) {
			RBinaryTree<AnalyticExpression> searchResult = analyticExpressionsDerivatedTree.search(new UnaryDerivate());
			if(searchResult == null)
				foundUnaryDerivate = false;
			else
				searchResult.leftTree().root().element().derivate(searchResult);
		}
		
		return analyticExpressionsDerivatedTree;
	}
	
	private void initDerivatedTree(RBinaryTree<AnalyticExpression> TreeDerivated, RBinaryTree<AnalyticExpression> Tree) {
		UnaryDerivate derivate = new UnaryDerivate();
		TreeDerivated.setElement(derivate);
		TreeDerivated.setLeft(Tree);
	}
	
	
}
