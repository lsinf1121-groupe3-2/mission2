package interpreter.command.unary;

import interpreter.command.AnalyticExpression;
import interpreter.command.Number;
import interpreter.command.binary.MulOperator;
import interpreter.command.unary.UnaryDerivate;
import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.LinkedRBinaryTree;
import linkedRBinaryTree.RBinaryTree;

public class CosOperator extends UnaryExpression {

	public CosOperator(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
	public CosOperator() {
		this("cos");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException {
		 {
			if(!(analyticExpressionsTree.root().element() instanceof UnaryDerivate)){
				throw new UnexpectedOperatorException();
			}
			if(!(analyticExpressionsTree.leftTree().root().element() instanceof CosOperator))
			{
				throw new UnexpectedOperatorException();
			}
			
			RBinaryTree<AnalyticExpression> f = analyticExpressionsTree.leftTree().leftTree();
			
			//analyticExpressionsTree = MulLeft = -1 * (mulRight)
			analyticExpressionsTree.setElement(new MulOperator());
			RBinaryTree<AnalyticExpression> sub1 = new LinkedRBinaryTree<AnalyticExpression>();
			sub1.setElement(new Number("-1"));
			
			//MulRight = D(f) * sin(f)
			RBinaryTree<AnalyticExpression> mulRight = new LinkedRBinaryTree<AnalyticExpression>();
			mulRight.setElement(new MulOperator());
			RBinaryTree<AnalyticExpression> leftD = new LinkedRBinaryTree<AnalyticExpression>();
			leftD.setElement(new UnaryDerivate());
			leftD.setLeft(f.clone());
			RBinaryTree<AnalyticExpression> sin = new LinkedRBinaryTree<AnalyticExpression>();
			sin.setElement(new SinOperator());
			sin.setLeft(f.clone());
			
			analyticExpressionsTree.setLeft(sub1);
			analyticExpressionsTree.setRight(mulRight);
			mulRight.setLeft(leftD);
			mulRight.setRight(sin);
		}
	}

}
