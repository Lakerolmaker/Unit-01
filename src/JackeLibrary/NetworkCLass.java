package JackeLibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;


public class NetworkCLass {

	
	public ipClass ipInfo = null;
	
	
	public boolean netIsAvailable() {	
		 try {
		        URL url = new URL("http://www.google.com");
		        URLConnection conn = (URLConnection) url.openConnection();
		        conn.connect();
		        return true;
		    } catch (MalformedURLException e) {
		    		return false;
		    } catch (IOException e) {
		        return false;
		    }
	}
	
	
	public String getPrivateIpAdress() {
		InetAddress adress = null;
		try {
		 adress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			
		}
		
		return adress.getHostAddress();
	}
		
	public String getpublicIpAdres() {
		getInfo();
		return ipInfo.ipAdress;
	}
	
	public String getLocalHostname() {
		
		InetAddress adress = null;
		try {
		 adress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			
		}
		
		return adress.getCanonicalHostName();
		
	}

	public String getPublicHostname() {
		getInfo();
		return ipInfo.Hostname;
	}
	
	public String getInternetProvider() {
		getInfo();
		return ipInfo.organization;
	}
		
	public void getInfo() {
		
		if(ipInfo == null) 
			$.get.URL = "https://ipinfo.io/json";
			try {
				convertFromJson($.get.get());
			}catch (Exception e) {
				console.log("error");
			}
		
	}
	
	public void convertFromJson(String input) {

		String ipAdress = ""; 
		String Hostname = ""; 
		String city = ""; 
		String region = ""; 
		String country = ""; 
		String latitude = ""; 
		String longitude = ""; 
		String organization = ""; 
		String postalcode = ""; 
				
		int ipIndex = input.indexOf("ip") + 6;
		for(int i = ipIndex ; i > 1 ; i++) {
			if(input.charAt(i) == '"') {
				input = input.substring(ipIndex);
				i = 0;
			}else {
				ipAdress += input.charAt(i);
			}
		} 
		

		int HostIndex = input.indexOf("hostname") + 12;
		for(int i = HostIndex ; i > 1 ; i++) {
			if(input.charAt(i) == '"') {
				input = input.substring(HostIndex);
				i = 0;
			}else {
				Hostname += input.charAt(i);
			}
		} 
		
		int cityIndex = input.indexOf("city") + 8;
		for(int i = cityIndex ; i > 1 ; i++) {
			if(input.charAt(i) == '"') {
				input = input.substring(cityIndex);
				i = 0;
			}else {
				city += input.charAt(i);
			}
		} 
		

		int regionIndex = input.indexOf("region") + 10;
		for(int i = regionIndex ; i > 1 ; i++) {
			if(input.charAt(i) == '"') {
				input = input.substring(regionIndex);
				i = 0;
			}else {
				region += input.charAt(i);
			}
		} 
		
		int countryIndex = input.indexOf("country") + 11;
		for(int i = countryIndex ; i > 1 ; i++) {
			if(input.charAt(i) == '"') {
				input = input.substring(countryIndex);
				i = 0;
			}else {
				country += input.charAt(i);
			}
		} 
		

		int locationIndex = input.indexOf("loc") + 7;
		boolean longCheck = false;
		for(int i = locationIndex ; i > 1 ; i++) {
			if((input.charAt(i) == ',') || (longCheck == true) ) {
				longCheck = true;
				if(input.charAt(i) == '"') {
					i = 0;
					longitude = longitude.substring(1);
				}else {
					longitude += input.charAt(i);
				}
			}else {
				latitude += input.charAt(i);
			}
		} 
		
	
		int preOrgIndex = input.indexOf("org") + 7;
		input = input.substring(preOrgIndex);
		int organiztionIndex = input.indexOf(" ") + 1;

		for(int i = organiztionIndex ; i > 1 ; i++) {
			if(input.charAt(i) == '"') {
				input = input.substring(organiztionIndex);
				i = 0;
			}else {
				organization += input.charAt(i);
			}
		} 
		
		int postalIndex = input.indexOf("postal") + 10;
		for(int i = postalIndex ; i > 1 ; i++) {
			if(input.charAt(i) == '"') {
				input = input.substring(postalIndex);
				i = 0;
			}else {
				postalcode += input.charAt(i);
			}
		} 
		
		location templocation = new location( Double.parseDouble(latitude),Double.parseDouble(longitude));
		
		ipInfo = new ipClass(ipAdress, Hostname, city, region, country, templocation, organization, postalcode);

	}
	
}
