package jay.geotest.consumer.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenMapQuestResponse {
	@JsonProperty("place_id")
	private String placeId;
	@JsonProperty("licence")
	private String licence;
	@JsonProperty("osm_type")
	private String osmType;
	@JsonProperty("osm_id")
	private String osmId;
	@JsonProperty("lat")
	private String lat;
	@JsonProperty("lon")
	private String lon;
	@JsonProperty("display_name")
	private String displayName;
	@JsonProperty("address")
	private Address address;

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getOsmType() {
		return osmType;
	}

	public void setOsmType(String osmType) {
		this.osmType = osmType;
	}

	public String getOsmId() {
		return osmId;
	}

	public void setOsmId(String osmId) {
		this.osmId = osmId;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Address {
		@JsonProperty("road")
		private String road;
		@JsonProperty("city")
		private String city;
		@JsonProperty("county")
		private String county;
		@JsonProperty("state")
		private String state;
		@JsonProperty("town")
		private String town;
		@JsonProperty("country")
		private String country;
		@JsonProperty("country_code")
		private String countryCode;
		@JsonProperty("continent")
		private String continent;
		@JsonProperty("village")
		private String village;
		@JsonProperty("state_district")
		private String stateDistrict;
		

		public String getRoad() {
			return road;
		}
		public void setRoad(String road) {
			this.road = road;
		}

		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}

		public String getCounty() {
			return county;
		}
		public void setCounty(String county) {
			this.county = county;
		}

		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
		public String getTown() {
			return town;
		}
		public void setTown(String town) {
			this.town = town;
		}

		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}

		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		
		public String getContinent() {
			return continent;
		}
		public void setContinent(String continent) {
			this.continent = continent;
		}
		
		public String getVillage() {
			return village;
		}
		public void setVillage(String village) {
			this.village = village;
		}
		public String getStateDistrict() {
			return stateDistrict;
		}
		public void setStateDistrict(String stateDistrict) {
			this.stateDistrict = stateDistrict;
		}
	}
}
