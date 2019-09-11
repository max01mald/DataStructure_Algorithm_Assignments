package ass1_352;

import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Linear {

	   

	    public static double[] linearT(int k) {
	        //THis is to store the values 
	    	double[] a = new double[4];

	        double l4 = 0;
	        double l3 = 0;
	        double l2 = 0;
	        double l1 = 0;
	        
	        //this is the base case
	        if (k == 4){
	            l4 = 1;
	            l3 = 0;
	            l2 = 0;
	            l1 = 0;


	            a[0] = l4;
	            a[1] = l3;
	            a[2] = l2;
	            a[3] = l1;

	            return (a); // This will return (1,0,0,0)
	        } else {
	        	//this is the recursive call
	            a = linearT(k-1);
	            l4 = a[0];
	            l3 = a[1];
	            l2 = a[2];
	            l1 = a[3];


	            a[0] = l4 + l3 + l2 + l1;
	            a[1] = l4;
	            a[2] = l3;
	            a[3] = l2;
	            return a;
	        }


	    }

	    public static void main(String[] args) {
	    	
	    	//this prints out the results to the Out.txt file
	        try {
	            PrintWriter pw = new PrintWriter("Out.txt");

	            long startTime = System.nanoTime();

	            for (int i = 5; i <= 100; i += 5) 
	            {
	            	pw.println((linearT(i)[0]));
	            }
	            long endTime = System.nanoTime();
	            long elapsedTime = endTime - startTime;

	            //this prints the time in nano seconds
	            pw.println("Elapsed Time: " + elapsedTime);


	            pw.close();

	        } catch (FileNotFoundException e){
	            System.out.println("Something went wrong when trying to write!");
	        }
	    }
	}

	
	

