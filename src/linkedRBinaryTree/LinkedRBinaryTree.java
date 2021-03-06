package linkedRBinaryTree;

import java.util.ArrayList;

/**
 * @author Tanguy
 */
public class LinkedRBinaryTree<E> implements RBinaryTree<E>, Cloneable {
	private Position<E> root;
	private RBinaryTree<E> leftChild;
	private RBinaryTree<E> rightChild;
	private RBinaryTree<E> parent;

	public LinkedRBinaryTree() {
		this.root = null;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}

	@Override
	public boolean isEmpty() {
		return this.root == null;
	}

	@Override
	public int size() {
		int size = 0;
		if (!this.isEmpty()) {
			size += 1;
			if (this.leftTree() != null) {
				size += this.leftTree().size();
			}
			if (this.rightTree() != null) {
				size += this.rightTree().size();
			}
		}
		return size;
	}

	@Override
	public Position<E> root() {
		return this.root;
	}

	@Override
	public boolean isLeaf() {
		return this.leftChild == null && this.rightChild == null;
	}

	@Override
	public RBinaryTree<E> leftTree() {
		return this.leftChild;
	}

	@Override
	public RBinaryTree<E> rightTree() {
		return this.rightChild;
	}

	@Override
	public void setElement(E o) {
		this.root = new Node(o);
	}

	@Override
	public void setLeft(RBinaryTree<E> tree) {
		this.leftChild = tree;
		if (tree != null)
			tree.setParent(this);
	}

	@Override
	public void setRight(RBinaryTree<E> tree) {
		this.rightChild = tree;
		if (tree != null)
			tree.setParent(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		ArrayList<Position<E>> positions = new ArrayList();
		if (!this.isEmpty()) {
			positions.add(this.root);
			if (this.leftChild != null) {
				positions.addAll((ArrayList<Position<E>>) this.leftChild
						.positions());
			}
			if (this.rightChild != null) {
				positions.addAll((ArrayList<Position<E>>) this.rightChild
						.positions());
			}
		}
		return positions;
	}

	@Override
	public RBinaryTree<E> parent() {
		return parent;
	}

	@Override
	public void setParent(RBinaryTree<E> parent) {
		this.parent = parent;
	}

	public RBinaryTree<E> search(E element) {
		RBinaryTree<E> result = null;
		if (this.root.element().equals(element))
			result = this;
		else if (this.leftChild != null) {
			result = leftChild.search(element);
			if (result == null && this.rightChild != null) {
				result = rightChild.search(element);
			}
		}
		return result;
	}

	public LinkedRBinaryTree<E> clone() {
		LinkedRBinaryTree<E> linkedRBinaryTree = null;
		try {
			linkedRBinaryTree = (LinkedRBinaryTree<E>) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace(System.err);
		}

		linkedRBinaryTree.root = (Position<E>) root.clone();
		if (leftChild != null)
			linkedRBinaryTree.leftChild = (RBinaryTree<E>) leftChild.clone();
		if (rightChild != null)
			linkedRBinaryTree.rightChild = (RBinaryTree<E>) rightChild.clone();
		// linkedRBinaryTree.parent = (RBinaryTree<E>) parent.clone();

		// on renvoie le clone
		return linkedRBinaryTree;
	}

	public String toString() {
		if (this.leftChild != null && this.rightChild != null) {
			return "(" + this.leftChild.toString()
					+ this.root.element().toString()
					+ this.rightChild.toString() + ")";
		} else if (this.leftChild != null) {
			return this.root.element().toString() + "("
					+ this.leftChild.toString() + ")";
		} else {
			return this.root.element().toString();
		}
	}

}
