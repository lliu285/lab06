package lab06;

/*
 * Lab #6
 * Name: Lucia Liu
 * Due date: 6/23/24
 * Purpose: The purpose of this lab is to implement a MinHeap derived from a BSTNode.
 */
public class HeapNode extends BSTNode
{
	private HeapNode parent;
	
	public HeapNode(Dollar dollar, HeapNode parent) 
	{
		super(dollar);
		this.parent = parent;
	}
	
	/*
	 * Post: Returns the parent HeapNode of this node
	 */
	public HeapNode getParent()
	{
		return parent;
	}
	
	/*
	 * Pre:
	 * parent - The new parent HeapNode that is being set
	 * 
	 * Post: 
	 * The parent attribute has been set with the new HeapNode parent
	 */
	public void setParent(HeapNode parent)
	{
		this.parent = parent;
	}

	/*
	 * Post: Returns the left HeapNode child.
	 */
	@Override
	public HeapNode getLeft() {
		return (HeapNode)super.getLeft();
	}

	/*
	 * Post: Returns the right HeapNode child.
	 */
	@Override
	public HeapNode getRight() {
		return (HeapNode)super.getRight();
	}
	
	/*
	 * Pre:
	 * left - The new left HeapNode that is being set.
	 * 
	 * Post: 
	 * The left node attribute has been set with the new HeapNode.
	 */
	public void setLeft(HeapNode left) {
		super.setLeft(left);
	}

	/*
	 * Pre:
	 * left - The new right HeapNode that is being set.
	 * 
	 * Post: 
	 * The right node attribute has been set with the new HeapNode.
	 */
	public void setRight(HeapNode right) {
		super.setRight(right);
	}
	
	/*
	 * Pre:
	 * node - The HeapNode whose height will be returned
	 * 
	 * Post:
	 * Returns an integer height of the given node.
	 */
	public int getHeight(HeapNode node)
	{
		/*
		 * Pseudocode:
		 * Returns -1 if node is null.
		 * Returns 0 if right child node is null.
		 * Otherwise, returns the height of the right child node + 1
		 */
		
		if (node == null) {
			return -1;
		} else if (node.getLeft() == null ||
				   node.getRight() == null) {
			return 0;
		} else {
			//left node height always greater than or equal to right node height
			return getHeight(node.getLeft()) + 1;
		}
	}
}
