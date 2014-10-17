/**
 * This package contains the logic needed to interpret an analytic expression and build a binary tree.
 */
package interpreter;

import interpreter.command.AnalyticExpression;
import interpreter.command.Number;
import interpreter.command.Variable;
import interpreter.command.binary.AddOperator;
import interpreter.command.binary.BinaryExpression;
import interpreter.command.binary.DivOperator;
import interpreter.command.binary.ExpOperator;
import interpreter.command.binary.MulOperator;
import interpreter.command.binary.SubOperator;
import interpreter.command.unary.CosOperator;
import interpreter.command.unary.SinOperator;
import interpreter.command.unary.UnaryExpression;
import interpreter.exception.OperatorNotFoundException;
import interpreter.exception.ParentExpectedException;
import interpreter.exception.UnknowOperatorException;

import java.util.HashMap;
import java.util.Map;

import linkedRBinaryTree.LinkedRBinaryTree;
import linkedRBinaryTree.RBinaryTree;

/**
 * This class is able to interpret an analytic expression and build a binary tree.
 * @author Tanguy
 */
public class Interpreter {
    private RBinaryTree<AnalyticExpression> analyticExpressionsTree;
    private Map<String, AnalyticExpression> commandsMap;
    private CharSequence charSequence;
    
    /**
     * @pre --
     * @post L'objet est dans un �tat coh�rent: ses attributs ont �t� initialis�s ainsi que les commandes.
     */
    public Interpreter(){
        this.analyticExpressionsTree = new LinkedRBinaryTree<AnalyticExpression>();
        this.commandsMap = new HashMap<>();
        this.initializeCommands();
    }
    
    /**
     * @pre la variable commandsMap est initialis�e.
     * @post les commandes sont instanci�es et associ�es � leur repr�sentation textuelle.
     * Cette association est maintenue dans la Map commandsMap.
     */
    private void initializeCommands(){
        this.commandsMap.put("-", new SubOperator());
        this.commandsMap.put("/", new DivOperator());
        this.commandsMap.put("cos", new CosOperator());
        this.commandsMap.put("+", new AddOperator());
        this.commandsMap.put("*", new MulOperator());
        this.commandsMap.put("sin", new SinOperator());
        this.commandsMap.put("^", new ExpOperator());
    }
    
    /**
     * @pre --
     * @post le caract�re est compar� avec le caract�re "(".
     * la valeur true est retourn�e s'ils sont �gaux. Faux sinon.
     */
    private boolean isOpenningBracket(Character c){
    	return c=='(';
    }
    
    /**
     * @pre --
     * @post le caract�re est compar� avec le caract�re ")".
     * la valeur true est retourn�e s'ils sont �gaux. Faux sinon.
     */
    private boolean isClosingBracket(Character c){
    	return c==')';
    }
    
    /**
     * @pre --
     * @post le caract�re est compar� avec le caract�re "x".
     * la valeur true est retourn�e s'ils sont �gaux. Faux sinon.
     */
    private boolean isVariable(Character c){
    	return c=='x';
    }
    
    /**
     * @pre --
     * @post le caract�re est compar� avec un entier.
     * la valeur true est retourn�e s'ils sont �gaux. Faux sinon.
     */
    private boolean isDigit(Character c){
    	return Character.isDigit(c);
    }
    
    /**
     * @pre un caract�re "(" vient d'�tre lu.
     * @post le noeud courant est cr�� s'il n'existe pas.
     * son fils gauche est cr�� s'il n'existe pas
     * la r�f�rence vers le sous-arbre courant est d�plac� vers le fils gauche
     */
    private void openningBracketRead(){
		if(this.analyticExpressionsTree == null)
			this.analyticExpressionsTree = new LinkedRBinaryTree<AnalyticExpression>();
		if(this.analyticExpressionsTree.leftTree() == null)
			this.analyticExpressionsTree.setLeft(new LinkedRBinaryTree<AnalyticExpression>());
		this.analyticExpressionsTree = this.analyticExpressionsTree.leftTree();
    }
    
    /**
     * @pre un caract�re ")" vient d'�tre lu.
     * @post la r�f�rence vers le sous-arbre courant est d�plac� vers son parent.
     * Ce dernier est cr�� s'il n'existe pas.
     */
    private void closingBracketRead(){
    	if(this.analyticExpressionsTree.parent() == null){
    		(new LinkedRBinaryTree<AnalyticExpression>()).setLeft(this.analyticExpressionsTree);
    	}
		this.analyticExpressionsTree = this.analyticExpressionsTree.parent();
    }
    
    /**
     * @pre un caract�re "x" vient d'�tre lu.
     * @post la valeur du sous-arbre courant est fix�e avec la valeur de la variable lue.
     * La r�f�rence vers le sous-arbre courant est d�plac� vers son parent.
     * L'exception ParentExpectedException est lanc�e si le parent est null.
     */
    private void variableRead() throws ParentExpectedException{
    	this.analyticExpressionsTree.setElement(new Variable("x"));
    	this.analyticExpressionsTree = this.analyticExpressionsTree.parent();
    	if(this.analyticExpressionsTree == null)
    		throw new ParentExpectedException();
    }
    
    /**
     * @pre un entier vient d'�tre lu.
     * @post la valeur du sous-arbre courant est fix�e avec la valeur de l'entier lu.
     * La r�f�rence vers le sous-arbre courant est d�plac� vers son parent.
     * Ce dernier est cr�� s'il n'existe pas.
     * l'it�rateur i est incr�ment� du d�placement n�cessaire pour lire le nombre entier int�gralement.
     * L'exception ParentExpectedException est lanc�e si le parent est null.
     */
	private int digitRead(int i) throws ParentExpectedException{
		/**
		 * Read the next value to see if this is a digit too. (ex: 100 must not be read as "1" and "0" and "0")
		 */
		int offset = 1;
		while(i+offset < charSequence.length() && Character.isDigit(charSequence.charAt(i+offset))){
			offset++;
		}
		//the number goes from i to i+offset-1
		String number = charSequence.subSequence(i, i+offset).toString();
		this.analyticExpressionsTree.setElement(new Number(number));
		this.analyticExpressionsTree = this.analyticExpressionsTree.parent();
    	if(this.analyticExpressionsTree == null)
    		throw new ParentExpectedException();
		return i+offset-1;
    }
	
	/**
     * @pre --
     * @post La valeur du sous-arbre courant est fix�e avec la valeur de l'op�rateur lu.
     * Si l'op�rateur est binaire et qu'il ne poss�de pas de fils gauche, la valeur du sous-arbre courant est fix�e 0 et la valeur sous-arbre parent est fix�e avec la valeur de l'op�rateur lu. 
     * Si l'op�rateur est binaire, le sous-arbre courant est d�plac� vers son fils droit.
     * Ce dernier est cr�� s'il n'existe pas.
     * l'it�rateur i est incr�ment� du d�placement n�cessaire pour lire l'op�rateur int�gralement.
     * L'exception UnknowOperatorException est lanc�e si l'op�rateur n'est pas connu.
     * L'exception OperatorNotFoundException est lanc�e si l'op�rateur n'est pas trouv�.
     */
	private int operatorRead(Character c, int i) throws OperatorNotFoundException, UnknowOperatorException{
		int offset = 1;
		AnalyticExpression operator = commandsMap.get(c.toString());
		//search for operator like 'sin' or 'cos' (more than 1 char)
		while(i+offset < charSequence.length() && operator == null){
			String cmd = charSequence.subSequence(i, i+offset+1).toString();
			operator = commandsMap.get(cmd);
			offset++;
		}
		if(operator == null){
			//can't read operator or operator not found
			throw new OperatorNotFoundException();
		}
		else {
			if(operator instanceof UnaryExpression){
				this.analyticExpressionsTree.setElement(operator);
				//no need to move because we must read a openning bracket after a unary operator
			}
			else if (operator instanceof BinaryExpression){
				//Because we are a Binary operator, we should already have a left child.
				//If not, put a 0 there and put the operator to the parent tree.
				if(this.analyticExpressionsTree.leftTree() == null){
					this.analyticExpressionsTree.setElement(new Number("0"));
					if(this.analyticExpressionsTree.parent() == null)
						(new LinkedRBinaryTree<AnalyticExpression>()).setLeft(this.analyticExpressionsTree);
					this.analyticExpressionsTree = this.analyticExpressionsTree.parent();
				}
				
				this.analyticExpressionsTree.setElement(operator);
				if(this.analyticExpressionsTree.rightTree() == null)
					this.analyticExpressionsTree.setRight(new LinkedRBinaryTree<AnalyticExpression>());
				
				this.analyticExpressionsTree = this.analyticExpressionsTree.rightTree();
			}
			else{
				throw new UnknowOperatorException();
			}
		}
		return i+offset-1;
	}
    
    /**
     * @pre le param�tre commandString contient est une chaine de caract�re repr�sentant une expression analytique enti�rement parenth�s�es.
     * Toutes les variables de travail (analyticExpressionsTree, commandsMap) ont �t� correctement initialis�es.
     * @post L'expression analytique est repr�sent�e sous la forme d'un arbre binaire.
     * Ce dernier est retourn�.
     * L'exception ParentExpectedException est lanc�e si le parent est null.
     * L'exception OperatorNotFoundException est lanc�e si l'op�rateur n'est pas trouv�.
     * L'exception UnknowOperatorException est lanc�e si l'op�rateur n'est pas connu.
     */
    public RBinaryTree<AnalyticExpression> interprete(String commandString) throws ParentExpectedException, OperatorNotFoundException, UnknowOperatorException{
        charSequence = commandString.subSequence(0, commandString.length());
    	this.analyticExpressionsTree = new LinkedRBinaryTree<AnalyticExpression>(); //we start from a fresh new tree
        
        for(int i = 0; i < charSequence.length(); i++){
        	Character currentChar = charSequence.charAt(i);
        	if(isOpenningBracket(currentChar)){
        		openningBracketRead();
        	}
        	else if (isClosingBracket(currentChar)) {
        		closingBracketRead();
        	}
        	else if (isVariable(currentChar)){
        		variableRead();
        	}
        	else if (isDigit(currentChar)){
        		 i = digitRead(i);
        	}
        	else {
        		i = operatorRead(currentChar, i);
        	}
        }
        if(this.analyticExpressionsTree.root() == null && this.analyticExpressionsTree.leftTree() != null){
        	//we add too much parent
        	this.analyticExpressionsTree = this.analyticExpressionsTree.leftTree();
        	this.analyticExpressionsTree.setParent(null);
        }
    	return this.analyticExpressionsTree;
    }
    
}
