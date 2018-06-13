package programs;

public class UselessFunctions {

public static int[] withoutTen(int[] nums) {
		
		int returned[] = new int[nums.length];
		int index = 0;
		for(int i = 0 ; i < nums.length; i++) {
			
			if(nums[i] != 10) {
				returned[index] = nums[i];
				index++;
			}
			
	}
		
		return returned;
	}
	
	public static boolean haveThree(int[] nums){
		boolean outcome = false;
		int count = 0;
		for(int i = 0; i < nums.length; i++) {
			
			
			if(nums[i] == 3) {
				int finder = 0;
				try {
					if(nums[i + 1] != 3) 
						finder++;
				} catch (Exception e) {
					finder++;
				}
				
				try {
					if(nums[i - 1] != 3)  
						finder++;
				} catch (Exception e) {
					finder++;
				}
				
				if(finder == 2) {
					count++;
				}else {
					count = 0;
				}
			 }
		
		}
		
		if(count == 3) {
			outcome = true;
		}
		
		return outcome;
	}
	
	 public static void oddEven(int n){
         int sumEven=0;
         int sumOdd=0;
         for (int i = 0; i < n + 1; i++) {
        	 	if(i % 2 == 0) {
        	 			sumEven += i;
        	 	}else {
        	 			sumOdd += i;
        	 	}
        
         }
         System.out.println("The sum of even numbers is "+ sumEven);
         System.out.println("The sum of odd numbers is "+ sumOdd);
}
	
	
	
}
