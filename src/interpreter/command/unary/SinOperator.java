package interpreter.command.unary;

import interpreter.command.AnalyticExpression;
import interpreter.command.binary.MulOperator;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.LinkedRBinaryTree;
import linkedRBinaryTree.RBinaryTree;

public class SinOperator extends UnaryExpression {

	public SinOperator(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public SinOperator() {
		this("sin");
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException {
		// TODO Auto-generated method stub
		if(!(analyticExpressionsTree.root().element() instanceof UnaryDerivate)){
			throw new UnexpectedOperatorException();
		}
		if(!(analyticExpressionsTree.leftTree().root().element() instanceof CosOperator))
		{
			throw new UnexpectedOperatorException();
		}

		RBinaryTree<AnalyticExpression> f = analyticExpressionsTree.leftTree().leftTree();
		analyticExpressionsTree.setElement(new MulOperator());
		RBinaryTree<AnalyticExpression> LeftD = new LinkedRBinaryTree(analyticExpressionsTree);
		LeftD.setElement(new UnaryDerivate());
		analyticExpressionsTree.setLeft(LeftD);
		
		RBinaryTree<AnalyticExpression> RightD = new LinkedRBinaryTree(analyticExpressionsTree);
		RightD.setElement(new CosOperator());
		analyticExpressionsTree.setRight(RightD);
		
		RBinaryTree<AnalyticExpression> LeftF = new LinkedRBinaryTree(LeftD);
		LeftF.setElement(f.root().element());
		
		RBinaryTree<AnalyticExpression> RightF = new LinkedRBinaryTree(RightD);
		RightF.setElement(f.root().element());		
		
	}

}
