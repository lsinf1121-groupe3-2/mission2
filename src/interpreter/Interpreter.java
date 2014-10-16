/**
 * This package contains the logic needed to interprete an analytic expression and build a binary tree.
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

import sun.awt.image.OffScreenImage;
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
    int nbrOpenedBrackets = 0;
	int nbrClosedBrackets = 0;
    
    /**
     * Default constructor.
     * Initialize the tree and the commands map.
     */
    public Interpreter(){
        this.analyticExpressionsTree = new LinkedRBinaryTree<AnalyticExpression>();
        this.commandsMap = new HashMap<>();
        this.initializeCommands();
    }
    
    /**
     * @pre la variable commandsMap est initialisée.
     * @post les commandes sont instanciées et associées à leur représentation textuelle.
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
    
    private boolean isOpenningBracket(Character c){
    	return c=='(';
    }
    
    private boolean isClosingBracket(Character c){
    	return c==')';
    }
    
    private boolean isVariable(Character c){
    	return c=='x';
    }
    
    private boolean isDigit(Character c){
    	return Character.isDigit(c);
    }
    
    private boolean isOperator(Character c){
    	return commandsMap.get(c)!=null;
    }
    
    private void openningBracketRead(){
    	nbrOpenedBrackets++;
		if(this.analyticExpressionsTree == null)
			this.analyticExpressionsTree = new LinkedRBinaryTree<AnalyticExpression>();
		if(this.analyticExpressionsTree.leftTree() == null)
			this.analyticExpressionsTree.setLeft(new LinkedRBinaryTree<AnalyticExpression>());
		this.analyticExpressionsTree = this.analyticExpressionsTree.leftTree();
		//this.analyticExpressionsTree.setElement(new Number("0")); //by default set the element to 0; Easier to manager the negatives numbers
    }
    
    private void closingBracketRead(){
    	nbrClosedBrackets++;
    	if(this.analyticExpressionsTree.parent() == null){
    		(new LinkedRBinaryTree<AnalyticExpression>()).setLeft(this.analyticExpressionsTree);
    	}
		this.analyticExpressionsTree = this.analyticExpressionsTree.parent();
//		if(this.analyticExpressionsTree == null)
//			throw new ParentExpectedException();
    }
    
    private void variableRead() throws ParentExpectedException{
    	this.analyticExpressionsTree.setElement(new Variable("x"));
    	this.analyticExpressionsTree = this.analyticExpressionsTree.parent();
    	if(this.analyticExpressionsTree == null)
    		throw new ParentExpectedException();
    }
    
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
//				if(this.analyticExpressionsTree.leftTree() == null)
//					this.analyticExpressionsTree.setLeft(new LinkedRBinaryTree<AnalyticExpression>());
//				this.analyticExpressionsTree = this.analyticExpressionsTree.leftTree();
			}
			else if (operator instanceof BinaryExpression){
				//TODO: check if there is a left child!
//				if(this.analyticExpressionsTree.leftTree() == null){
//					this.analyticExpressionsTree = this.analyticExpressionsTree.parent();
//				}
				
				this.analyticExpressionsTree.setElement(operator);
				if(this.analyticExpressionsTree.rightTree() == null)
					this.analyticExpressionsTree.setRight(new LinkedRBinaryTree<AnalyticExpression>());
				
				this.analyticExpressionsTree = this.analyticExpressionsTree.rightTree();
			}
			else{
				//unknow operator
				throw new UnknowOperatorException();
			}
		}
		return i+offset-1;
	}
    
    /**
     * @throws ParentExpectedException 
     * @throws OperatorNotFoundException 
     * @throws UnknowOperatorException 
     */
    public RBinaryTree<AnalyticExpression> interprete(String commandString) throws ParentExpectedException, OperatorNotFoundException, UnknowOperatorException{
        charSequence = commandString.subSequence(0, commandString.length());
    	nbrOpenedBrackets = 0;
    	nbrClosedBrackets = 0;
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
