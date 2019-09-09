import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TRit<E extends Comparable<E>> implements Iterator<E>
{
	private List<E> list;
	private int size;
	private int index;
	
	protected TRit()
	{
		this.list = null;
		this.size = 0;
		this.index = 0;
	}
	
	protected TRit(A3BSTree<E> Tree)
	{
		this.list = inOrder(Tree);
		this.size = list.size();
		this.index = 0;
	}
	
	protected TRit(A3AVLTree<E> Tree)
	{
		this.list = inOrder(Tree);
		this.size = list.size();
		this.index = 0;
	}
	
	public boolean hasNext()
	{
		if(index+1 > size)
		{
			index = 0;
			return false;
		}
		
		return true;
	}
	
	public E next()
	{
		E temp = null;
		
		if(hasNext())
		{
			temp = this.list.get(index);
			index += 1;
			if(index == size)
			{
				//index = 0;
			}
			
		}
		
		return temp;
	}
	
	private List<E> inOrder(A3BSTree Tree)
	{
		List<E> temp = new ArrayList<E>();
		Node<E> P = new Node();

		P = Tree.getRoot();
		
		temp = Visitor(P, temp);

		return temp;
	}
	
	private List<E> inOrder(A3AVLTree Tree)
	{
		List<E> temp = new ArrayList<E>();
		Node<E> P = new Node();

		P = Tree.getRoot();
		
		temp = Visitor(P, temp);

		return temp;
	}
	
	private List<E> Visitor(Node<E> P, List<E> L)
	{
		Node<E> LC = new Node();
		Node<E> RC = new Node();
		
		
		if(P == null)
		{
			
			return L;
			
		}
		if(P != null)
		{
			LC = P.getLeftChild();
			
			if(LC != null)
			{
				
				L = Visitor(LC,L);
			}
			
		
			L.add(P.getData());
			
			
			RC = P.getRightChild();
			
			
			if(RC != null)
			{
				L = Visitor(RC,L);
				
			}
			
		}
		
		return L;
	}

	public int size()
	{
		return size;
	}
	
}
