package interpreter.command.binary;

import interpreter.command.AnalyticExpression;
import interpreter.command.unary.UnaryDerivate;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.LinkedRBinaryTree;
import linkedRBinaryTree.RBinaryTree;

public class MulOperator extends BinaryExpression {

	public MulOperator(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public MulOperator() {
		this("*");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException {


		if(!(analyticExpressionsTree.root().element() instanceof UnaryDerivate)){
			throw new UnexpectedOperatorException();
		}
		if(!(analyticExpressionsTree.leftTree().root().element() instanceof MulOperator)){
			throw new UnexpectedOperatorException();
		}
		//On enregistre f et g
		RBinaryTree<AnalyticExpression> left = analyticExpressionsTree.leftTree().leftTree();
		RBinaryTree<AnalyticExpression> right = analyticExpressionsTree.leftTree().rightTree();
		
		//On modifie le noeud du dessus
		analyticExpressionsTree.setElement(new AddOperator());
		analyticExpressionsTree.setLeft(leftD);
		analyticExpressionsTree.setRight(rightD);
		
		
		RBinaryTree<AnalyticExpression> leftD = new LinkedRBinaryTree<AnalyticExpression>(analyticExpressionsTree);
		leftD.setElement(new UnaryDerivate());
		leftD.setLeft(left);
		left.setParent(leftD);
		
		//On construit le sous arbre de droite
		
		RBinaryTree<AnalyticExpression> rightD = new LinkedRBinaryTree<AnalyticExpression>(analyticExpressionsTree);
		rightD.setElement(new UnaryDerivate());
		rightD.setLeft(right);
		right.setParent(rightD);
		
		

	}

}
