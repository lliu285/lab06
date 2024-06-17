package lab06;

public class MinHeap
{
	//private HeapNode[] heap;
	private HeapNode root;
	private int height;
	
	public MinHeap()
	{
		root = null;
		height = 0;
	}
	
	private void percolateUp()
	{
		
		/* note: this is for max heap
		while (nodeIndex > 0) {
		      parentIndex = (nodeIndex - 1) / 2
		      if (heapArray[nodeIndex] <= heapArray[parentIndex])
		         return
		      else {
		         swap heapArray[nodeIndex] and heapArray[parentIndex]
		         nodeIndex = parentIndex
		      }
	   	}
		 */
	}
	
	private void percolateDown()
	{
		
	}
	
	private void swap(HeapNode node1, HeapNode node2)
	{
		BSTNode temp = node1;
		node1.setParent(node2);
		node2.setLeft(temp); // setRight??
	}
	
	public void insert(Dollar dollar) 
	{
		HeapNode node = root;
		
		while (true) {
			
		}
	}
	
	private boolean insertHelper(HeapNode node) 
	{
		if (node.getDollar().isGreater(node.getParent().getDollar())) {
			swap(node, node.getParent());
		}
		
		return false;
	}
	
	private BSTNode findEmptyPos(BSTNode node)
	{
		if (node.getHeight() == 0) {
			return node;
		} else {
			if (node.getLeft().getHeight() >= node.getRight().getHeight()) {
				return findEmptyPos(node.getLeft());
			} else {
				return findEmptyPos(node.getRight());
			}
		}
	}
	
	public boolean search(Dollar dollar) 
	{
		return searchHelper(root, dollar);
	}
	
	private boolean searchHelper(BSTNode root, Dollar dollar) 
	{
		Dollar curr = root.getDollar();
		
		if (dollar.isGreater(curr)) {
			return false;
		} else if (dollar.isEqual(curr)) {
			return true;
		} else {
			if (!searchHelper(root.getLeft(), dollar)) {
				return searchHelper(root.getRight(), dollar);
			}
			return true;	
		}
	}
	
	public void delete(Dollar dollar)
	{
		
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
