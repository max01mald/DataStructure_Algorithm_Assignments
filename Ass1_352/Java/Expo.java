package ass1_352;

import java.io.*;
import java.util.Scanner;

public class Expo {

	
	    public static int exponentialT(int k) {
	       //this is the base case
	    	if (k == 4)
	            return 1;
	    	//this is if the request number will return a 0
	        else if (k <= 3)
	            return 0;
	        else
	        	//this is the recursive step calculating all the 4 numbers by recursion
	            return exponentialT(k - 1) + exponentialT(k - 2) + exponentialT(k - 3) + exponentialT(k - 4);
	    }

	    public static void main(String[] args) {
	    	//this prints the results to Out2.text
	        try {
	            PrintWriter writer = new PrintWriter("Out2.txt");


	            long startTime = System.nanoTime();
	            for (int i = 5; i <= 30; i += 5) {
	                writer.println(exponentialT(i));
	            }
	            long endTime = System.nanoTime();
	            long elapsedTime = endTime - startTime;

	            //this pastes the elapsed time in nano seconds
	            writer.println("Elapsed Time: " + elapsedTime);
	            writer.close();

	        } catch (FileNotFoundException e) {
	            System.out.println("Something went wrong when trying to write!");
	        }

	    }

	

	
	
	
}
