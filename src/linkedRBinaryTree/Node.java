package linkedRBinaryTree;

/**
 * @author Tanguy
 */
public class Node<E> implements Position<E>, Cloneable {
	E element;

	public Node() {

	}

	public Node(E element) {
		this.element = element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	@Override
	public E element() {
		return this.element;
	}

	public Node<E> clone() {
		Node<E> node = null;

		try {
			node = (Node<E>) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(System.err);
		}

		return node;
	}

}
