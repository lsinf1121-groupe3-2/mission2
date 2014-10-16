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
     * @pre la variable analyticExpressionsTree est un arbre dont le noeud du dessus est un opérateur de dérivation
     * @post l'abre est modifié pour selon la règle de dérivation
     */
	public abstract void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException;

}
