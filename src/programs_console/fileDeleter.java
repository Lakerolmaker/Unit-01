package programs_console;

import java.io.File;
import java.util.Arrays;

import com.sun.org.apache.xpath.internal.operations.Number;

import JackeLibrary.$;
import JackeLibrary.console;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class fileDeleter {

	private static String path = "";
	public static int index = -2;
	private static boolean on = false;
	private static long mb = -1;
	private static long deleteLimit = -1;
	private static long totallFiles = 0;
	private static int filesDeleted = 0;
	private static int filesFailed = 0;
		
	public static void deleteLowerThan() {

		console.external.openStandalone(()->{
	
			console.external.print("");
			console.external.print("File deleter V 1.3 ");
			console.external.printLine();
			
			console.external.print("");
			console.external.print("Saved profiles : ");
			console.external.print("----------------- ");
			console.external.print("Profile 1 ( /Reactions Gifs | Limit : 8 MB ) ");
			console.external.print("");
			console.external.print("");
			
			console.external.print("Enter path to delete OR number of saved profile:");
			
			console.external.print(">");
			console.external.saveText();
			
			console.external.addKeydownEvent( "deleteLowerThan_Main", KeyCode.ENTER, ()-> {
				
				String input = "";
				if(on) {
					input = console.external.getInput();
				}
				
				if((input != "") && (index == 1)) {

					if($.number.isInt(input) == false) {
						path = input;	
						console.external.print("Enter the lowest size that shall not be deleted :");
						index++;
					}else {
						int value = $.number.toInt(input);
						if(value == 1) {
							path = "/Users/jacobolsson/OneDrive/Reaction gifs";
							
							console.external.print("Enter the lowest size that shall not be deleted :");
							index++;
						}else {
							console.external.print("No such profile found!");
						}
						
					}
					
				}else if((input != "") && (index == 2)) {
					
					try {
						
						long value = Long.valueOf(input);
						
						if(value >= 0){
							mb = value;
							deleteLimit = value * 1048576;
							console.external.print("");
							console.external.print("Path : " + path);
							console.external.print("Delete limit : " + mb + "MB");
							console.external.print("Do you wish to continue ( Y/N) ");
							index++;
						}else {
							console.external.error("Number must be non negative");
						}	
					} catch (Exception e) {
						console.external.error("Not a number");
						console.logg(e);
					}
					
				}else if((input != "") && (index == 3)) {
					if(input.toUpperCase().equals("Y")) {
						deleteStart();
						console.external.removeEnterEvent("deleteLowerThan_Main");
					}else {
						console.external.removeEnterEvent("deleteLowerThan_Main");
					}
					programs_console.consoleMenu.turnon();
					console.external.print("");
					console.external.print("Shuting down File deleter");
					console.external.print(">");
					turnoff();
				}
				

				if(on) {
					console.external.print(">");
					console.external.saveText();
				}
				
				if(index == -2) {
					turnon();
					index = 1;
				}
				
			});
			
			
			
		});
			
	}
	
	private static void deleteStart() {
		
		console.external.print("");
		console.external.print("Starting...");
		
		deleteFiles(path, 0);

		console.external.print("");
		console.external.print("Totall files : " + totallFiles );
		console.external.print("Files deleted : " + filesDeleted );
		console.external.print("Files Failed : " + filesFailed );
		console.external.print("");
		console.external.print("Shuting down File deleter");
	}

	private static void deleteFiles(String path, int indentation) {
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
	      Arrays.sort(listOfFiles, (f1, f2) -> {
	          if (f1.isDirectory() && !f2.isDirectory()) {
	             return 1;
	          } else if (!f1.isDirectory() && f2.isDirectory()) {
	             return -1;
	          } else {
	             return f1.compareTo(f2);
	          }
	       });

		String indent = "";
		
		for(int i = 0 ; i < indentation; i++) {
			indent += "\t";
		}
		
		
		for (File file : listOfFiles) {
		
		    if ((file.isFile()) && (	file.isHidden() != true)) {
		        
		    		totallFiles++;
		        long fileSize = file.length();
		        
		        if(fileSize > deleteLimit) {
		        		if(file.delete() == true) {
		        			console.external.print("file deleted : " + file.getName());
		        			filesDeleted++;
		        		}else {
		        			console.external.print("file FAILED : " + file.getName());
		        			filesFailed++;
			        }
		        } 
		    }
			
			if ((file.isDirectory()) && (file.isHidden() != true)){
		        deleteFiles(file.getAbsolutePath() ,indentation + 1 );
		    }
			
		}
		
		
	}

	public static void turnon() {
		on = true;
	}
	
	public static void turnoff() {
		on = false;
	}
	
}
