package interpreter.command;

import interpreter.exception.UnexpectedOperatorException;
import linkedRBinaryTree.RBinaryTree;

/**
 * @author Tanguy
 */
public abstract class AnalyticExpression {
	protected String value;

	public AnalyticExpression(String value){
		this.value = value;
	}
	
    /**
     * @pre la variable analyticExpressionsTree est un arbre binaire dont le noeud du dessus contient un opérateur de déravation et son noeud fils contient un opérateur du type du AnalyticExpression qu'on dérive
     * @post analyticExpressionsTree est modifié selon la règle de dérivation correspondant à l'opérateur
     * Toutes les méthodes derivate se font en temps constant
     */
	public abstract void derivate(RBinaryTree<AnalyticExpression> analyticExpressionsTree) throws UnexpectedOperatorException;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean equals(Object a){
		if(!(a instanceof AnalyticExpression))
			return false;
		return this.value.equalsIgnoreCase(((AnalyticExpression)a).getValue());
	}
	
	public String toString(){
		return this.value;
	}
}
