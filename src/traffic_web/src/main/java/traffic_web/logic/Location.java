package traffic_web.logic;

public class Location {

	private String postalCode;
	private String street;
	private String district;
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getDistrict() {
		return district;
	}
}
