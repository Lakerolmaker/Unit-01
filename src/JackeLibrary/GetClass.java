package JackeLibrary;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetClass {

	
	public ArrayList<URLParamater> GetParamaters = new ArrayList<URLParamater>(); 
	public String URL;
	
	public void addGetParamter(String name , String value) {
		
		URLParamater parameter = new URLParamater(name , value);
		
		this.GetParamaters.add(parameter);
		
	}
	
	// HTTP GET request
	public String get() throws Exception {

		//: Adds the paramaters
		String urlParameters = "?";
					
		for(int i = 0 ; i < GetParamaters.size(); i++) {
			urlParameters += GetParamaters.get(i).name + "=" + GetParamaters.get(i).value + "&";
		}
		
		URL += urlParameters;
		
		URL obj = new URL(URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
	
		
		int responseCode = con.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();

			// print result
			GetParamaters.clear();
			return response.toString();

		} else {
			GetParamaters.clear();
			throw new Exception("error");
		}

		
	}
	
	
}
