package JackeLibrary;

public class userInfoClass {
	
	public location getLocation() {
		$.network.getInfo();
		return $.network.ipInfo.locationInfo;
	}
	
	public String getPostalCode() {
		$.network.getInfo();
		return $.network.ipInfo.postalcode;
	}
	
	public String getCity() {
		$.network.getInfo();
		return $.network.ipInfo.city;
	}
	
	public String getRegion() {
		$.network.getInfo();
		return $.network.ipInfo.region;
	}
	
	public String getCountry() {
		$.network.getInfo();
		return $.network.ipInfo.country;
	}
	
	
	
}
