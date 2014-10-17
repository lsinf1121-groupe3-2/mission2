package interpreter.command.binary;

import interpreter.command.AnalyticExpression;
import interpreter.command.unary.UnaryDerivate;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.LinkedRBinaryTree;
import linkedRBinaryTree.RBinaryTree;

public class AddOperator extends BinaryExpression {

	public AddOperator(String value) {
		super(value);
	}
	
	public AddOperator() {
		this("+");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException {
		if(!(analyticExpressionsTree.root().element() instanceof UnaryDerivate)){
			throw new UnexpectedOperatorException();
		}
		if(!(analyticExpressionsTree.leftTree().root().element() instanceof AddOperator)){
			throw new UnexpectedOperatorException();
		}
		//On construit le sous arbre de gauche
		RBinaryTree<AnalyticExpression> left = analyticExpressionsTree.leftTree().leftTree();
		RBinaryTree<AnalyticExpression> leftD = new LinkedRBinaryTree<AnalyticExpression>();
		leftD.setElement(new UnaryDerivate());
		leftD.setLeft(left);
		
		//On construit le sous arbre de droite
		RBinaryTree<AnalyticExpression> right = analyticExpressionsTree.leftTree().rightTree();
		RBinaryTree<AnalyticExpression> rightD = new LinkedRBinaryTree<AnalyticExpression>();
		rightD.setElement(new UnaryDerivate());
		rightD.setLeft(right);
		
		analyticExpressionsTree.setElement(new AddOperator());
		analyticExpressionsTree.setLeft(leftD);
		analyticExpressionsTree.setRight(rightD);
	}

}
