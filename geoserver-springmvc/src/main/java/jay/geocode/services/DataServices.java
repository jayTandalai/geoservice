package jay.geocode.services;

import java.util.List;

import jay.geocode.model.GeoLoc;


public interface DataServices {

	public long save(GeoLoc geoloc);

	public GeoLoc get(double lat, double lon);

	public List<GeoLoc> getAll() ;

}
