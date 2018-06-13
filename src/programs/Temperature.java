package programs;

import JackeLibrary.*;
import UILibrary.UI;

public class Temperature {

	private static UI temperatureConveter;
	
	//: For the tempeture conveter
	public static String conversionMethod = "";
	
	public static void start() {
		
		temperatureConveter = new UI("MyFrame" , "Temperature Converter", 230 , 145 , false , false, true);
		
		temperatureConveter.addButton("Convert" ,  60 , 84 , 100 ,30 , ()-> buttoncallback("convert") );   
		temperatureConveter.addButton("°C" ,  120 , 20 , 44 ,33 , ()-> buttoncallback("C") ); 
		temperatureConveter.addButton("°F" ,  165 , 20 , 44 ,33 , ()-> buttoncallback("F") );   
		temperatureConveter.addLabel("output", "Select unit", 30, 20, 100, 30);
		temperatureConveter.addTextField("InputField",  null, 10, 57, 200, 30);	  
		temperatureConveter.show();
		
	
		
		
	}
		
	//: initialize the teperature conveters
	private static void teperatureConverter() {
		
		temperatureConveter = new UI("MyFrame" , "Temperature Converter", 230 , 145 , false , false, true);
		
		temperatureConveter.addButton("Convert" ,  60 , 84 , 100 ,30 , ()-> buttoncallback("convert") );   
		temperatureConveter.addButton("°C" ,  120 , 20 , 44 ,33 , ()-> buttoncallback("C") ); 
		temperatureConveter.addButton("°F" ,  165 , 20 , 44 ,33 , ()-> buttoncallback("F") );   
		temperatureConveter.addLabel("output", "Select unit", 30, 20, 100, 30);
		temperatureConveter.addTextField("InputField",  null, 10, 57, 200, 30);	  
		temperatureConveter.show();
	}
	
	//: The call back function.
	private static void buttoncallback(String buttonName) {
		//:If the convert buton was pressed
		if((buttonName == "convert") && (conversionMethod != "")){
			//: Calls the calculation function
			calculateTemperature();
			
			
		//:If the Celcius button was pressed
		}else if(buttonName == "C") {
			//: If the button hasn´t been pressen before, the user is promted to input a number
			if(conversionMethod == "") {
				conversionMethod = "C";	
				temperatureConveter.label.setValue("output" , "Input number" );
				
			//: if a conversion method has been inputed before the number if converted directly
			}else {
				conversionMethod = "C";	
				calculateTemperature();
			}
		
		//:If the farenheight button was pressed	
		}else if(buttonName == "F") {
			//: If the button hasn´t been pressen before, the user is promted to input a number
			if(conversionMethod == "") {
				conversionMethod = "F";	
				temperatureConveter.label.setValue("output" , "Input number" );
				
			//: if a conversion method has been inputed before the number if converted directly	
			}else {
				conversionMethod = "F";	
				calculateTemperature();
			}
		}
		
		
		
	}
	
	//: The function that calculated the temperature
	private static void calculateTemperature() {
		//: Gets the input from the user
		String input = temperatureConveter.textfield.getValue("InputField");
		
		//: Checks if the user has inputed a number
		try {
			
		//: converts it into a double
		double parsedInput = Double.parseDouble(input);
		
		//: Defines the output
		double output = 0;
		
		//: Checks which method should be used
		if(conversionMethod == "C") {
			//: Calculates the Temperature
			output = ( parsedInput * 1.8) + 32;
			//: Rounds the value to 2 decimals
			output = Math.round(output * 100.0) / 100.0;
			
			 //: Prints the result
			temperatureConveter.label.setValue("output" , Double.toString(output) + " °F");
			
		}else if(conversionMethod == "F") {
			//: Calculates the Temperature
			output = ( parsedInput - 32) / 1.8;
			//: Rounds the value to 2 decimals
			output = Math.round(output * 100.0) / 100.0;
			
			 //: Prints the result
			temperatureConveter.label.setValue("output" , Double.toString(output) + " °C");
		}
		
		
		//: Prints a error to the user if they have inuted something other than a number
		}catch(Exception e) {
			temperatureConveter.label.setValue("output" , "Not a number" );
		}
		
	}// end calculateTemperature()
	
	
}
