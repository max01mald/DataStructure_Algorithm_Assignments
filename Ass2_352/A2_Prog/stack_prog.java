import java.util.*;
import java.lang.Math;
public class stack_prog {

	public static void main(String[] args)
	{
		Random rand = new Random();
		
		int count = 0;
		boolean t = false;
		int[] arr;
	
		
		
		while(count != 21)
		{
			int randi = rand.nextInt(10-0) +0;
			
			arr = new int[randi];
			
			for(int i=0; i<arr.length; i++)
			{
				if(i == arr.length-1)
				{
					arr[arr.length-1] = 0;
					break;
				}
				
				int randa = rand.nextInt(10-1) +1;
				arr[i] = randa;
			
			}
			
			t = call(arr);
			
			if (t)
			{
				count += 1;
				System.out.println("This is successful game: " + count);
				System.out.println(printArr(arr) + " this array is " + t);
			}
		}
		
		
		
	}
	
	
	private static String printArr(int[] arr)
	{
		String str ="";
		
		for (int i=0; i<arr.length; i++)
		{
			str += (arr[i] +", ");
		}
		
		return str;
	}
	
	
	private static boolean call(int[] arr)
	{
		boolean t = false;
		
		//int count = 0;
		//int max = (int)Math.pow(2.0, (arr.length-2));
		
		int[] arr2 = new int[arr.length];
		
		int pos = 0;
		int move = 0;
		
		int loop = 0;
		
		Stack<Integer> stack = new Stack<Integer>();
		
		
		while(loop == 0)
		{		
			
			
			if (pos > arr.length-1 || pos < 0)
			{
				loop = -1;
				t =false;
				break;
			}
			
			if(pos == arr.length-1)
			{
				loop = -1;
				t = true;
				break;
			}
			
			if (arr[pos]%2 == 0)
			{
				move = (arr[pos]/2);
			}
			else 
			{
				move = (arr[pos]/2) + 1;
			}
			//System.out.println(pos + move);
			if ((pos + move) < arr.length-1)
			{

				if (arr2[pos+move] == 2)
				{
					
					loop = -1;
					t = false;
					break;
				}
				else
				{
					arr2[pos+move] +=1;
					stack.push(pos+move);
				}
			}
			else if (pos + move == arr.length-1)
			{
				loop = -1;
				t = true;
				break;
			}
			
			
			if ((pos - move) > 0)
			{
				if (arr2[pos-move] == 2)
				{
					loop =-1;
					t = false;
					break;
				}
				else
				{	
					arr2[pos-move] +=1;
					stack.push(pos-move);
				}
			}
			else if (pos - move == arr.length-1)
			{
				loop = -1;
				t = true;
				break;
			}
			
			if(!stack.isEmpty())
			{
				pos = stack.pop();
			}
			else
			{
				loop =-1;
				t = false;
				break;
			}
		}
		return t;
	}
	
	
	
}
