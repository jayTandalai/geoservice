package jay.geocode.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "geoloc")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
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
public class GeoLoc implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "lat")
	@Min(-85)
      @Max(85)
	private String lat;

	@Column(name = "lon")
	@Min(-180)
      @Max(180)
	private String lon;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
