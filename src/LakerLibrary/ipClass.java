package LakerLibrary;

public class ipClass {

	public String ipAdress;
	public String Hostname;
	public String city;
	public String region;
	public String country;
	public location locationInfo;
	public String organization;
	public String postalcode;
	
	
	public ipClass(String ipAdress, String hostname, String city, String region, String country, location locationInfo,
			String organization, String postalcode) {
		
		this.ipAdress = ipAdress;
		this.Hostname = hostname;
		this.city = city;
		this.region = region;
		this.country = country;
		this.locationInfo = locationInfo;
		this.organization = organization;
		this.postalcode = postalcode;
	}
	
	
	
	
}
