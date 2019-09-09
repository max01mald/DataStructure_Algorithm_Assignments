import java.util.Random;
import java.lang.Math;


public class prog {
	
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
			int [] arr2 = new int[arr.length];
			
			for (int i=0; i<arr.length; i++)
			{
				arr2[i] = 0;
			}
			
			t = call(arr, 0, arr2);
			
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
	
	private static boolean call(int[] arr, int pos, int[] arr2)
	{
		
		int move =0;
		boolean t = false;
		
		
		
		if (pos == arr.length-1)
		{
			t= true;
			return t;
		}
		
		if (pos > arr.length-1 || pos < 0)
		{
			t =false;
			return t;
		}
		
		if (arr[pos] % 2 == 0)
		{
			
			move = arr[pos]/2;
			//System.out.println("even" + " " +arr[pos]);
		}
		else
		{
			move = (arr[pos]/2) +1;
			//System.out.println("odd" + " " + arr[pos]);
		}
		
		if ((pos + move) < arr.length-1 && (pos - move) > 0)
		{	
			if(arr2[move + pos] == 2)
			{
				t = false;
				return t;
			}
			else
			{	
				//System.out.println("right " + (move));
				arr2[pos+move] += 1;
				return call(arr, pos+move,arr2);
			}
		}
		else if ((pos + move) == arr.length-1)
		{
			//System.out.println("right " + (move));
			//System.out.println("RIGHTYou have reached the end");
			t= true;
			return t;
			
		}
		
		
		if ((pos + move) < arr.length-1 && (pos - move) > 0)
		{
			if(arr2[(move - pos)] == 2)
			{
				t = false;
				return t;
			}
			else
			{	
			//System.out.println("left" + " " + (move));
			arr2[(pos - move)] +=1;
			return call(arr, pos-move, arr2);
			}
		}
		else if ((pos + move) == arr.length-1)
		{
			//System.out.println("left" + " " + (move));
			//System.out.println("LEFTYou have reached the end");
			t=true;
			return t;
		}
		//System.out.println("call");
		return t;
		
		
	}
	
	
	
}
