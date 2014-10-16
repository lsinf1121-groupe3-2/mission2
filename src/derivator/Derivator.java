package derivator;

import interpreter.command.AnalyticExpression;
import interpreter.command.Number;
import interpreter.command.Variable;
import interpreter.command.binary.AddOperator;
import interpreter.command.binary.BinaryExpression;
import interpreter.command.binary.DivOperator;
import interpreter.command.binary.ExpOperator;
import interpreter.command.binary.MulOperator;
import interpreter.command.binary.SubOperator;
import interpreter.command.unary.CosOperator;
import interpreter.command.unary.SinOperator;
import interpreter.command.unary.UnaryExpression;
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
		
		}
	
	
	
		
		
		return analyticExpressionsDerivatedTree;
	}
	
	private void InitDerivatedTree(RBinaryTree<AnalyticExpression> TreeDerivated, RBinaryTree<AnalyticExpression> Tree) {
		UnaryDerivate derivate = new UnaryDerivate();
		TreeDerivated.setElement(derivate);
		TreeDerivated.setLeft(Tree);
	}
	
	
}
