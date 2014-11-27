package jay.geotest.consumer.entity;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class GeoLoc {
	private Long id;

    private double lat;
    private double lon;

	@JsonProperty
	public long getId() {
		return id;
	}

	@JsonProperty
	public double getLat() {
		return lat;
	}

	@JsonProperty
	public double getLon() {
		return lon;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	@Override
	public boolean equals(Object o) {

		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Item or not "null instanceof [type]"
		 * also returns false
		 */
		if (!(o instanceof GeoLoc)) {
			return false;
		}

		// typecast o to Item so that we can compare data members
		GeoLoc that = (GeoLoc) o;

		// Compare the data members and return accordingly
		return Objects.equals(id, that.id) &&
                Objects.equals(this.lat, that.lat) &&
                Objects.equals(this.lon, that.lon) ;
	}

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lon);
    }
}
