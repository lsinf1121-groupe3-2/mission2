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
		RBinaryTree<AnalyticExpression> f = analyticExpressionsTree.leftTree().leftTree();
		RBinaryTree<AnalyticExpression> g = analyticExpressionsTree.leftTree().rightTree();
		
		//On modifie le noeud du dessus
		analyticExpressionsTree.setElement(new AddOperator());
		
		//sous-sous arbre de gauche
		RBinaryTree<AnalyticExpression> leftD = new LinkedRBinaryTree<AnalyticExpression>();
		leftD.setElement(new UnaryDerivate());
		leftD.setLeft(f);
		
		//sous-sous arbre de droite
		RBinaryTree<AnalyticExpression> rightD = new LinkedRBinaryTree<AnalyticExpression>();
		rightD.setElement(new UnaryDerivate());
		rightD.setLeft(g);
		
		//sous arbre de gauche
		RBinaryTree<AnalyticExpression> leftMul = new LinkedRBinaryTree<AnalyticExpression>();
		leftMul.setElement(new MulOperator());
		leftMul.setLeft(leftD);
		leftMul.setRight(g.clone());
		
		//sous arbre de droite
		RBinaryTree<AnalyticExpression> rightMul = new LinkedRBinaryTree<AnalyticExpression>();
		rightMul.setElement(new MulOperator());
		rightMul.setLeft(f.clone());
		rightMul.setRight(rightD);
		
		analyticExpressionsTree.setLeft(leftMul);
		analyticExpressionsTree.setRight(rightMul);
	}

}
