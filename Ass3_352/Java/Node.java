
public class Node<E extends Comparable<E>> extends Object 
{
	private int key;
	private E data;
	private Node<E> right;
	private Node<E> left;
	
	public Node()
	{
		this.key = 0;
		this.data = null;
		this.right = null;
		this.left = null;
	}
	
	protected Node(E Data)
	{	
		this.key = 0;
		this.data = Data;
		this.right = null;
		this.left = null;
	}
	
	protected Node(E Data, Node<E> Left, Node<E> Right)
	{
		this.key = 0;
		this.data = Data;
		this.left = Left;
		this.right = Right;
		
		
	}
	
	protected int getKey()
	{
		return key;
	}
	
	protected E getData()
	{
		return data;
		
	}
	
	protected Node<E> getLeftChild()
	{
		return left;
	}
	
	protected Node<E> getRightChild()
	{
		return right;
	}
	
	protected void setKey(int k)
	{
		this.key = k;
	}
	
	protected void setData(E Data)
	{
		this.data = Data;
		
	}
	
	protected void setNode(Node<E> N)
	{
		this.key = N.key;
		this.data = N.data;
		this.left = N.left;
		this.right = N.right;
	}
	
	protected void setLeftChild(Node<E> Left)
	{
		this.left = Left;
	}
	
	protected void setRightChild(Node<E> Right)
	{
		this.right = Right;
	}
	
	protected boolean hasRChild()
	{
		if(this.getRightChild() != null)
		{
			return true;
		}
		
		return false;
	}
	
	protected boolean hasLChild()
	{
		if(this.getLeftChild() != null)
		{
			return true;
		}
		
		return false;
	}
	
	protected boolean isEquals(Node<E> N)
	{
		if(this.data == N.data && this.getLeftChild() == N.getLeftChild() && this.getRightChild() == N.getRightChild())
		{
			return true;
		}
		return false;
	}
	
	
	

}
