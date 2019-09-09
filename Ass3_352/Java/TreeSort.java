import java.util.Collection;

public class TreeSort 
{
	
	public static <E> void sortBS( Collection a){
		A3BSTree tree = new A3BSTree();
		sortBS(tree, a);
	}
	
	
	public static <E> void sortBS(A3BSTree tree, Collection a){
		tree.addAll(a);
		
		//TRit it = new TRit();
		
	}
	
	public static <E> void sortAVL( Collection a){
		A3AVLTree tree = new A3AVLTree();
		sortAVL(tree, a);
	}
	
	
	public static <E> void sortAVL(A3AVLTree tree, Collection a){
		tree.addAll(a);
		
		TRit it = new TRit();
		
		
	}
	
	
	
	
}
