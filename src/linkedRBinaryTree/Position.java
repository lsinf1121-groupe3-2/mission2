package linkedRBinaryTree;

public interface Position<E> {
  /** Return the element stored at this position. */
  E element();
  
  /**
   * @pre this is not empty.
   * @post return a full clone of this.
   */
  public Node<E> clone();

}