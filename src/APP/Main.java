package APP;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import LakerLibrary.*;
import TCP.RunnableArg;
import TCP.TCP;
import consoleWindow.consoleFX;
import consoleWindow.runnableConsole;


public class Main {

	public static String[] ARGS;
	
	public static void main(String[] args) throws Exception{
		//: Stores the command line arguments
		ARGS = args;

		//programs_console.consoleMenu.start();
		//programs_console.FTPProgram.start();
		
		//GameEngine.GameEngine.startProgram(args);
		
		//: TODO : add a reveal to the label 
		
		//programs.SystemMonitor.start();
		//Chat.start();
		//sleepCalulator.start();
		//MainFX.startProgram(args);

		String filename = "1 to 1 000 000";
		$.file.createTextFile(filename, $.file.CurrentDir);
		StringBuilder data = new StringBuilder();
		for(long i  = 1; i <= 1000000; i++) {
			String number = $.number.toWord.convert(i);
			data.append(number + "\n");
		}
		$.file.writeToTextFile(filename, data.toString() , $.file.CurrentDir);
		
	} 

}


