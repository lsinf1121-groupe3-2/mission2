package linkedRBinaryTree;

/**
 * Interface for a Binary Tree defined recursively.
 *
 * This interface uses the Position interface described in DSAJ-4.
 *
 * @author Tanguy
 */
public interface RBinaryTree<E> extends Cloneable {
	/**
	 * @pre -
	 * @post return true if this is empty, false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * @pre -
	 * @post return the number of nodes of this. Note: the number of nodes of an
	 *       empty tree is 0.
	 */
	public int size();

	/**
	 * @pre this is not empty.
	 * @post return a reference to the tree root.
	 */
	public Position<E> root();

	/**
	 * @pre this is not empty
	 * @post return true if this is reduced to a leaf (External Node), false
	 *       otherwise
	 */
	public boolean isLeaf();

	/**
	 * @pre this is not empty.
	 * @post return a reference to the left subtree.
	 */
	public RBinaryTree<E> leftTree();

	/**
	 * @pre this is not empty.
	 * @post return a reference to the right subtree.
	 */
	public RBinaryTree<E> rightTree();

	/**
	 * @pre this is not empty.
	 * @post o is the element stored at the root of this.
	 */
	public void setElement(E o);

	/**
	 * @pre this is not empty.
	 * @post tree is the left subtree of this. Parent of tree is this.
	 */
	public void setLeft(RBinaryTree<E> tree);

	/**
	 * @pre this is not empty.
	 * @post tree is the right subtree of this. Parent of tree is this.
	 */
	public void setRight(RBinaryTree<E> tree);

	/**
	 * @pre -
	 * @post an iterable collection of the positions of this, following an
	 *       inorder traversal, is returned.
	 */
	public Iterable<Position<E>> positions();

	/**
	 * @pre this is not empty.
	 * @post return a reference to the parent tree.
	 */
	public RBinaryTree<E> parent();

	/**
	 * @pre this is not empty.
	 * @post parent is the parent of this. this is not defined as parent's child
	 *       /!\
	 */
	public void setParent(RBinaryTree<E> parent);

	/**
	 * @pre this is not empty.
	 * @post return a reference to the first subtree which element's matching
	 *       element.
	 */
	public RBinaryTree<E> search(E element);

	/**
	 * @pre this is not empty.
	 * @post return a full clone of this.
	 */
	public LinkedRBinaryTree<E> clone();

	/**
	 * @pre this is not empty.
	 * @post return a string representation of this.
	 */
	public String toString();
}
