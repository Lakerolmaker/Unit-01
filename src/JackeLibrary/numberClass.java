package JackeLibrary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class numberClass {

	Random rand = new Random(); 
	
	public boolean isInt(String input) {
		
		boolean outcome = true;
		
		try {
			int test = Integer.valueOf(input);
		} catch (Exception e) {
			outcome = false;
		}
		return outcome;
	}

	public int toInt(String input) {
		try {
			return Integer.valueOf(input);
		} catch (Exception e) {}
		return -1;
	}
	
	public int randomInt(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}

	public static int[] reverse(int[] array) {
		for(int i=0; i < array.length/2; i++){ 
			int temp = array[i];
			array[i] = array[array.length -i -1]; 
			array[array.length -i -1] = temp; 
		}
		return array;
    }
	
	public static String[] reverse(String[] array) {
		for(int i=0; i < array.length/2; i++){ 
			String temp = array[i];
			array[i] = array[array.length -i -1]; 
			array[array.length -i -1] = temp; 
		}
		return array;
    }
}
