import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SortTester 
{
	public static void main(String[] args)
	{
		int N = 10;
		int num = 0;
		Random rand = new Random();
		List<Integer> arr = new ArrayList<Integer>();
		List<Integer> rev = new ArrayList<Integer>();
		
		try 
		{
            PrintWriter writer = new PrintWriter("testrun.txt");
            
			while(N< 1000001)
			{
				for(int i=0; i<N; i++)
				{
					num = rand.nextInt(N)+1;
					arr.add(num);
				}
				
				for(int i=N; i>0; i--)
				{
					rev.add(i);
				}
				
				long tStart = System.currentTimeMillis();
				
				TreeSort.sortBS(arr);
				
				long tEnd = System.currentTimeMillis();
				long tDelta = tEnd - tStart;
				double elapsedT = tDelta;
				
				writer.println("N = " + N +":");
				writer.println("BST\t\t" + elapsedT + " ms" );
				elapsedT = 0;
				
				tStart = System.currentTimeMillis();
				
				if(N<1000000)
				{
					TreeSort.sortAVL(arr);
				}
				
				tEnd = System.currentTimeMillis();
				tDelta = tEnd - tStart;
				elapsedT = tDelta;
				
				
				
				if(N<1000000)
				{
					writer.println("AVL\t\t" + elapsedT + " ms" );
				}
				else
				{
					writer.println("AVL\t\t" + "Too slow!" );
				}
				elapsedT = 0;
				
				tStart = System.currentTimeMillis();
				if(N<100000)
				{
					TreeSort.sortBS(rev);
				}
				tEnd = System.currentTimeMillis();
				tDelta = tEnd - tStart;
				elapsedT = tDelta;
				
				if(N<100000)
				{
					writer.println("BST(reverse)\t" + elapsedT + " ms" );
					
				}
				else
				{
					writer.println("BST(reverse)\t" + "Stack Overflow");
				}
				elapsedT = 0;
				
				tStart = System.currentTimeMillis();
				
				if(N<10000)
				{
					TreeSort.sortAVL(rev);
				}
				
				tEnd = System.currentTimeMillis();
				tDelta = tEnd - tStart;
				elapsedT = tDelta;
				
				if(N<10000)
				{
					writer.println("AVL(reverse)\t" + elapsedT + " ms");
				}
				else
				{
					writer.println("AVL(reverse)\t" + "Stack Overflow");
				}
				writer.println();
			
			
			N = N*10;
			
			arr.clear();
			rev.clear();
			}
			writer.close();
		}
		catch (FileNotFoundException e) 
		{
            System.out.println("Something went wrong when trying to write!");
        }
		System.out.println("done");
		
	}
}
