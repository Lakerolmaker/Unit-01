package programs_console;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;

import LakerLibrary.$;
import LakerLibrary.EnglishNumberToWords;
import LakerLibrary.console;
import console_external.consoleFX;
import console_external.consoleStyle;
import console_external.runnableConsole;
import encryption.AES_crypt;
import encryption.B64_crypt;
import encryption.MD5_crypt;
import encryption.encryption;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class consoleMenu {

	public static boolean cancel = false;
	private static int index = 0;
	public static boolean on = true;
	private static String path = "";
	private static String savePath = $.file.savePath + File.separator + "console";
	private static ArrayList<String> commandHistory = null;
	private static int commandHistory_INDEX = -1;
	private static String avtiveFontColor = "green";
	private static long activeFontSize = 13;
	private static String activeFontFamily = "Consolas";
	private static String runningApp = "";

	private static String mode = "";

	private static int progress = 0;

	private static ArrayList<consoleFX> consoleArray = new ArrayList<consoleFX>();

	public static void start() {

		// progress bar


		// todo

		//programs
		// ftp

		// sub routines:
		// unpack zip
		// drag and drop
		// read/write text file
		// more stuff with cd . like remove , duplicate
		// cancel all apps
		// hacker mode
		// make maain function

		//: turn on backslash

		// text to speach
		// jar inside jar'

		createSavefolder();
		loadSave();

		console.external.displayStandalone(null, new runnableConsole() {

			@Override
			public void run() {
				path = System.getProperty("user.dir");


				console.external.print("");
				console.external.print("Lakerolmakers console  V 3.4");
				console.external.printLine();
				console.external.print("");
				console.external.print("To list all commands type help.");
				printCursor();

				console.external.addKeydownEvent( "consoleMenuMain", KeyCode.ENTER , ()-> {
					String input = "";
					input = console.external.getInput();

					if(on) {

						if(index == 0) {
							switch (getFirstArg(input)) {
							case "cd":
								changeDir(input);
								break;
							case "read":
								readFile(input);
								break;
							case "duplicate":
								duplicateFile(input);
								break;
							case "edit":
								editFile(input);
								break;
							case "ls":
								PrintPath(true);
								break;
							case "dir":
								PrintPath(false);
								break;
							case "path":
								print(path);
								break;
							case "deleter":
								turnoff();
								programs_console.fileDeleter.deleteLowerThan();
								break;
							case "help":
								printHelp(input);
								break;
							case "?":
								printHelp(input);
								break;
							case "encrypt":
								encrypt(input);
								break;
							case "decrypt":
								decrypt(input);
								break;
							case "tree":
								tree(input);
								break;
							case "color":
								color(input);
								break;
							case "fontSize":
								fontSize(input);
								break;
							case "matrix":
								matrix();
								break;
							case "hackermode":
								hackerMode();
								break;
							case "ftp":
									ftpControler(input);
								break;
							default:
								if(input.equals("") == false) {
									console.external.print("Command not found : '" + input + "'");
								}
								break;
							}//: end switch

						}//: end index 0
						// edit mode 1
						else if(index == 1) {

						}

						commandHistory_INDEX = -1;
						printCursor();

					}

					saveHistory(input);

				});

				addKeyNormalBinds();
				addStaticKeyBinds();


			}

		});

	}

	private static void printHelp(String input) {
		String[] args = getArgs(input);
		String[] content = getContent(args);


		if(hasArgs(args)){
			if(contains(args ,"-encrypt")) {

				console.external.print("Encrypt Info:");
				console.external.print("Encrypt is a command that encrypt a string. ");
				console.external.print("encrypts the string using DES if no other method is specified   ");
				console.external.print("Avalible arguments: ");
				console.external.print("-B64 \t Encrypts the string in base 64  ");
				console.external.print("-AES \t Encrypts the string with a Advanced Encryption Standard");
				console.external.print("-DES \t Encrypts the string with the standard data encryption standard");
				console.external.print("-MD5 \t Encrypts the string into a 128-bit hash value using MD5");

			}else if(contains(args ,"-decrypt")) {

				console.external.print("Decrypt Info:");
				console.external.print("Decrypt is a command that decrypt a encrypted string of the same kind. ");
				console.external.print("Decrypts the string using DES if no other method is specified   ");
				console.external.print("Avalible arguments: ");
				console.external.print("-B64 \t Decrypts a string in base 64  ");
				console.external.print("-AES \t Decrypts the string with a Advanced Encryption Standard");
				console.external.print("-DES \t Decrypts the string using the standard data encryption standard");
				console.external.print("-file \t Prints the result in a text file on the desktop");

			}else if(contains(args ,"-tree")) {

				console.external.print("Avalible arguments:  ");
				console.external.print("-home \t prints the structure of the current path of the program");

			}else if(contains(args ,"-cd")) {

				console.external.print("Avalible arguments:  ");
				console.external.print("-root	\t Changes the current directory to the systems root");
				console.external.print("-home	\t Changes the current directory to the programs location");
				console.external.print("-desktop	\t Changes the current directory to the desktop");
				console.external.print("-up		\t Changes the current working path to it's parent");
				console.external.print("--		\t Changes the current working path to it's parent");

			}else if(contains(args ,"-color")) {

				console.external.print("Color Info:");
				console.external.print("Changes the color of the text used in the console.");
				console.external.print("There are a set amount of colors to choose from.");
				console.external.print("Those are as listed below.");
				console.external.print("");
				console.external.print("Avalible colors : ");
				console.external.print("---------------------");
				printArray(consoleStyle.fontColors, 2);

			}else if(contains(args ,"-fontSize")) {

				console.external.print("FontSize Info:");
				console.external.print("Changes the font of the text used in the console.");
				console.external.print("You can also change the fontSize with ctrl + '+/-'");
				console.external.print("There are a set amount of fonts to choose from.");
				console.external.print("Those are as listed below.");
				console.external.print("");
				console.external.print("Avalible fontSizes : ");
				console.external.print("---------------------");
				printArray(consoleStyle.fontSize, 5);

			}else if(contains(args ,"-ftp")) {

				console.external.print(FTPProgram.USAGE);

			}else {
				console.external.print("The selected argument does not have a help page");
			}
		}else {
			console.external.print("Avalible commands : ");
			console.external.print("---------------------");
			console.external.print("Progrmas : ");
			console.external.print("");
			console.external.print("help | ?  \t\t List all avalible commands");
			console.external.print("deleter   \t\t Removes file over a specific size");
			console.external.print("ls        \t\t prints all the files in the current path");
			console.external.print("dir       \t\t prints all the directories");
			console.external.print("path      \t\t prints the path to the current directory");
			console.external.print("matrix    \t\t Starts the matrix effect");
			console.external.print("---------------------");
			console.external.print("Commands : ");
			console.external.print("Uses a argument after the command , example : ecrypt  hello ");
			console.external.print("Type help -command for more options");
			console.external.print("");
			console.external.print("cd      	    \t\t Changes the current working directory");
			console.external.print("read    	    \t\t prints the contents of a text file");
			console.external.print("duplicate    \t\t duplicates a file");
			console.external.print("speak        \t\t Uses text tp speach to syntezise what you input");
			console.external.print("color        \t\t changes the color of the text to a specified color");
			console.external.print("fontSize     \t\t changes the fontSize of the text");
			console.external.print("encrypt      \t\t Encrypts a string");
			console.external.print("decrypt      \t\t Decrypts a string");
			console.external.print("tree    	    \t\t prints the the structure of a path");

		}


	}

	private static void print(String input) {
		console.external.print(input);
	}

	private static void error(String input) {
		console.external.error(input);
	}

	private static void setText(String input) {
		console.external.setText(input);
	}

	private static void printArray(String[] input , int numCol) {
		int index = 0;
		String temp = "";

		for (int i = 0; i < input.length; i++) {
			String value = input[i];

			temp += value + " \t";
			index++;

			if(index == numCol) {
				print(temp);
				temp = "";
				index = 0;
			}

		}

	}

	public static void turnon() {
		addKeyNormalBinds();
		on = true;
	}

	public static void turnoff() {
		removeNormalKeyBinds();
		on = false;
	}

	private static String getFirstArg(String text) {
		  if (text.indexOf(' ') > -1) { // Check if there is more than one word.
		      return text.substring(0, text.indexOf(' ')); // Extract first word.
		  } else {
		      return text; // Text is the first word itself.
		  }
	}

	private static String[] getArgs(String input) {
		return input.split(" ");
	}

	private static boolean contains(String[] input , String search) {
		for(String index : input) {
			if (index.equals(search))
				return true;
		}
		return false;
	}

	private static boolean hasArgs(String[] input) {
		for(String index : input) {
			if (index.charAt(0) == '-')
				return true;
		}
		return false;
	}

	private static String[] getContent(String[] input) {
		ArrayList<String> temp =  new ArrayList<String>();
		for(int i = 0; i < input.length; i++) {
			if ((input[i].charAt(0) != '-') && (i != 0))
				temp.add(input[i]);
		}
		return temp.toArray(new String[0]);
	}

	private static String getContentAsOne(String[] input) {
		String temp = "";
		for(int i = 0; i < input.length; i++) {
			if ((input[i].charAt(0) != '-') && (i != 0))
				if(i == input.length - 1) {
					temp += input[i];
				}else {
					temp += input[i] + " ";
				}

		}
		return temp;
	}

	private static boolean checkContent_one(String[] input) {
		if(input.length == 1) {
			return true;
		}else {
			return false;
		}
	}

	private static void printCursor() {
		console.external.print("/" + getDirectory(path) + " >");
		console.external.saveText();
	}

	private static void encrypt(String input) {
		String[] args = getArgs(input);
		String content = getContentAsOne(args);
		String encryptedData = "";

			if(hasArgs(args)){

				if(contains(args , "-DES")) {
					encryptedData = encryption.DES.encrypt(content);
				}else if(contains(args , "-AES")) {
					encryptedData = encryption.AES.encrypt(content);
				}else if(contains(args , "-B64")) {
					encryptedData = encryption.B64.encrypt(content);
				}else if(contains(args , "-MD5")) {
					encryptedData = encryption.MD5.encrypt(content);
				}else {
					encryptedData = encryption.DES.encrypt(content);
				}

			}else {
				encryptedData = encryption.DES.encrypt(content);
			}

			if(encryptedData != null) {
				if(contains(args , "-file")) {
					$.file.writeToTextFile("Encrypted_Text", encryptedData , $.file.desktop);
					console.external.print("Written to file ");
				}else {
					console.external.print("Encrypted : " + encryptedData);
				}
			}else {
				console.external.error("Could not encrypt string : '" + content + "'");
			}
	}

	private static void decrypt(String input) {
		String[] args = getArgs(input);
		String[] content = getContent(args);
		String decryptedData = "";

		if(checkContent_one(content)) {
			String cont = content[0];
			if(hasArgs(args)){

				if(contains(args , "-DES")) {
					decryptedData = encryption.DES.decrypt(cont);
				}else if(contains(args , "-AES")) {
					decryptedData = encryption.AES.decrypt(cont);
				}else if(contains(args , "-B64")) {
					decryptedData = encryption.B64.decrypt(cont);
				}

			}else {
				decryptedData = encryption.DES.decrypt(cont);
			}

			if(decryptedData != null) {
				console.external.print("Decrypted : " + decryptedData);
			}else {
				console.external.error("Could not decrypt string : '" + cont + "'");
			}

		}else {
			console.external.error("Could not read arguments");
		}

	}

	private static void changeDir(String input) {

		String[] args = getArgs(input);
		String[] content = getContent(args);

		if(hasArgs(args)) {

			if(contains(args , "-home")) {
				checkDir($.file.CurrentDir);
			}else if(contains(args , "-root")) {
				path = $.system.info.getDiskPath(0);
			}else if(contains(args , "-desktop")) {
				checkDir(System.getProperty("user.home") + File.separator + "Desktop");
			}

		}else {

			if(checkContent_one(content)) {
				checkDir(content[0]);
			}

		}
	}

	private static void checkDir(String input) {
		String url = input;
		String newUrl = path + File.separator + url;
		if(new File(newUrl).exists()) {
			path = newUrl;
		}else {
			if(new File(url).exists()) {
				path = url;
			}else {
				error("Directory '" + url + "' not found");
			}
		}
	}

	private static String getDirectory(String input) {
		int position = 0;
		for(int i = input.length() - 1; i > 0; i--) {
			char index = input.charAt(i);
			if((index == '/') || (index == (char)92) ) {
				position = i + 1;
				i = 0;
			}
		}

		return  input.substring(position , input.length() ); // Extract first word.

	}

	private static void tree(String input) {
		String[] args = getArgs(input);
		String[] content = getContent(args);

		if(hasArgs(args)) {
			if(contains(args , "-home")) {
				ListTree($.file.CurrentDir);
			}
		}else {
			ListTree(path);
		}

	}

	private static void ListTree(String path) {
		printTree(path , 0);
	}

	private static void printTree(String path , int indentation) {

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

	try {
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
		    	console.external.print(indent + file.getName() + " | " + $.file.getSizeEnding(file.length()));
		    }

			if ((file.isDirectory()) && (file.isHidden() != true)){
				console.external.print(" ");
				console.external.print(indent + File.separator + file.getName());
				Thread.sleep(100);
				printTree(file.getAbsolutePath() ,indentation + 1 );
		    }

		}

	} catch (Exception e) {}



	}

	private static void PrintPath(boolean includeFiles ) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

	try {
	      Arrays.sort(listOfFiles, (f1, f2) -> {
	          if (f1.isDirectory() && !f2.isDirectory()) {
	             return 1;
	          } else if (!f1.isDirectory() && f2.isDirectory()) {
	             return -1;
	          } else {
	             return f1.compareTo(f2);
	          }
	       });

		String toPrint = "";

		if(includeFiles == true ) {
			for (File file : listOfFiles) {

			    if ((file.isHidden() != true)  && (includeFiles == true)) {

			    	toPrint = file.getName();

			    	long fileSize = file.length();
			    	toPrint += "\t " + formatSize(fileSize);



			    	console.external.print(toPrint);

			    }
			}
		}else {
			for (File file : listOfFiles) {
				if ((file.isDirectory()) && (file.isHidden() != true)){
					toPrint += ( file.getName() + "\t  ");
			    }

			}
		}


		console.external.print(toPrint);

	} catch (Exception e) {}

	}

	public static String formatSize(long v) {
	    if (v < 1024) return v + " B";
	    int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
	    return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
	}

	private static void fontFamily(String input) {
		String[] args = getArgs(input);
		String content = getContentAsOne(args);


	}

	private static void fontSize(String input) {
		String[] args = getArgs(input);
		String content = getContentAsOne(args);
		long number = -1;
		try {
			number = Long.valueOf(content);
		} catch (Exception e) {}

		if(number == activeFontSize) {
			error(content  + " is allready the current fontSize");
		}else if(number > 0) {

			console.external.theTextField.setStyle("-fx-font-size: " + number +"px;");

			activeFontSize = number;

			print("FontSize changed to " + number);

		}else{
			error("could not set fontSize to " + content);
		}

	}

	private static void addTofontSize(int change) {
		long newfontSize = activeFontSize + change;


		if(newfontSize > 0){

			console.external.theTextField.setStyle("-fx-font-size: " + newfontSize +"px;");

			activeFontSize = newfontSize;

		}else{
			error("FontSize can't be lower then zero");
		}


	}

	private static void color(String input) {
		String[] args = getArgs(input);
		String content = getContentAsOne(args);

		if(content == avtiveFontColor) {
			error(content  + " is allready the active color ");
		}else if(contains(consoleStyle.fontColors , content)) {

			console.external.theTextField.getStyleClass().remove(avtiveFontColor);
			console.external.theTextField.getStyleClass().add(content);

			avtiveFontColor = content;

		}else{
			error("No such color found!");
		}

	}

	private static void matrix() {

		runningApp = "matrix";

		color("color green");

		$.time.setInterval("matrixTimer" , 0, 70, ()->{

			double width = console.external.theTextField.getWidth();
			double unit = width / activeFontSize;
			double modulas = 1.3;
			double numberCount = unit * modulas;

			String toBePrinted = "";
			for(int i = 0; i < numberCount; i++) {
				toBePrinted += $.number.randomInt(0, 10);
			}

			print(toBePrinted);
		});

	}

	private static int Hackerprogress = 0;
	private static void hackerMode() {
		createNewConsoles();

		runningApp = "Multimatrix";

		color("color green");

		double width = console.external.theTextField.getWidth();
		double unit = width / activeFontSize;
		double modulas = 1.3;
		double numberCount = unit * modulas;

		for (int ix = 0; ix < consoleArray.size(); ix++) {
			int index = ix;

				$.time.setInterval("MultiMatrixTimer" , 0, 70, ()->{

					String toBePrinted = "";
					for(int i = 0; i < numberCount; i++) {
						toBePrinted += $.number.randomInt(0, 10);
					}
					consoleArray.get(index).print(toBePrinted);

				});
		};

		consoleFX hackConsole = new consoleFX();
		hackConsole.display();
		hackConsole.print("Setting up TTP connection");
		hackConsole.print("");
		hackConsole.saveText();

		$.time.setInterval("hackerProgress", 1000, 1000, ()->{
			if(Hackerprogress < 120) {
				hackConsole.progressbar(Hackerprogress);
			}else if(Hackerprogress == 130) {
				hackConsole.print("FTP connection esstablished");
			}else if(Hackerprogress == 140) {
				hackConsole.print("Uploading passwords to database");
			}else if(Hackerprogress > 200) {
				$.time.removeInterval("hackerProgress");
				$.time.removeInterval("MultiMatrixTimer");
				removeNewConsoles();
			}

			Hackerprogress += 10;
		});

	}

	private static void createNewConsoles() {
		int height = $.system.info.getScreenHeight();
		int heightAmount = (height - 20) / 290;

		int width = $.system.info.getScreenWidth();
		int widhtAmount = width / 430;

		int indent = 0;
		int marginTop = 20;

		for (int ix = 0; ix < heightAmount; ix++) {

			for (int i = 0; i < widhtAmount; i++) {

				consoleFX NEWconsole = new consoleFX();
				NEWconsole.display(indent , marginTop );
				indent += 430;
				consoleArray.add(NEWconsole);

			}
			marginTop += 290;
			indent = 0;
		}
	}

	private static void removeNewConsoles() {
		for (consoleFX cons : consoleArray) {
			cons.close();
		}
		consoleArray.clear();
	}

	private static void createSavefolder() {
		$.file.createSavefolder();
		$.file.createFolder("console", $.file.savePath);
	}

	private static void saveHistory(String input) {
		if(input.equals("") != true) {
			$.file.writeToTextFile("consoleHistory", input + "\n", savePath);
			commandHistory.add(0 , input);
		}
	}

	private static void loadSave() {
		String[] temp =	$.number.reverse($.file.readFromTextFile_Arr("consoleHistory", savePath));
		commandHistory = new ArrayList<String>(Arrays.asList(temp));
	}

	private static void cancelApps() {
		switch (runningApp) {
		case "matrix":
			$.time.removeInterval("matrixTimer");
			break;
		}
	}

	private static void readFile(String input) {
		String[] args = getArgs(input);
		String content = getContentAsOne(args);

		String fileContent;
		try {
			fileContent = $.file.readFromFile(content, path);
			print("Content in file " + content + " :");
			print(fileContent);
		} catch (Exception e) {
			error("Could not read file");
		}

	}

	private static void duplicateFile(String input) {
		String[] args = getArgs(input);
		String content = getContentAsOne(args);

		try {
			$.file.dublicateFile(content, path);
			print("File duplicated");
		} catch (IOException e) {
			error("File could not be duplicated");
		}
	}

	private static void editFile(String input) {
		String[] args = getArgs(input);
		String content = getContentAsOne(args);

		boolean canread = false;
		String fileContent;
		try {
			fileContent = $.file.readFromFile(content, path);
			canread = true;
		} catch (Exception e) {
			error("Could not edit file");
		}


		if(canread) {
			index = 1;
			turnoff();
			removeNormalKeyBinds();
			addEditKeyBinds();


			print("");
			console.external.printLine();

		}

	}

	private static void addKeyNormalBinds() {


		//: fontSize
		KeyCharacterCombination plusComb = new KeyCharacterCombination("+", KeyCombination.CONTROL_DOWN);
		console.external.addMultiKeyDown("plusEvent", plusComb, ()->{
			addTofontSize(1);
		});

		KeyCharacterCombination minusComb1 = new KeyCharacterCombination("-", KeyCombination.CONTROL_DOWN);
		KeyCodeCombination minusComb2 = new KeyCodeCombination(KeyCode.SLASH, KeyCombination.CONTROL_DOWN);
		console.external.addMultiKeyDown("minusevent", minusComb1, minusComb2, ()->{
			addTofontSize(-1);
		});

		KeyCodeCombination controlC = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
		console.external.addMultiKeyDown("cancelEvent", controlC, ()->{
			cancelApps();
			printCursor();
		});


	}

	private static void removeNormalKeyBinds() {
		console.external.removeKeydownEvent("plusEvent");
		console.external.removeKeydownEvent("minusevent");
		console.external.removeKeydownEvent("cancelEvent");
	}

	private static void addStaticKeyBinds() {
		//: History
		console.external.addKeydownEvent("upArrowEvent", KeyCode.UP, ()->{
			if(commandHistory_INDEX < commandHistory.size() - 1) {
				commandHistory_INDEX++;
				String before = console.external.getBackupText();
				String command = commandHistory.get(commandHistory_INDEX);
				String appended = before + command;
				console.external.setText(appended);
			}
		});

		//: History
		console.external.addKeydownEvent("downArrowEvent", KeyCode.DOWN, ()->{
			if(commandHistory_INDEX > 0) {
				commandHistory_INDEX--;
				String before = console.external.getBackupText();
				String command = commandHistory.get(commandHistory_INDEX);
				String appended = before + command;
				console.external.setText(appended);
			}else if(commandHistory_INDEX == 0){
				String before = console.external.getBackupText();
				console.external.setText(before);
				commandHistory_INDEX--;
			}
		});

		console.external.addKeydownEvent("preventBackSpace", KeyCode.BACK_SPACE, ()->{

			String before = console.external.getBackupText();
			String textNow = console.external.getText();
			if(textNow.contains(before) == false) {
				console.external.setText(before);
			}

		});
	}

	private static void addEditKeyBinds() {

		KeyCodeCombination controlX = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
		console.external.addMultiKeyDown("savEevent", controlX, ()->{
			console.log("save");
		});

	}

	/****************************************************************

	  FTP Mode

	****************************************************************/

	private static FTPClient ftp;

	private static void ftpControler(String input) {
		String[] args = getArgs(input);
		String[] content = getContent(args);
		String oneContent = getContentAsOne(args);

		// download
		// upload
		// make dir
		// logout
		// save login info

		switch (args[1]) {
		case "-connect":
			FTPconnect(content);
			break;
		case "-login":
			FTPlogin(content);
			break;
		case "-ls":
			FTPlistFiles(true);
		case "-listfiles":
			FTPlistFiles(true);
			break;
		case "-dir":
			FTPlistFiles(false);
			break;
		case "-remove":
			FTPdeleteFile(content);
			break;
		case "-rm":
			FTPdeleteFile(content);
			break;
		case "-cd":
			if(args[2] == "-up") {
				FTPgoToParentDIR();
			}else {
				FTPchangeDIR(content);
			}
			break;
		case "-parent":
			FTPgoToParentDIR();
			break;
		case "-status":
			FTPprintStatus();
			break;
		case "-disconnect":
			FTPdisconnect();
			break;
		case "-dc":
			FTPdisconnect();
			break;
		case "-up":
			FTPuploadFile(oneContent);
			break;
		case "-down":
			FTPdownloadFile(oneContent);
			break;
		default:
			error("No such command found");
			break;
		}

	}

	@SuppressWarnings("deprecation")
	private static void FTPprintStatus() {

		print("");
		if(ftp != null) {
			if(ftp.isConnected()) {
				int port = ftp.getLocalPort();
				InetAddress adress = ftp.getRemoteAddress();
				String ipAdress = adress.getHostAddress();
				int localPort = ftp.getLocalPort();
				int remotePort = ftp.getRemotePort();
				String hostName = adress.getHostName();
				String systemName = "error";
				String status = "error";
				int connectionMode = ftp.getDataConnectionMode();

				try {
					status = ftp.getStatus();
				} catch (IOException e) {}
				try {
					systemName = ftp.getSystemName();
				} catch (IOException e) {}

				print("FTP Status - connected");
				print("Ip-Adress : " + ipAdress );
				print("Local port : " + localPort  + " ,  remote port : " + remotePort);
				print("Connection mode : " + connectionMode);
				print("Hostname : " + hostName);
				print("Systemname : " + systemName);

				print("Status : " + status);
			}else {
				print("FTP Status - disconnected");
			}
		}else {
			print("FTP Status - disconnected");
		}

	}

	private static void FTPlogin(String[] input) {

		try {
			String username = input[0];
			String password = input[1];

			 if (!ftp.login(username, password))
	         {
	             ftp.logout();
	             error("Could not login");
	         }else {
	        	 	print("FTP - Logged in");
	        		print("Remote system is " + ftp.getSystemType());
	        	 	print(ftp.getReplyString());
	         }


		} catch (Exception e) {
			error("Could no use the arguments specified");
			console.logg(e + " at " + e.getStackTrace()[0].getLineNumber());
		}

	}

	private static void FTPconnect(String[] input) {

		try {
			String serverAdress = input[0];
			int port = Integer.valueOf(input[1]);

			ftp = new FTPClient();
	        try
	        {
	            int reply;
	            if (port > 0) {
	                ftp.connect(serverAdress, port);
	            } else {
	                ftp.connect(serverAdress);
	            }
	            print("Connected to " + serverAdress + " on port " + (port>0 ? port : ftp.getDefaultPort()));

	            reply = ftp.getReplyCode();

	            if (!FTPReply.isPositiveCompletion(reply))
	            {
	                ftp.disconnect();
	                error("FTP server refused connection.");
	            }
	            ftp.enterLocalPassiveMode();
	            ftp.setCopyStreamListener(createListener());
	        }catch (Exception e) {
	            if (ftp.isConnected())
	            {
	                try
	                {
	                    ftp.disconnect();
	                }
	                catch (IOException f) {}
	            }
	            error("Could not connect to server.");

	        }

		} catch (Exception e) {
			error("Could no use the arguments specified");
		}

	}

	private static void FTPlistFiles(boolean showFiles) {


        try {
			for (FTPFile file : ftp.listFiles()) {
				if(file.isDirectory()) {
					String printed = "/";
					printed += file.getName();
				    print(printed);
				}else if((file.isFile()) && (showFiles) ) {
					String printed = "";
					printed += file.getName();
					printed += "\t Size :" + file.getSize();
				    print(printed);
				}
			}
		} catch (Exception e) {
			error(e.getMessage());
		}

	}

	private static void FTPdeleteFile(String[] input) {

		try {
			String fileName = input[0];

			if(ftp.deleteFile(fileName)) {
				print("File deletd");
			}else{
				error("Could not delete file");
			};

		} catch (Exception e) {
			error(e.getMessage());
			console.logg(e + " at " + e.getStackTrace()[0].getLineNumber());
		}


	}

	private static void FTPuploadFile(String input) {
		if(ftp != null) {
			try {

				String localFileName = input;
				File file = new File(path + File.separator + localFileName);
				InputStream localFile = new FileInputStream(file);
				FTPfileSize = file.getTotalSpace();
				ftp.appendFile(localFileName, localFile);

			} catch (Exception e) {
				error(e.getMessage());
			}
		}else {
			error("Not connected to a server");
		}
	}

	private static void FTPdownloadFile(String input) {
		if(ftp != null) {
			try {

				String remoteFileName = input;

				File localFile = new File(path + File.separator + remoteFileName);
				boolean ff = localFile.exists();

				if(localFile.isFile() == false) {
					OutputStream file = new FileOutputStream(localFile);
					ftp.retrieveFile(remoteFileName, file);
					print("File Downloaded");
				}else {
					error("File with that name allready exits");
				}

			} catch (Exception e) {
				error(e.getMessage());
			}
		}else {
			error("Not connected to a server");
		}
	}

	private static void FTPgoToParentDIR() {
		try {
			if(ftp.changeToParentDirectory()) {

			}else {
				error("Could not change to parent directory");
			}
		} catch (IOException e) {
			error(e.getMessage());
			console.logg(e + " at " + e.getStackTrace()[0].getLineNumber());
		}

	}

	private static void FTPchangeDIR(String[] input) {
		try {
			String path = input[0];

			if(ftp.changeWorkingDirectory(path)) {
				print("Changed to FTP path to /" + path);
			}else {
				error("Could not change to directory");
			}
		} catch (IOException e) {
			error(e.getMessage());
			console.logg(e + " at " + e.getStackTrace()[0].getLineNumber());
		}
	}

	private static void FTPdisconnect() {
		if(ftp != null) {
			try {
				ftp.disconnect();
				print("FTP connection closed");
			} catch (Exception e) {
				error(e.getMessage());
			}
		}else {
			error("Not connected to a server");
		}

	}

	private static void FTPlogout() {
		if(ftp != null) {
			try {
				ftp.logout();
				print("FTP logged out");
			} catch (Exception e) {
				error(e.getMessage());
			}
		}else {
			error("Not connected to a server");
		}
	}

	private static long FTPfileSize = 0;

	private static CopyStreamListener createListener(){
        return new CopyStreamListener(){
            private long megsTotal = 0;

            @Override
            public void bytesTransferred(CopyStreamEvent event) {
                bytesTransferred(event.getTotalBytesTransferred(), event.getBytesTransferred(), event.getStreamSize());
            }

            @Override
            public void bytesTransferred(long totalBytesTransferred,
                    int bytesTransferred, long streamSize) {
                long megs = totalBytesTransferred / 1000000;
                double procentage = totalBytesTransferred / FTPfileSize;
                console.logg(procentage);
                megsTotal = megs;
            }
        };
        
        
    }
	
	public static void main(String[] args) {
		consoleMenu consolemenu = new consoleMenu();
		consolemenu.start();
	}

}
