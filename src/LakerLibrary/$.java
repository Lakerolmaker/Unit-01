package LakerLibrary;

import static LakerLibrary.Input.*;

import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.tools.JavaFileObject;

import console_external.keydownEvent;

public class $ {

		public static void print(Object o) {
		    System.out.print(o);
		   
		}
		
		public static void sleep(int time){
			try        
			{
			    Thread.sleep(time);
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
		}
		
		public static bitClass bit = new bitClass();
		
		public static PostClass post = new PostClass();
		
		public static GetClass get = new GetClass();
		
		public static NetworkCLass network = new NetworkCLass();
		
		public static popupClass popup = new popupClass();
		
		public static Input input = new Input();

		public static userInfoClass user = new userInfoClass();
		
		public static fileClass file = new fileClass();
		
		public static sortClass sort = new sortClass();
		
		public static console console = new console();

		public static TimeClass time =  new TimeClass();
		
		public static numberClass number = new  numberClass();
		
		public static systemClass system = new systemClass();
		
		public static mouseInterface mouse = new mouseInterface();
	
		public static MathClass math = new MathClass();
}





