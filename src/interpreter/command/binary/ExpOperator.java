package interpreter.command.binary;

import interpreter.command.AnalyticExpression;
import interpreter.command.Number;
import interpreter.command.unary.UnaryDerivate;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.LinkedRBinaryTree;
import linkedRBinaryTree.RBinaryTree;

public class ExpOperator extends BinaryExpression {

	public ExpOperator(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public ExpOperator() {
		this("^");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException {
		if(!(analyticExpressionsTree.root().element() instanceof UnaryDerivate)){
			throw new UnexpectedOperatorException();
		}
		if(!(analyticExpressionsTree.leftTree().root().element() instanceof ExpOperator)){
			throw new UnexpectedOperatorException();
		}
		
		RBinaryTree<AnalyticExpression> f = analyticExpressionsTree.leftTree().leftTree();
		RBinaryTree<AnalyticExpression> a = analyticExpressionsTree.leftTree().rightTree();

		RBinaryTree<AnalyticExpression> Df = new LinkedRBinaryTree<AnalyticExpression>();
		Df.setElement(new UnaryDerivate());
		RBinaryTree<AnalyticExpression> ff = f.clone();
		Df.setLeft(ff);
		
		//Tous les coefficients avant l'exposant
		RBinaryTree<AnalyticExpression> coeff = new LinkedRBinaryTree<AnalyticExpression>();
		coeff.setElement(new MulOperator());
		coeff.setLeft(Df);
		coeff.setRight(a);
		
		RBinaryTree<AnalyticExpression> exp = new LinkedRBinaryTree<AnalyticExpression>();
		exp.setElement(new ExpOperator());
		exp.setLeft(f);
		
		//On crée l'exposant a-1
		RBinaryTree<AnalyticExpression> aMin1 = new LinkedRBinaryTree<AnalyticExpression>();
		RBinaryTree<AnalyticExpression> aa = a.clone();
		RBinaryTree<AnalyticExpression> one = new LinkedRBinaryTree<AnalyticExpression>();
		one.setElement(new Number("1"));
		
		aa.setParent(aMin1);
		aMin1.setLeft(aa);
		aMin1.setRight(one);
		
		exp.setRight(aMin1);
		
		analyticExpressionsTree.setElement(new MulOperator());
		analyticExpressionsTree.setLeft(coeff);
		analyticExpressionsTree.setRight(exp);
	}

}
