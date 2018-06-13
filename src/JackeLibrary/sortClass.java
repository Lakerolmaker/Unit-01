package JackeLibrary;

import java.util.Arrays;

public class sortClass {

	private mergesortClass mergersorter = new mergesortClass();
	
	public int[] bubbleSort(int[] array) {  
        int lenght = array.length;  
        
        for( int x = 0; x < lenght; x++ ){  
        		for( int y = 1; y < ( lenght - x); y++ ){  
        			if(array[ y - 1] > array[y] ){  
        				swap(array , y - 1 , y );	
        			}               
        		}  
        } 
         return array;
	}
	
	public int[] selectionSort(int[] array){
		
		for(int x = 0 ;  x < array.length ; x++) {
			for(int i = x ;  i < array.length; i++) {
				if( array[x] > array[i])  {
					swap(array , x , i);	
				}	
			}	
		}
	
		return array;    
    }
	
	public int[] insertionSort(int[] array) {
	  for(int i = 1; i < array.length; ++i){
      		int index = i;
         for(int z = i - 1; z >= 0; z--) {
      	  
      	   		if( array[z] > array[index]) {
      	   			swap(array , index , z);
      	   			index--;
      	   		}else {	
      	   			z = -1;
      	   		}
      	   
         }
           
      }
      return array;
    }
	
	public int[] mergesort(int[] array) {
		return mergersorter.sort(array);
	}
	
	public int[] quickSort(int[] array) {
		return doQuickSort(array ,0, array.length - 1);
	}
	
	private int[] doQuickSort(int[] array, int begin , int end)  {
	        // Base case.
	        if (begin >= end) return null;

	        // Partition the array.
	        int pivot = array[begin + (end-begin)/2];

			  int a = begin;
			  int b = end;
		  
		        while (a <= b) {
		          
		            while (array[a] < pivot) {
		                a++;
		            }
		       
		            while (array[b] > pivot) {
		                b--;
		            }

		            if (a <= b) {
		                swap(array,a, b);
		                a++;
		                b--;
		            }
		            
		            // Recursion
			        if (begin < b)
			        	doQuickSort(array ,begin, b );
			        if (a < end)
			        	doQuickSort(array, a, end );
		            
		        }
		       
		       return array;    
	    }
	
	private void swap(int[] array , int x, int y) {
		 int temp = array[x];
		 array[x] = array[y];
		 array[y] = temp; 
	}
	
	public void test() {
		int[] input1 = { 1, 6, 5 ,2, -9 ,7 ,3, 0 ,8 ,7, -5, 3 ,53, 2, 24, -454, 3, 232 ,1, -2 };
	   int[] output1 = { -454 ,-9, -5, -2, 0 ,1, 1, 2, 2, 3, 3, 3 ,5, 6 ,7, 7 ,8, 24, 53, 232 };

		
		int[] input2 = { -20 ,-54 ,-17 ,-57 ,35 ,47 ,74 ,-72 ,37, 57 ,69, -94, 34, 12 ,-72 ,-31, -47 ,-93, -2, -41, -31 ,-18 ,-14, 77 ,-5, -68, -94, 29, -16, 31 };
	   int[] output2 = { -94 ,-94 ,-93 ,-72 ,-72 ,-68 ,-57 ,-54, -47 ,-41 ,-31, -31, -20, -18, -17, -16 ,-14, -5, -2, 12 ,29, 31, 34, 35 ,37, 47, 57, 69, 74, 77 };

		
		int[] input3= { -45 ,-98, 54, 93, 392, 1, 94, -3, -2, 5, 29, 90 ,-100, 23, 200, 34, 11, 23, 4, -999 ,-1 ,33, 77, 88, 99 ,92 ,83, -54, -81, 819};
	  int[] output3 = { -999 ,-100, -98, -81, -54, -45, -3, -2 ,-1, 1, 4, 5, 11, 23, 23, 29, 33, 34, 54, 77, 83, 88, 90 ,92, 93, 94 ,99, 200, 392, 819};

		long startTime = System.nanoTime();
		
		if(Arrays.equals(mergesort(input1), output1)) {
			System.out.print("Passed test 1 \n" );
		}else {
			System.out.print("Failed test 1 \n");
		}
		
		if(Arrays.equals(mergesort(input2), output2)) {
			System.out.print("Passed test 2 \n");
		}else {
			System.out.print("Failed test 2 \n");
		}
		
		if(Arrays.equals(mergesort(input3), output3)) {
			System.out.print("Passed test 3 \n");
		}else {
			System.out.print("Failed test 3 \n");
		}

	}
	
}
