package lab06;

/*
 * Lab #6
 * Name: Lucia Liu
 * Due date: 6/23/24
 * Purpose: The purpose of this lab is to implement a MinHeap derived from a BSTNode.
 */
public class MinHeap
{
	private HeapNode root;
		
	public MinHeap()
	{
		root = null;
	}
	
	/*
	 * 
	 */
	private void percolateUp(HeapNode node)
	{
		while (node.getParent() != null &&
			   node.getParent().getDollar().isGreater(node.getDollar())) {
			swapNodes(node, node.getParent());
			node = node.getParent();
		}
	}
	
	/*
	 * 
	 */
	private void percolateDown(HeapNode node)
	{
		while (node.getLeft() != null /*&&
			   node.getDollar().isGreater(node.getLeft().getDollar())*/) {
			if (node.getRight() == null) {
				swapNodes(node, node.getLeft());
				node = node.getLeft();
			} else if (node.getRight().getDollar().isGreater(node.getLeft().getDollar())) {
				swapNodes(node, node.getLeft());
				node = node.getLeft();
			} else {
				swapNodes(node, node.getRight());
				node = node.getRight();
			}
		}
	}
	
	/*
	 * Pre:
	 * node1 - Node that will be swapped with node2
	 * node2 - Node that will be swapped with node3
	 * 
	 * Post:
	 * The Dollar values of node1 and node2 are swapped.
	 */
	private void swapNodes(HeapNode node1, HeapNode node2)
	{
		Dollar temp = node1.getDollar();
		node1.setDollar(node2.getDollar());
		node2.setDollar(temp);
	}
	
	/*
	 * Pre:
	 * dollar - Dollar value that is inserted into the min heap
	 * 
	 * Post:
	 * A HeapNode with the specified Dollar value is inserted into the heap.
	 */
	public void insert(Dollar dollar) 
	{
		HeapNode newNode = insertHelper(root, dollar);
		percolateUp(newNode);
	}
	
	/*
	 * Pre:
	 * node - 
	 * dollar - 
	 */
	private HeapNode insertHelper(HeapNode node, Dollar dollar) 
	{
		HeapNode newNode;
		
		if (node == null) { // insert root node
			newNode = new HeapNode(dollar, null);
			root = newNode;
		} else if (node.getLeft() == null) {
			// right node must be null too.
			newNode = new HeapNode(dollar, node);
			node.setLeft(newNode);
		} else if (node.getRight() == null) {
			newNode = new HeapNode(dollar, node);
			node.setRight(newNode);
		} else if (node.getHeight(node.getLeft()) > node.getHeight(node.getRight())) {
			newNode = insertHelper(node.getRight(), dollar);
		} else {
			newNode = insertHelper(node.getLeft(), dollar);
		}
		
		return newNode;
	}
	
//	private HeapNode findStartPos(HeapNode node)
//	{
//		if (node == null) {
//			return node;
//		} else if (node.getHeight(node) == 0) {
//			return node;
//		} else {
//			if (node.getRight() == null || 
//				node.getLeft().getHeight(node) >= node.getRight().getHeight(node)) {
//				return findStartPos(node.getLeft());
//			} else {
//				return findStartPos(node.getRight());
//			}
//		}
//	}
	
	public boolean search(Dollar dollar) 
	{
		
		
		return searchHelper(root, dollar);
	}
	
	private boolean searchHelper(HeapNode node, Dollar dollar) 
	{
		//Dollar curr = node.getDollar();
		
		if (node == null) {
			return false;
		} else if (dollar.isEqual(node.getDollar())) {
			return true;
		} else {
			return searchHelper(node.getLeft(), dollar) || searchHelper(node.getRight(), dollar);
		}
	}

	private HeapNode getLastNode()
	{
		return getLastNodeHelper(root);
	}
	
	private HeapNode getLastNodeHelper(HeapNode node)
	{		
		if (node == null) {
			return null;
		} else if (node.getLeft() == null) {
			//right node must also be null
			return node;
		} else if (node.getRight() == null) {
			return getLastNodeHelper(node.getLeft());
		} else if (node.getHeight(node.getLeft()) > node.getHeight(node.getRight()) &&
				   node.getRight().getLeft() != null) {
			return getLastNodeHelper(node.getRight());
		} else if (node.getHeight(node.getLeft()) == node.getHeight(node.getRight())) {
			return getLastNodeHelper(node.getRight());
		} else {
			return getLastNodeHelper(node.getLeft());
		}
	}
	
	public Dollar delete()
	{
		Dollar dollar;
		
		if (root == null) {
			return null;
		}
		
		dollar = root.getDollar();
		
		if (root.getLeft() == null &&
			root.getRight() == null) {
		    root = null;
		} else {
  		    HeapNode node = getLastNode();
		    swapNodes(node, root);
		    if (node == node.getParent().getLeft()) {
		    	node.getParent().setLeft(null);
		    } else {
		    	node.getParent().setRight(null);
		    }
		}
		
		percolateDown(root);
		
		return dollar;
	}
	
	/*
	 * Pre:
	 * title - String of specified traversal type.
	 * 
	 * Post:
	 * Prints a header for the specified traversal type in console and output file.
	 */
	private void printTitle(String title) 
	{
		System.out.println("\n" + title + ": ");
	}
	
	/*
	 * Pre:
	 * node - BSTNode that will be printed.
	 * 
	 * Post:
	 * Prints data of given node in console and output file.
	 */
	private void printNode(BSTNode node) 
	{
		System.out.print(node.getDollar() + " ");
	}

	/*
	 * Pre:
	 * ps - File-based PrintStream object for the output file.
	 * 
	 * Post:
	 * Prints the in-order traversal of the binary search tree.
	 */
	public void inOrderTraversal() 
	{
		printTitle("In-order traversal");
		inOrderTraversalHelper(root);
	}

	/*
	 * Pre:
	 * node - A reference to a node in the binary search tree or null.
	 * 
	 * Post:
	 * Prints the in-order traversal of the binary search tree rooted at the specified node.
	 */
	private void inOrderTraversalHelper(BSTNode node) 
	{
		/*
		Pseudocode:
		If the node is not null:
        	Call inOrderTraversalHelper(node's left child)
            printNode the node
            Call inOrderTraversalHelper(node's right child)
		 */
		
		if (node != null) {
			inOrderTraversalHelper(node.getLeft());
			printNode(node);
			inOrderTraversalHelper(node.getRight());
		}
	}

	/*
	 * Pre:
	 * ps - File-based PrintStream object for the output file.
	 * 
	 * Post:
	 * Prints the pre-order traversal of the binary search tree.
	 */
	public void preOrderTraversal() 
	{
		printTitle("Pre-order traversal");
		preOrderTraversalHelper(root);
	}

	/*
	 * Pre:
	 * node - A reference to a node in the binary search tree or null.
	 * 
	 * Post:
	 * Prints the pre-order traversal of the binary search tree rooted at the specified node.
	 */
	private void preOrderTraversalHelper(BSTNode node) 
	{
		/*
		Pseudocode:
		If the node is not null:
        	printNode the node
            Call preOrderTraversalHelper(node's left child)
            Call preOrderTraversalHelper(node's right child)
		 */
		
		if (node != null) {
			printNode(node);
			preOrderTraversalHelper(node.getLeft());
			preOrderTraversalHelper(node.getRight());
		}
	}

	/*
	 * Pre:
	 * ps - File-based PrintStream object for the output file.
	 * 
	 * Post:
	 * Prints the post-order traversal of the binary search tree.
	 */
	public void postOrderTraversal() 
	{
		printTitle("Post-order traversal");
		postOrderTraversalHelper(root);
	}

	/*
	 * Pre:
	 * node - A reference to a node in the binary search tree or null.
	 * 
	 * Post:
	 * Prints the post-order traversal of the binary search tree rooted at the specified node.
	 */
	private void postOrderTraversalHelper(BSTNode node) {
		/*
		Pseudocode:
		If the node is not null:
        	Call postOrderTraversalHelper(node's left child)
            Call postOrderTraversalHelper(node's right child)
			printNode the node
		 */
		
		if (node != null) {
			postOrderTraversalHelper(node.getLeft());
			postOrderTraversalHelper(node.getRight());
			printNode(node);
		}
	}

	/*
	 * Pre:
	 * ps - File-based PrintStream object for the output file.
	 * 
	 * Post:
	 * Prints the breadth-first traversal of the binary search tree.
	 */
	public void breadthFirstTraversal() {
		/*
		Pseudocode:
        PrintTitle "Breadth-first traversal"
        Call getHeight(root) to set the height of the tree
        From level 1 to i(the height of the tree):
            Call breadthFirstTraversalHelper(root,i)
		 */
		
		printTitle("Breadth-first traversal");
		int height = getHeight(root);
		
		for (int i = 1; i <= height; i++)
			breadthFirstTraversalHelper(root, i);
	}
	
	/*
	 * Post:
	 * Returns the height of the specified node.
	 */
	private int getHeight(BSTNode node) {
		/*
		Pseudocode:
		If the node is null:
			Return 0
		Call getHeight(node's left child) to set the height of the left subtree
        Call getHeight(node's right child) to set the height of the right subtree
		if left subtree's height > right subtree's height: 
            return left subtree's height + 1 
		otherwise:
			return right subtree's height + 1
		 */
		
		if (node == null)
			return 0;
		else {
			int lheight = getHeight(node.getLeft());
			int rheight = getHeight(node.getRight());

			if (lheight > rheight)
				return (lheight + 1);
			else
				return (rheight + 1);
		}
	}

	/*
	 * Pre:
	 * node - A reference to a node in the binary search tree or null.
	 * level - Positive integer indicating the level in the binary search tree.
	 * 
	 * Post:
	 * Prints the nodes at the specified level in the binary search tree rooted at the specified node.
	 */
	private void breadthFirstTraversalHelper(BSTNode node, int level) {
		/*
		Pseudocode:
		If the node is null:
        	Return; (exit out of method)
		If the level == 1:
			printNode the node
		If the level > 1:
			Call breadthFirstTraversalHelper(node's left child,level - 1)
			Call breadthFirstTraversalHelper(node's right child,level - 1)
		 */
		
		if (node == null)
			return;
		if (level == 1)
			printNode(node);
		else if (level > 1) {
			breadthFirstTraversalHelper(node.getLeft(), level - 1);
			breadthFirstTraversalHelper(node.getRight(), level - 1);
		}
	}
}
