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
     * @pre la variable analyticExpressionsTree est un arbre binaire dont le noeud du dessus contient un op�rateur de d�ravation et son noeud fils contient un op�rateur du type du AnalyticExpression qu'on d�rive
     * @post analyticExpressionsTree est modifi� selon la r�gle de d�rivation correspondant � l'op�rateur
     * Toutes les m�thodes derivate se font en temps constant
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
