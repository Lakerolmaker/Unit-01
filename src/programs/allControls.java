package programs;

import javax.swing.ImageIcon;

import LakerLibrary.$;
import LakerLibrary.console;
import UILibrary.UI;
import UILibrary.border;

public class allControls {

	public static UI frame1;
	
	public static void start() {
		
		frame1 = new UI("Frame" , "Test frame", 400 , 400 ,false , false , true);

		 String[] choices = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};

	    frame1.addPasswordField("password", 10, 10, 100, 30);
	    
	    frame1.addTextField("text", "text", 10, 50, 100, 30);
	    
	    frame1.addLabel("labelID", "label", 10, 140, 45, 30);
	    frame1.label.setborder("labelID", border.stroke(1, 1));
	   
	    frame1.addButton("Print" ,  250 , 100 , 100 ,30 , null ); 
	    
	    frame1.addSlider("slider", 150, 10, 200, 40, 0, 50, 25, true);
	    frame1.slider.addOnChange("slider", ()->{
	    	
	    	int procentage = frame1.slider.getProcentage("slider");
	    	
	    frame1.updateProgressbar("hello", procentage);
	    	
	    });
	    
	    
	    frame1.addPogressbar("hello", 150, 70, 200, 15, false, 0, 100);
	    frame1.updateProgressbar("hello", frame1.slider.getProcentage("slider"));
	    
	    frame1.addSpinner("hello", 250, 200, 100, 20, false, choices);
	    
	    frame1.addDropdown("mydropdown", 10, 100, 100, 30, choices);
	    
	    frame1.frame.setIconImage(new ImageIcon("icon.png").getImage());

	    
	    String[] columnNames = {
				"colum 1",
				"colum 2",
               	};
	    String computerName = $.computer.getComputerName();
		Object[][] data = {
			    {"1" , "1 data"},
			    {"2" , "2 data"},
			    {"3" , "3 data"},
			    {"4" , "4 data"},
			    {"5" , "5 data"},  
			};
		
		frame1.addGridTable("title", 200, 200, 250 , 250,  data , columnNames);
	    
		frame1.addScrollBox(frame1.getter.getGridTable("title") , 100, 100);
		
		frame1.show();
		
		
	}
	
	
}
