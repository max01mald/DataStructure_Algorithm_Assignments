import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class A3BSTree<E extends Comparable<E>> implements Tree<E>
{
	private Node<E> root;
	
	public A3BSTree()
	{
		this.root = null;
	}
	
	public A3BSTree(Node<E> Root)
	{
		this.root = Root;
	}
	
	protected Node<E> getRoot()
	{
		return root;
	}
	
	private void setRoot(Node<E> Root)
	{
		this.root = Root;
	}
	
	public int size()
	{
		return sizer(0, null);
	}
	
	private int sizer(int n, Node<E> P)
	{
		Node<E> LC = null;
		Node<E> RC = null;
		
		if(this.getRoot() == null)
		{
			//System.out.println("noRoot");
			return n;
		}
		if(P == null)
		{
			
			P = this.getRoot();
			n += 1;
			//System.out.println("Root " + P.getData() + " " + n);
			
		}
		if(P != null)
		{
			LC = P.getLeftChild();
			
			if(LC != null)
			{
				n = sizer(n +1,LC);
				//System.out.println("LC " + LC.getData() + " " + n);
				
			}
			
			
			RC = P.getRightChild();
			
			
			if(RC != null)
			{
				n = sizer(n+1,RC);
				//System.out.println("RC " + RC.getData() + " " + n);
				
			}
			
		}
		
		return n;
	}
	
	public void add(E e)
	{	
		
		Node N = new Node(e);
		
		adder(null, N);
		
	}
	
	private void adder(Node<E> P, Node<E> N)
	{

		Node<E> LC = null;
		Node<E> RC = null;
		
		if(P == null)
		{
			P = this.root;
			if (P == null)
			{
				setRoot(N);
				return;
			}
		}
		
	
		if(N.getData().compareTo(P.getData())<=0)
		{
			if(P.hasLChild())
			{
				LC = P.getLeftChild();
				adder(LC, N);
			}
			else
			{
				P.setLeftChild(N);
			}
		}
		
		else if(N.getData().compareTo(P.getData())>0)
		{
			if(P.hasRChild())
			{
				RC = P.getRightChild();
				adder(RC, N);
			}
			else
			{
				P.setRightChild(N);
			}
			
		}
	}
	
	public void addAll(Collection<? extends E> e)
	{
		
		for(int i=0; i<e.size(); i++)
		{
			add(((List<E>) e).get(i));
		}
	}
	
	public int height()
	{
		return heighter(null, 1, 1);
	}
	
	private int heighter(Node<E> P, int n, int max)
	{
		Node<E> LC = null;
		Node<E> RC = null;
		
		if(P == null)
		{
			P = this.getRoot();
		}
		
		if(P == null)
		{
			return -1;
		}
		if(max < n)
		{
			max = n;
		}

		LC = P.getLeftChild();
		
		if(LC != null)
		{
			max = heighter(LC,n+1, max);

		}
		
		
		RC = P.getRightChild();
		
		
		if(RC != null)
		{
			max = heighter(RC,n+1, max);
			
		}
		
		return max;
	}
	
	public boolean remove(Object N)
	{
		Comparable<E> Ndat = null;
		boolean t = false;
		
		if(N instanceof Comparable<?>)
		{
			Node<E> temp = new Node((Comparable) N);
			
			temp = finder(null, temp.getData());
			Ndat =  temp.getData();
			t = remover(temp, Ndat, null);
		
		}

		System.out.println("Remove Retruns " + t);
		
		return t;
	}
	
	private boolean remover(Node<E> N, Comparable<E> Ndat, Node<E> P)
	{
		Node<E> LC = null;
		Node<E> RC = null;
		Node<E> temp = null;
		Comparable Nkey = null;
		TRit it = new TRit();
		
		if(P == null)
		{
			P = this.getRoot();
			if(P == null)
			{
				return false;
			}
		}
		
		
		if(P.isEquals(N))
		{
			if(!P.hasLChild() && !P.hasRChild())
			{
				P=null;
				delete(P, P.getData(), null);
				return true;
			}
			else
			{
				it = this.iterator();
				while(it.hasNext())
				{
					if(it.next() == P.getData())
					{
						Nkey = it.next();
					}
				}
				
				temp = finder(P, Nkey);
				
				if(temp.isEquals(P))
				{
					while(it.hasNext())
					{
						if(it.next() == P.getLeftChild().getData())
						{
							Nkey = it.next();
						}
					}
					temp = finder(P.getLeftChild(), Nkey);
				}
				
				
				if(!temp.hasLChild() && !temp.hasRChild())
				{
					delete(temp,Nkey,LC);
					P.setData((E) Nkey);
					
				}
				else
				{
					remover(temp, temp.getData(), P);
					P.setData((E) Nkey);
					return true;
				}
				
			}
		}
		
		if(N.getData().compareTo(P.getData())<=0)
		{
			LC = P.getLeftChild();
			
			if(LC != null)
			{
				if(LC.isEquals(N))
				{
					if(LC.hasLChild()||LC.hasRChild())
					{
						
						it = this.iterator();
						while(it.hasNext())
						{
							if(it.next() == LC.getData())
							{
								 Nkey = it.next();
							}
						}
						
						temp = finder(LC, Nkey);
						
						if(!temp.hasLChild() && !temp.hasRChild())
						{
							delete(temp,Nkey,LC);
							LC.setData((E) Nkey);
						}
						else
						{
							remover(temp, temp.getData(), LC);
							LC.setData((E) Nkey);
							return true;
						}
					}
					else
					{
						delete(LC, LC.getData(), P);
						return true;
					}
					
				}
				else
				{
					return remover(N, Ndat, LC);
				}
				
			}
			else return false;
			
		}
		
		if(N.getData().compareTo(P.getData())>0)
		{
			RC = P.getRightChild();
			
			if(RC != null)
			{
				if(RC.isEquals(N))
				{
					if(RC.hasRChild() || RC.hasLChild())
					{	
						it = this.iterator();
						while(it.hasNext())
						{
							if(it.next() == RC.getData())
							{
								 Nkey = it.next();
							}
						}
						
						temp = finder(RC, Nkey);
						if(!temp.hasLChild() && !temp.hasRChild())
						{
							delete(temp,Nkey,RC);
							RC.setData((E) Nkey);
						}
						else
						{
							remover(temp, temp.getData(), RC);
							RC.setData((E) Nkey);
							return true;
						}
						
					}
					else
					{
						delete(RC, RC.getData(), P);
						return true;
					}
					
				}
				else
				{
					System.out.println("right2");
					return remover(N, Ndat, RC);
				}
				
			}
			else return false;
			
		}

		return false;
	}
	
	private void delete(Node<E> N, Comparable<E> Ndat, Node<E> P)
	{
		Node<E> LC = null;
		Node<E> RC = null;
		
		System.out.println("delete");
		
		if(P == null)
		{
			P = this.getRoot();
			
			if(P== null)
			{
				return;
			}
		}
		
		if(N.getData().compareTo(P.getData())<=0)
		{
			if(P.hasLChild())
			{
				if(P.getLeftChild().isEquals(N))
				{
					P.setLeftChild(null);
				}
				else
				{
					delete(N,Ndat,P.getLeftChild());
				}
			}
		}
		
		if(N.getData().compareTo(P.getData())>0)
		{
			if(P.hasRChild())
			{
				
				if(P.getRightChild().isEquals(N))
				{
					P.setRightChild(null);
				}
				else
				{
					delete(N,Ndat,P.getRightChild());
				}
				
			}
		}
		
	}
	
	private Node<E> finder(Node<E> P, Comparable<E> Nkey)
	{
		Node<E> LC = null;
		Node<E> RC = null;
		
		System.out.println("finder");
		
		if(P == null)
		{
			P = this.getRoot();
		}
		
		if(P.getData() == Nkey)
		{
			return P;
		}
		if(Nkey.compareTo(P.getData())<=0)
		{
			LC = P.getLeftChild();
			return finder(LC, Nkey);
		}
		if(Nkey.compareTo(P.getData())>0)
		{
			RC = P.getRightChild();
			return finder(RC, Nkey);
		}
		
		return null;
		
		
	}
	
	public TRit<E> iterator()
	{
		TRit h = new TRit(this);
		
		return h;
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		Node<Integer> n1 = new Node(5);
		Node<Integer> n2 = new Node(6);
		Node<Integer> n3 = new Node(7);
		Node<Integer> n4 = new Node(8);
		Node<Integer> n5 = new Node(20);
		Node<Integer> n6 = new Node(30);
		
		
		A3BSTree t1 = new A3BSTree(n1);
		A3BSTree t2 = new A3BSTree(n2);
		A3BSTree t3 = new A3BSTree(n4);
		
		A3BSTree t4 = new A3BSTree();
		
		List<Integer> l = new ArrayList<Integer>();
		l.add(10);
		l.add(18);
		l.add(2);
		l.add(2);
		l.add(4);
		l.add(5);
		l.add(6);
		l.add(7);
		l.add(10);
		l.add(9);
		l.add(3);
		l.add(11);
		l.add(13);
		l.add(14);
		l.add(15);
		l.add(16);
		
		
		
		t4.addAll(l);
		
		
		
		TRit it = new TRit();
		
		it = t4.iterator();
		
		
		
		while(it.hasNext())
		{
			System.out.print(it.next()+ ", ");
		}
		System.out.println();
		
		t4.remove(10);
		
		it = t4.iterator();
		
		System.out.println();
		
		while(it.hasNext())
		{
			System.out.print(it.next()+ ", ");
		}
		
		System.out.println("test " + t4.size());
		
		
		
	}


	

}
