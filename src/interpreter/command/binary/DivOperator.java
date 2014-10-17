package interpreter.command.binary;

import interpreter.command.AnalyticExpression;
import interpreter.command.Number;
import interpreter.command.unary.UnaryDerivate;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.LinkedRBinaryTree;
import linkedRBinaryTree.RBinaryTree;

public class DivOperator extends BinaryExpression{

	public DivOperator(String value) {
		super(value);
	}
	
	public DivOperator() {
		this("/");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException {
		if(!(analyticExpressionsTree.root().element() instanceof UnaryDerivate)){
			throw new UnexpectedOperatorException();
		}
		if(!(analyticExpressionsTree.leftTree().root().element() instanceof DivOperator)){
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
		rightMul.setLeft(Dg);
		rightMul.setRight(f);
		leftMul.setLeft(Df);
		leftMul.setRight(g);
		rightMul.setElement(new MulOperator());
		leftMul.setElement(new MulOperator());
		
		//On crée le numérateur
		RBinaryTree<AnalyticExpression> Numerator = new LinkedRBinaryTree<AnalyticExpression>();
		Numerator.setLeft(leftMul);
		Numerator.setRight(rightMul);
		Numerator.setElement(new SubOperator());
		
		//On crée le dénominateur
		RBinaryTree<AnalyticExpression> Denominator = new LinkedRBinaryTree<AnalyticExpression>();
		RBinaryTree<AnalyticExpression> ggg = g.clone();
		RBinaryTree<AnalyticExpression> two = new LinkedRBinaryTree<AnalyticExpression>();
		two.setElement(new Number("2"));
		
		Denominator.setLeft(ggg);
		Denominator.setRight(two);
		Denominator.setElement(new ExpOperator());
		
		analyticExpressionsTree.setElement(new DivOperator());
		analyticExpressionsTree.setLeft(Numerator);
		analyticExpressionsTree.setRight(Denominator);
		
	}

}
