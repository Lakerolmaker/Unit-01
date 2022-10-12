package APP;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import LakerLibrary.*;
import TCP.RunnableArg;
import TCP.TCP;
import console_external.consoleFX;
import console_external.runnableConsole;


public class Main {

	public static String[] ARGS;
	
	public static void main(String[] args) throws Exception{
		//: Stores the command line arguments
		ARGS = args;

		//programs_console.consoleMenu.start();
		//programs_console.FTPProgram.start();
		
		
		//programs.allControls.start();
		//programs.SystemMonitor.start();
				
		
		//Da_vinci_sleep.sleepCalulator.start();
		
		//GameEngine.GameEngine.startProgram(args);
		
		//: TODO : add a reveal to the label 
		
		//programs.SystemMonitor.start();
		//Chat.start();
		//sleepCalulator.start();
		//MainFX.startProgram(args);
		
		
		
		console.external.displayStandalone(args, new runnableConsole() {
			
			@Override
			public void run() {
				this.print("hi");
				
				for (int i = 0; i <= 1000; i++) {
					String number = $.number.toWord.convert(i);
					this.print(number);
				}
				
			}
			
		});
		
		
	} 

}


