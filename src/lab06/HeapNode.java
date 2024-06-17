package lab06;

public class HeapNode extends BSTNode
{
	private BSTNode parent;
	
	public HeapNode(Dollar dollar, BSTNode parent) 
	{
		super(dollar);
		this.parent = parent;
	}
	
	public BSTNode getParent()
	{
		return parent;
	}
	
	public void setParent(BSTNode parent)
	{
		this.parent = parent;
	}

}
