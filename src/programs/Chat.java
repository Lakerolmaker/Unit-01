package programs;

import static LakerLibrary.Input.*;

import LakerLibrary.*;
import UILibrary.UI;
import UILibrary.border;
import javafx.scene.paint.Color;


public class Chat {

	public static UI frame1;

	public static void start() {
		
		frame1 = new UI("Frame" , "Chat", 400 , 400 ,false , false, true);
		
		frame1.addTextField("input", "hello", 40, 300, 100, 30);
			
		String[] columnNames3 = {
				"Computer :",
				"Operating System",
				
               	};
		
		Object[][] data3 = {
			    {"Postal Code" , $.user.getPostalCode()},
			    {"Postal Code" , $.user.getPostalCode()},
			    {"Postal Code" , $.user.getPostalCode()},
			    {"Postal Code" , $.user.getPostalCode()},
			    {"Postal Code" , $.user.getPostalCode()},
			    {"Postal Code" , 2},
			    {"Postal Code" , 2},
			    {"Postal Code" , 1},
			    
			};
		
		frame1.addGridTable("user Info", 10, 10, 300, 200,  data3 , columnNames3);
		
		frame1.addScrollBox(frame1.getter.getGridTable("user Info"), 100 , 200);
		frame1.gridTable.setGridColor("user Info", java.awt.Color.RED);
		
		
		
		
		frame1.show();
						
		
	}
	
	
	public static void main(String[] args) throws Exception{
		
		Chat.start();
		
	}
	
	
	
}
