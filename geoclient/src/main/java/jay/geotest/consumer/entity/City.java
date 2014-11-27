package jay.geotest.consumer.entity;

public class City {
	private String name;
	private String country;
	private double lat;
	private double lon;
	
	public City(String name, String country, double lat, double lon) {
		this.name=name;
		this.country=country;
		this.lat=lat;
		this.lon=lon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
}
