package linkedRBinaryTree;



/**
 *
 * @author Tanguy
 */
public class Node<E> implements Position<E>{
    E element;
    
    public Node(){
        
    }
    
    public Node(E element){
        this.element = element;
    }
    
    public void setElement(E element){
        this.element = element;
    }
    
    @Override
    public E element() {
        return this.element;
    }
    
}
