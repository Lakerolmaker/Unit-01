package programs;

import java.text.DecimalFormat;
import java.util.Random;

import LakerLibrary.*;
import UILibrary.UI;
import UILibrary.border;

public class HigherLower {
	
	private static UI frame1;
	private static double userInput;
	private static double randomNumber;
	
	public static void start() {
		
		frame1 = new UI("mainFrame" , "Guess the number", 400 , 400 , false , false, true);
		
		frame1.addLabel("output", "Guess the Number", 10, 10, 380, 200);
		frame1.label.setborder("output", border.loweredbevel);
		frame1.label.setFontSize("output", 50);
		frame1.label.setAllingment("output", 0);
		
		frame1.addTextField("input", "" , 10, 250, 380, 100);
		frame1.textfield.setFontSize("input", 50);
		frame1.textfield.setAllingment("input", 0);
		
		frame1.show();
		
		Random rand = new Random(); 
		randomNumber = rand.nextDouble() * 100;
		
		DecimalFormat df2 = new DecimalFormat("##.##");
		randomNumber = Double.valueOf(df2.format(randomNumber)); 
		
		//: keypress
		frame1.textfield.addKeypressEvent("input", ()->{
			
			int pressedButton = frame1.textfield.getPressedKey();
			if(pressedButton ==  10) {

				if(Checker() == true) {
					
					String output = "";
					
					if(userInput > randomNumber ) {
						output = "Lower";
					}else if (userInput < randomNumber ){
						output = "Higher";
					}else if(userInput ==  randomNumber ){
						output = "Correct";
					}
				
					frame1.label.setValue("output", output );
					
				}else {
					
				//: Error
				frame1.label.setValue("output", "NaN" );
				
				}
			}
		});
		
		
		
	}
	
	
	private static boolean Checker() {
		
		try {
			String inputed = frame1.textfield.getValue("input");
			userInput = Double.parseDouble(inputed);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	
}
