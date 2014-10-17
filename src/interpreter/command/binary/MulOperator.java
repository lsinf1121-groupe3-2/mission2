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
		
		//On crée Df et Dg
		RBinaryTree<AnalyticExpression> Df = new LinkedRBinaryTree<AnalyticExpression>();
		RBinaryTree<AnalyticExpression> Dg = new LinkedRBinaryTree<AnalyticExpression>();
		Df.setElement(new UnaryDerivate());
		Dg.setElement(new UnaryDerivate());
		RBinaryTree<AnalyticExpression> ff = f.clone();
		RBinaryTree<AnalyticExpression> gg = g.clone();
		Df.setLeft(ff);
		Dg.setLeft(gg);
		
		//On crée les deux sous noeuds multiplicateurs
		RBinaryTree<AnalyticExpression> rightMul = new LinkedRBinaryTree<AnalyticExpression>();
		RBinaryTree<AnalyticExpression> leftMul = new LinkedRBinaryTree<AnalyticExpression>();
		rightMul.setElement(new MulOperator());
		leftMul.setElement(new MulOperator());
		rightMul.setLeft(Df);
		rightMul.setRight(g);
		leftMul.setLeft(f);
		leftMul.setRight(Dg);
		
		analyticExpressionsTree.setElement(new AddOperator());
		analyticExpressionsTree.setLeft(leftMul);
		analyticExpressionsTree.setRight(rightMul);
	}

}
