import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class A3AVLTree<E extends Comparable<E>> implements Tree<E>
{
	
private Node<E> root;
	
	public A3AVLTree()
	{
		this.root = null;
	}
	
	public A3AVLTree(Node<E> Root)
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
		Node GGP = null;
		Node GP = null;
		Node P = null;
		Node C = null;
		
		Node N = new Node(e);
		
		adder(null, N, null, null);
		
		if(Math.abs(Rheight(this.root) - Lheight(this.root))>1)
		{
			GP = this.root;
			if(Rheight(GP)>Lheight(GP))
			{
				P = GP.getRightChild();
			}
			else
			{
				P = GP.getLeftChild();
			}
			
			if(Rheight(P)>Lheight(P))
			{
				C = P.getRightChild();
			}
			else
			{
				C = P.getLeftChild();
			}
			
			rebalance(GGP, GP, P, C);
			
		}
		
	}
	
	private void adder(Node<E> P, Node<E> N, Node<E> GP, Node<E> GGP)
	{

		Node<E> LC = null;
		Node<E> RC = null;
		Node<E> C = null;
		
		
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
				
				adder(LC, N, P, GP);
			}
			else
			{
				P.setLeftChild(N);
	
				if(GP != null)
				{
					if(Math.abs(Rheight(GP) - Lheight(GP))>1)
					{
						
						if(Rheight(GP)>Lheight(GP))
						{
							P = GP.getRightChild();
						}
						else
						{
							P = GP.getLeftChild();
						}
						
						if(Rheight(P)>Lheight(P))
						{
							C = P.getRightChild();
						}
						else
						{
							C = P.getLeftChild();
						}
						
						rebalance(GGP, GP, P, C);
						
					}
				}
				
				
			}
		}
		
		else if(N.getData().compareTo(P.getData())>0)
		{
			if(P.hasRChild())
			{
				RC = P.getRightChild();
				adder(RC, N, P, GP);
			}
			else
			{
				
				P.setRightChild(N);
				
				//System.out.println("new right child " + N.getData());
				if(GP != null)
				{
					if(Math.abs(Rheight(GP) - Lheight(GP))>1)
					{
						if(Rheight(GP)>Lheight(GP))
						{
							P = GP.getRightChild();
						}
						else
						{
							P = GP.getLeftChild();
						}
						
						if(Rheight(P)>Lheight(P))
						{
							C = P.getRightChild();
						}
						else
						{
							C = P.getLeftChild();
						}
						
						rebalance(GGP, GP, P, C);
						
					}
				}
				
			}
			
		}
		
		//System.out.println("End tree after add RC " + Rheight(this.root) + " LC " + Lheight(this.root));
	}
	
	
	
	private void rebalance(Node<E> GGP, Node<E> GP, Node<E> P, Node<E> C)
	{
		
		if(GP.getLeftChild()==P)
		{
			if(P.getRightChild()==C)
			{
				//System.out.println("Case2 Left");
				GP.setLeftChild(null);
				P.setRightChild(C.getLeftChild());
				C.setLeftChild(P);
				GP.setLeftChild(C);
				
				
				rebalance(GGP,GP, C, P);
			}
		}
		
		if(GP.getRightChild() == P)
		{
			if(P.getLeftChild() == C)
			{
				//System.out.println("Case2 Right");
				GP.setRightChild(null);
				P.setLeftChild(C.getRightChild());
				C.setRightChild(P);
				GP.setRightChild(C);
				
				rebalance(GGP, GP, C, P);
			}
		}
		
		if(GGP == null)
		{

			if(GP.getLeftChild() == P)
			{
				if(P.getLeftChild() == C)
				{
					//System.out.println("Case1 GGP Left");
					GP.setLeftChild(null);
					GP.setLeftChild(P.getRightChild());
					P.setRightChild(GP);
					
					//System.out.println("P "+ P.getData() +" LC " + P.getLeftChild().getData() + " RC " + P.getRightChild().getData());
					
					setRoot(P);
				}
			}
			
			if(GP.getRightChild() == P)
			{
				if(P.getRightChild() == C)
				{
					//System.out.println("Case1 GGP Right");
					GP.setRightChild(null);
					GP.setRightChild(P.getLeftChild());
					P.setLeftChild(GP);
					//System.out.println("P "+ P.getData() + " LC " + P.getLeftChild().getData() + " RC " + P.getRightChild().getData());
					
					setRoot(P);
				}
			}
			
			
		}
		else
		{
			if(GGP.getLeftChild() == GP)
			{
				if(GP.getLeftChild() == P)
				{
					if(P.getLeftChild() == C)
					{
						//System.out.println("Case 1 Left");
						GP.setLeftChild(null);
						GP.setLeftChild(P.getRightChild());
						P.setRightChild(GP);
						
						//System.out.println("P "+ P.getData() +" LC " + P.getLeftChild().getData() + " RC " + P.getRightChild().getData());
						
						GGP.setLeftChild(P);
					}
				}
			}
			if(GGP.getRightChild() == GP)
			{
				if(GP.getRightChild() == P)
				{
					if(P.getRightChild() == C)
					{
						//System.out.println("Case1 Right");
						GP.setRightChild(null);
						GP.setRightChild(P.getLeftChild());
						P.setLeftChild(GP);
						//System.out.println("P "+ P.getData() + " LC " + P.getLeftChild().getData() + " RC " + P.getRightChild().getData());
						
						GGP.setRightChild(P);
					}
				}
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
	
	private int Rheight(Node<E> P)
	{
		if(P == null)
		{
			return 0;
		}
		if(P.hasRChild())
		{
			return heighter(P.getRightChild(),1,1);
		}
		return 0;
	}
	
	private int Lheight(Node<E> P)
	{
		if(P == null)
		{
			return 0;
		}
		if(P.hasLChild())
		{
			return heighter(P.getLeftChild(),1,1);
		}
		return 0;
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
					//System.out.println("right2");
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
		
		A3AVLTree t4 = new A3AVLTree();
		A3BSTree t = new A3BSTree();
		
		List<Integer> rev = new ArrayList<Integer>();
		int N = 100000;
		for(int i=N; i>0; i--)
		{
			rev.add(i);
		}
		
		t4.addAll(rev);
		t.addAll(rev);
		
		
		TRit it = new TRit();
		
		/*it = t4.iterator();
		
		
		
		while(it.hasNext())
		{
			System.out.print(it.next()+ ", ");
		}
		System.out.println();
		
		//t4.remove(10);
		
		it = t4.iterator();
		
		System.out.println();
		
		while(it.hasNext())
		{
			System.out.print(it.next()+ ", ");
		}*/
		
		System.out.println("AVL Height " + t4.height());
		System.out.println("AVL RHeight " + t4.Rheight(t4.root.getRightChild()));
		System.out.println("AVL LHeight " + t4.Lheight(t4.root.getLeftChild()));
		System.out.println("AVL size " + t4.size());
		//System.out.println("BS Height " + t.height());
		//System.out.println("BS size " + t.size());
	}

}
