package APP;
import JackeLibrary.*;
import UILibrary.UI;
import UILibrary.UIEvent;
import application.*;
import consoleWindow.consoleFX;
import programs.*;
import sms.smsClass;
import encryption.*;

import static JackeLibrary.Input.*;

import java.awt.Desktop;
import java.awt.List;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import javax.management.monitor.Monitor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import Da_vinci_sleep.sleepCalulator;

public class Main {

	public static String[] ARGS;
	
	
	public static void main(String[] args) throws Exception{
		//: Stores the command line arguments
		ARGS = args;

		//programs_console.consoleMenu.start();
		
		GameEngine.GameEngine.startProgram(args);
		
		//: TODO : add a reveal to the label 
		
		//programs_console.file.deleteLowerThan();
		
		//SystemMonitor.start();
		//Chat.start();
		//sleepCalulator.start();
		//MainFX.startProgram(args);
		
		// Mecenat
		// DHL
		// Google
		// Eurovoice
		// JCCLUB
		// Facebook
		// Hemkop
		// Avisering
		
		
		String message = "You souck , gooo F yourself";
		String from = "Hemkop";
		
		//console.logg("Message lenght : " + message.length());
		//console.logg("Author lencht  : " + from.length());
		
		
		//smsClass.sendMessage(message, from , "+46733443240");
		//smsClass.getBalance();
	
	} 
		

}


