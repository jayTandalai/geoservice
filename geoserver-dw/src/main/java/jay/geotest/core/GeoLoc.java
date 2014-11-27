package jay.geotest.core;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "geoloc")
@NamedQueries({
        @NamedQuery(
                name = "jay.geotest.core.GeoLoc.findAll",
                query = "SELECT g FROM GeoLoc g"
        ),
        @NamedQuery(
                name = "jay.geotest.core.GeoLoc.findByLatLon",
                query = "SELECT g FROM GeoLoc g where g.lat=:lat and g.lon=:lon"
        )
})
public class GeoLoc {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "lat", nullable = false)
    private double lat;

    @Column(name = "lon", nullable = false)
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