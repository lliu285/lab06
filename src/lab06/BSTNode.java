package lab06;

/*
 * Lab 4
 * Names: Lucia Liu, Nithya Ramasubramonian
 * Due date: 6/1/24
 * Purpose: The purpose of this lab is to practice implementing Binary Search Trees through a Dollar modeling scenario.
 * New edits: Add height attribute
 */
public class BSTNode {

	private Dollar dollar;
	private BSTNode left;
	private BSTNode right;
	private int height;

	/*
	 * Pre:
	 * dollar - The Dollar data of the node.
	 * 
	 * Post:
	 * dollar has been set to the user-given value.
	 * left and right child nodes have been set to null.
	 */
	public BSTNode(Dollar dollar) {
		this.dollar = dollar;
		this.left = null;
		this.right = null;
		height = 0;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	/*
	 * Post: Returns the dollar attribute of the BSTNode.
	 */
	public Dollar getDollar() {
		return dollar;
	}

	/*
	 * Post: Returns the left child of the BSTNode.
	 */
	public BSTNode getLeft() {
		return left;
	}

	/*
	 * Post: Returns the right child of the BSTNode.
	 */
	public BSTNode getRight() {
		return right;
	}

	/*
	 * Pre:
	 * dollar - The new Dollar object that is being set.
	 * 
	 * Post: 
	 * The dollar attribute has been set with the new Dollar object.
	 */
	public void setDollar(Dollar dollar) {
		this.dollar = dollar;
	}

	/*
	 * Pre:
	 * left - The new left BSTNode that is being set.
	 * 
	 * Post: 
	 * The left node attribute has been set with the new node.
	 */
	public void setLeft(BSTNode left) {
		this.left = left;
	}

	/*
	 * Pre:
	 * right - The new right BSTNode that is being set.
	 * 
	 * Post: 
	 * The right node attribute has been set with the new node.
	 */
	public void setRight(BSTNode right) {
		this.right = right;
	}
}