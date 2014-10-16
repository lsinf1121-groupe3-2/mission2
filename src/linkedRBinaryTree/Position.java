package linkedRBinaryTree;

public interface Position<E> {
  /** Return the element stored at this position. */
  E element();

  public Node<E> clone();

}