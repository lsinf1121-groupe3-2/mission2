package interpreter.command.binary;

import linkedRBinaryTree.RBinaryTree;
import interpreter.command.AnalyticExpression;
import interpreter.exception.UnexpectedOperatorException;

/**
 * 
 * @author Tanguy
 *
 */
public abstract class BinaryExpression extends AnalyticExpression{

	public BinaryExpression(String value) {
		super(value);
		
	}
	
    /**
     * @pre la variable analyticExpressionsTree est un arbre dont le noeud du dessus est un op�rateur de d�rivation
     * @post l'abre est modifi� pour selon la r�gle de d�rivation
     */
	public abstract void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException;

}
