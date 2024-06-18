package lab06;

/*
 * Lab #6
 * Name: Lucia Liu
 * Due date: 6/23/24
 * Purpose: The purpose of this lab is to implement a MinHeap derived from a BSTNode.
 */
public class Main6 
{
	public static void main(String[] args) 
	{
		Dollar[] dollars = new Dollar[20];
		
		dollars[0] = new Dollar(1.00);
		dollars[1] = new Dollar(2.00);
		dollars[2] = new Dollar(3.00);
		dollars[3] = new Dollar(4.00);
		dollars[4] = new Dollar(5.00);
		dollars[5] = new Dollar(6.00);
		/*
		dollars[0] = new Dollar(57.12);
		dollars[1] = new Dollar(23.44);
		dollars[2] = new Dollar(87.43);
		dollars[3] = new Dollar(68.99);
		dollars[4] = new Dollar(111.22);
		dollars[5] = new Dollar(44.55);
		dollars[6] = new Dollar(77.77); 
		dollars[7] = new Dollar(18.36);
		dollars[8] = new Dollar(543.21); 
		dollars[9] = new Dollar(20.21);
		dollars[10] = new Dollar(345.67);
		dollars[11] = new Dollar(36.18);
		dollars[12] = new Dollar(48.48);
		dollars[13] = new Dollar(101.00);
		dollars[14] = new Dollar(11.00);
		dollars[15] = new Dollar(21.00);
		dollars[16] = new Dollar(51.00);
		dollars[17] = new Dollar(1.00);
		dollars[18] = new Dollar(251.00);
		dollars[19] = new Dollar(151.00);
		*/
		MinHeap heap = new MinHeap();
		/*
		for (int i = 0; i < 10; i++) {
			heap.insert(dollars[i]);
		}
		
		// traversals
		System.out.println("Heap after 10 insertions----------------------------------------");
		heap.breadthFirstTraversal(); 
		heap.inOrderTraversal(); 
		heap.preOrderTraversal();
		heap.postOrderTraversal();
		*/
		
		for (int i = 0; i < 6; i++) {
			heap.insert(dollars[i]);
		}
		/*
		for (int i = 0; i < 6; i++) {
			heap.insert(dollars[i]);
		}*/

		// traversals
		//System.out.println("\n\nHeap after 20 insertions----------------------------------------");
		heap.breadthFirstTraversal(); 
		heap.inOrderTraversal(); 
		heap.preOrderTraversal();
		heap.postOrderTraversal();
		
		heap.delete();
		
		System.out.println("\n\nHeap after delete----------------------------------------");
		heap.breadthFirstTraversal(); 
		//heap.inOrderTraversal(); 
		//heap.preOrderTraversal();
		//heap.postOrderTraversal();
		
		heap.delete();
		
		System.out.println("\n\nHeap after delete2----------------------------------------");
		heap.breadthFirstTraversal(); 
//		heap.inOrderTraversal(); 
//		heap.preOrderTraversal();
//		heap.postOrderTraversal();
		
		heap.delete();
		
		System.out.println("\n\nHeap after delete3----------------------------------------");
		heap.breadthFirstTraversal(); 
		
		System.out.println(heap.search(new Dollar(4.00)));
		System.out.println(heap.search(new Dollar(4.80)));
		
		
	}
}
