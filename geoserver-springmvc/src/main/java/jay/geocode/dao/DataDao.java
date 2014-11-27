package jay.geocode.dao;

import java.util.List;

import jay.geocode.model.GeoLoc;

public interface DataDao {

	public long create(GeoLoc geoloc) throws Exception;

	public GeoLoc get(double lat, double lon) throws Exception;

	public List<GeoLoc> getAll() throws Exception;
}