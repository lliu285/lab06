package lab06;

public class HeapNode extends BSTNode
{
	private HeapNode parent;
	
	public HeapNode(Dollar dollar, HeapNode parent, int height) 
	{
		super(dollar);
		this.parent = parent;
	}
	
//	public int getHeight()
//	{
//		return height;
//	}
//	
//	public void setHeight(int height)
//	{
//		this.height = height;
//	}
	
	public HeapNode getParent()
	{
		return parent;
	}
	
	public void setParent(HeapNode parent)
	{
		this.parent = parent;
	}

}
