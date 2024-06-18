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
	 * Pre:
	 * node - HeapNode that is being percolated up
	 * 
	 * Post:
	 * Reorganizes heap starting from the given node
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
	 * Pre:
	 * node - HeapNode that is being percolated down
	 * 
	 * Post:
	 * Reorganizes heap starting from the given node
	 */
	private void percolateDown(HeapNode node)
	{
		while (node.getLeft() != null) {
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
	 * node - Node in the heap that is checked to see if a new node can be added
	 * dollar - Dollar value of the node that is being added
	 * 
	 * Post:
	 * A HeapNode with the specified dollar value is inserted 
	 * at the specified node or the node's child if conditions are suitable.
	 * Otherwise, method is called again for a child node.
	 */
	private HeapNode insertHelper(HeapNode node, Dollar dollar) 
	{
		/*
		 * Pseudocode:
		 * If node is null, set the root node to the new node
		 * If node's left child is null, add the new node as node's left child
		 * If node's right child is null, add the new node as node's right child
		 * If node's left child's height is greater than or equal to node's right child's height, 
		 * 		call the function again for the right child
		 * If node's right child's height is greater than node's right child's height, 
		 * 		call the function again for the left child
		 */
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
		} else if (node.getHeight(node.getLeft()) >= node.getHeight(node.getRight())) {
			newNode = insertHelper(node.getRight(), dollar);
		} else {
			newNode = insertHelper(node.getLeft(), dollar);
		}
		
		return newNode;
	}
	
	/*
	 * Pre:
	 * dollar - Dollar value that is searched for in heap
	 * 
	 * Post:
	 * Returns true if a node containing the specified dollar value is found.
	 * Returns false otherwise.
	 */
	public boolean search(Dollar dollar) 
	{
		return searchHelper(root, dollar);
	}
	
	/*
	 * Pre:
	 * node - HeapNode where dollar value is being searched for
	 * dollar - Dollar value that is searched for in heap
	 * 
	 * Post:
	 * Returns false if node is null.
	 * Returns true if dollar is equal to the node's dollar value.
	 * Otherwise, the method is called again to check the node's children.
	 */
	private boolean searchHelper(HeapNode node, Dollar dollar) 
	{
		if (node == null) {
			return false;
		} else if (dollar.isEqual(node.getDollar())) {
			return true;
		} else {
			return searchHelper(node.getLeft(), dollar) || searchHelper(node.getRight(), dollar);
		}
	}

	/*
	 * Post:
	 * The last inserted HeapNode in the MinHeap is returned.
	 */
	private HeapNode getLastNode()
	{
		return getLastNodeHelper(root);
	}
	
	/*
	 * Pre:
	 * node - Starting HeapNode object from which finding the last inserted node.
	 * 
	 * Post:
	 * The last inserted HeapNode object after the input HeapNode object is returned.
	 */
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
	
	/*
	 * Post:
	 * The minimum HeapNode object is deleted from the MinHeap and the contained Dollar
	 * object is returned.
	 */
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
	 * Prints a header for the specified traversal type.
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
		System.out.print("$" + node.getDollar() + " ");
	}

	/*
	 * Post:
	 * Prints the in-order traversal of the binary search tree.
	 */
	public void inOrderTraversal() 
	{
		printTitle("\nIn-order traversal");
		inOrderTraversalHelper(root);
	}

	/*
	 * Pre:
	 * node - A BSTNode reference or null.
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
	 * Post:
	 * Prints the pre-order traversal of the heap.
	 */
	public void preOrderTraversal() 
	{
		printTitle("\nPre-order traversal");
		preOrderTraversalHelper(root);
	}

	/*
	 * Pre:
	 * node - A BSTNode reference or null.
	 * 
	 * Post:
	 * Prints the pre-order traversal rooted at the specified node.
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
	 * Post:
	 * Prints the post-order traversal of the heap.
	 */
	public void postOrderTraversal() 
	{
		printTitle("\nPost-order traversal");
		postOrderTraversalHelper(root);
	}

	/*
	 * Pre:
	 * node - A BSTNode reference or null.
	 * 
	 * Post:
	 * Prints the post-order traversal rooted at the specified node.
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
	 * Post:
	 * Prints the breadth-first traversal of the heap.
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
	 * Prints the nodes at the specified level in the heap rooted at the specified node.
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
