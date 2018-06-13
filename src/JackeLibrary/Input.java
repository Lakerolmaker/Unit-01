package JackeLibrary;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import com.sun.management.OperatingSystemMXBean; 

public class Input {
	
private String choice;
private String userString;
private int userInt;

//: The users input
public String userChoice;


public void print(Object o) {
    System.out.print(o); 
}

public void cls() {
	
   for(int i = 0;  i < 100; i++) {
	   print("\n");
   }  
}

public void clear() {
	
	   for(int i = 0;  i < 100; i++) {
		   print("\n");
	   }  
}



public int random(int min , int max) {
	Random rand = new Random(); 
	int random = rand.nextInt((max - min) + 1) + min;
	return random;
}





public void course(){

	String one = "|----------------|";
	String two = "| Code   |course |";
  String three = "|----------------|";
   String four = "| DIT042 | 001   |";
   String five = "|----------------|";
	String six = "| Dit022 | MFSE  |";
  String seven = "|----------------|";


	
	System.out.println(one);
	System.out.println(two);
	System.out.println(three);
	System.out.println(four);
	System.out.println(five);
	System.out.println(six);
	System.out.println(seven);
	
}

public void printArray( Object[] theArray) {
	String res = Arrays.toString(theArray);
	System.out.println(res);
}



//: Function form Eugene Yokota.
public void Usage() {
	
	  OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	  for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
	    method.setAccessible(true);
	    if (method.getName().startsWith("get")
	        && Modifier.isPublic(method.getModifiers())) {
	            Object value;
	        try {
	            value = method.invoke(operatingSystemMXBean);
	        } catch (Exception e) {
	            value = e;
	        } // try
	        System.out.println(method.getName() + " = " + value);
	    } // if
	  } // for
	}



}
