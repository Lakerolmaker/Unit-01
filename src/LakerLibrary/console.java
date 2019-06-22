package LakerLibrary;

import java.util.Scanner;
import consoleWindow.consoleFX;
import consoleWindow.runnableConsole;
import javafx.scene.input.KeyCode;

public class console {

		public static consoleFX consoleWindow = null;	

		public static void log(Object o) {
	        System.out.print(o);
	    }
		
		public static void logg(Object o) {
	        System.out.println(o);
	    }
		
		public static void logn(Object o) {
	        System.out.println(o);
	    }

		public static externalConsoleInterface external = new externalConsoleInterface() {
			
			public void openStandalone(runnableConsole caller) {
				if(consoleWindow == null) {
					consoleWindow = new consoleFX();
					consoleWindow.displayStandalone(caller);
				}
			}
			
			public void open() {
				if(consoleWindow == null) {
					consoleWindow = new consoleFX();
					consoleWindow.display();
				}	
			}
			
			public void open(double x , double y) {
				if(consoleWindow == null) {
					consoleWindow = new consoleFX();
					consoleWindow.display(x, y);
				}
			}
			
			public void move(double x , double y) throws Exception {
					consoleWindow.move(x, y);	
			}
			
			public void print(String text) {
				consoleWindow.print(text);
			}

			@Override
			public void printLine() {
				consoleWindow.printLine();
			}	
			
			public void close() {
				try {
					consoleWindow.close();
				} catch (Exception e) {}
			}

			@Override
			public void error(String text) {
				consoleWindow.error(text);
			}

			@Override
			public void addKeydownEvent(String name , KeyCode trigger, Runnable code ) {
				consoleWindow.addKeydownEvent(name , trigger,  code);
			}
			
			@Override
			public void addKeydownEvent(String name , char trigger, Runnable code ) {
				consoleWindow.addKeydownEvent(name , trigger,  code);
			}

			@Override
			public String getInput() {
				return consoleWindow.getInput();
			}

			@Override
			public String getText() {
				return consoleWindow.getText();
			}

			@Override
			public void saveText() {
				consoleWindow.saveText();
			}

			@Override
			public void removeEnterEvent(String name) {
				consoleWindow.removeKeydownEvent(name);
			}

			@Override
			public void RemoveConsoleSymbol() {
				this.removeEnterEvent("consoleAdderFunc");
			}
	
		};
		
		public static readerinterface get = new readerinterface() {

		@Override
		public int readInt() {
			Scanner reader = new Scanner(System.in);  
			if (reader.hasNextInt()) {
				return reader.nextInt();
			}else {
				return 0;
			}
			
		}

		@Override
		public String readString() {
			Scanner reader = new Scanner(System.in);  
			if (reader.hasNextLine()) {
				return reader.nextLine();
			}else {
				return null;
			}
		}

		@Override
		public Byte readByte() {
			Scanner reader = new Scanner(System.in);  
			if (reader.hasNextByte()) {
				return reader.nextByte();
			}else {
				return null;
			}
		}

		@Override
		public boolean readBool() {
			Scanner reader = new Scanner(System.in);  
			if (reader.hasNextBoolean()) {
				return reader.nextBoolean();
			}else {
				 new Exception("No input given");
			}
			return false;
		}
		  
	  };
	
}
