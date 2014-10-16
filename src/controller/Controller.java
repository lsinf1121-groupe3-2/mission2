package controller;

import interpreter.Interpreter;
import interpreter.command.AnalyticExpression;
import interpreter.exception.OperatorNotFoundException;
import interpreter.exception.ParentExpectedException;
import interpreter.exception.UnknowOperatorException;

import java.io.*;

import derivator.Derivator;
import linkedRBinaryTree.RBinaryTree;

/**
 * @author Tanguy
 */
public class Controller {

    String commandFile;
    String outputFile;
    String defaultFile = "resultFile.rmps";
    BufferedReader br;
    BufferedWriter bw;
    Interpreter interpreter;
    Derivator derivator;

    /**
     * @pre --
     * @post l'objet est dans un �tat coh�rent et pr�t � �tre utilis�
     */
    public Controller() {
        this.interpreter = new Interpreter();
    }
    
    /**
     * @pre --
     * @post extrait le fichier d'entr�e et le fichier de sortie du tableau d'arguments args
     */
    private void parseArgs(String[] args){
		if (args.length > 0 && args.length <= 2 && args[0] != null && !args[0].isEmpty()) { 
    		this.commandFile = args[0];
    		
    		if(args.length > 1 && args[1] != null && !args[1].isEmpty() ) {
    			this.outputFile = args[1];
    		}else{
    			this.outputFile = defaultFile;
    		}
    	}
		else{
			System.out.println("First argument must be a valid path to the commands file");
			System.exit(-1);
		}
    }
    
    /**
     * @pre la variable commandFile est initialis�e
     * @post le fichier renseign� dans la variable commandFile est ouvert et pr�t � �tre lu; la variable br est initialis�e.
     * Si le fichier n'existe pas, le programme se termine avec le code d'erreur -2.
     */
    private void initializeReader(){
		try {
			InputStream ips = new FileInputStream(commandFile);
			InputStreamReader ipsr = new InputStreamReader(ips);
			this.br = new BufferedReader(ipsr);
		} catch (FileNotFoundException e1) {
			System.out.println("Commands file not found. please check the path.");
			System.exit(-2);
		}
    }
    
    /**
     * @pre --
     * @post ouvre le fichier de sortie ou en cr�e un par d�faut si aucun fichier de sortie n'�tant renseign� en argument.
     * La variable bw est initialis�e.
     * Le programme se termine avec le code d'erreur -3 si il ne parvient pas � ouvrir/cr�er le fichier.
     */
    private void initializeWriter(){
		try {
			FileWriter fw = new FileWriter (this.outputFile);
			this.bw = new BufferedWriter(fw);
		} catch (IOException e1) {
			System.out.println("Error while opening output file.");
			System.exit(-3);
		}
    }
    
    /**
     * @pre les variables bw et br sont initialis�es.
     * @post Les fichiers ouverts par le programme sont ferm�s.
     * Le programme se termine avec le code d'erreur -4 si il ne parvient pas � fermer correctement les fichiers.
     */
    private void closeFiles(){
    	try {
			bw.close();
			br.close();
		} catch (IOException e) {
			System.out.println("Error while closing files.");
			System.exit(-4);
		}
    }
    
    /**
     * @pre Les variables bw et br sont initialis�es.
     * @post Le fichier d'entr�e � �t� enti�rement lu et interpr�t�.
     * Le r�sultat � �t� ecrit dans le fichier de sortie.
     * Le programme se termine avec le code d'erreur -5 si une erreur de lecture ou d'�criture survient pendant l'ex�cution.
     */
    private void interpreteFile(){
    	String commandLigne;
		try {
			while ((commandLigne = br.readLine())!=null){
				try{
					RBinaryTree<AnalyticExpression> analyticExpressionBinaryTree = interpreter.interprete(commandLigne);
					RBinaryTree<AnalyticExpression> derivatedAnalyticExpressionTree = derivator.derivate(analyticExpressionBinaryTree);
			        if (derivatedAnalyticExpressionTree != null) {
			        	bw.write(derivatedAnalyticExpressionTree.toString()+"\n"); //TODO: toString() parcours de l'arbre et constuction d'une expression entierement parenthesees
			        }
				} catch (ParentExpectedException e) {
					bw.write("Expression incorrect, are you missing a bracket in " + commandLigne);
				} catch (OperatorNotFoundException e) {
					bw.write("Operator not found in " + commandLigne);
				} catch (UnknowOperatorException e) {
					bw.write("Unknow operator in " + commandLigne);
				}
			}
		} catch (IOException e) {
			System.out.println("Error while I/O operations");
			System.exit(-5);
		} 
    }

    /**
     * @pre --
     * @post La logique m�tier permettant de lire le fichier d'entr�e contenant les commandes PostScript a �t� ex�cut�e.
     * Le r�sultat a �t� �crit dans le fichier de sortie.
     * Les fichiers ont �t� ferm�s correctement.
     */
    public void start(String[] args) {
    	this.parseArgs(args);
    	this.initializeReader();
    	this.initializeWriter();
		this.interpreteFile();
		this.closeFiles();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller applicationController = new Controller();
        applicationController.start(args);
    }

}
