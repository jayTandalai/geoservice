package jay.geotest.service;

import java.util.List;

import javax.ws.rs.core.Response;

import jay.geotest.core.GeoLoc;
import jay.geotest.db.GeoLocationDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeoLocationService {

	final static Logger logger = LoggerFactory.getLogger(GeoLocationService.class);
	private static final String DEFAULT_SUFFIX = " task has been marked as done";

	private GeoLocationDAO dao;
	
	public GeoLocationService(GeoLocationDAO dao) {
		this.dao=dao;
	}

	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public GeoLoc get(float lat, float lon) {
		// TODO Auto-generated method stub
		return dao.get(lat, lon);
	}

	public long save(GeoLoc geoloc) {
		long id = dao.create(geoloc);
		return id;
	}

	public List<GeoLoc> getAll() {
		List<GeoLoc> geolocs = dao.findAll();
		return geolocs;
	}
}
