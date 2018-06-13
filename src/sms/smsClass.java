package sms;

import java.util.ArrayList;

import JackeLibrary.$;
import JackeLibrary.console;


public class smsClass {
	
	

	private static String APIkey = "";
	
	
	public static void sendMessage(String message , String from , String To) throws Exception {
		
		$.post.URL = "https://api.clockworksms.com/http/send.aspx";
		
		$.post.addPostParamter("Key", APIkey);
		$.post.addPostParamter("To", To);
		$.post.addPostParamter("From", from);
		$.post.addPostParamter("Content", message);
		
		if(from.length() > 12) {
			throw new Exception("To long name on author , lenght : " + from.length());
		}
		
		$.post.post();
		
	}
	
	

	
	public static void getBalance() {
		
		$.get.URL = "https://api.clockworksms.com/http/balance";
		$.get.addGetParamter("Key", APIkey);
		
		try {
			String returned = $.get.get();
			
			double value = Double.valueOf(returned.substring(9, 13));
			
			int remaining = (int) Math.floor(value / 0.05);
			
			console.log($.get.get() + " | Remaining sms : " + remaining);
		} catch (Exception e) {
			console.log("error");
		}
		
	}
	
	
	
}
