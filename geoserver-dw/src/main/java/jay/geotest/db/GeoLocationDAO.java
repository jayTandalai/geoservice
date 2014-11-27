package jay.geotest.db;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import jay.geotest.core.GeoLoc;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeoLocationDAO extends AbstractDAO<GeoLoc> {
	final static Logger logger = LoggerFactory.getLogger(GeoLocationDAO.class);
    public GeoLocationDAO(SessionFactory factory) {
        super(factory);
    }

    public GeoLoc findById(Long id) {
        return get(id);
    }

    public long create(GeoLoc geoLocation) {
		GeoLoc lookedupGeoLoc = get(geoLocation.getLat(), geoLocation.getLon());
		if(lookedupGeoLoc!=null) {
			lookedupGeoLoc.setLat(geoLocation.getLat());
			lookedupGeoLoc.setLon(geoLocation.getLon());
		}
		return persist(geoLocation).getId();
    }

    public List<GeoLoc> findAll() {
        return list(namedQuery("jay.geotest.core.GeoLoc.findAll"));
    }

	public GeoLoc get(double d, double e) {
		logger.debug("geoloc - lat-lon:"+d+"--"+e);
		GeoLoc geoloc = 
				uniqueResult(namedQuery("jay.geotest.core.GeoLoc.findByLatLon")
				.setParameter("lat", d)
				.setParameter("lon", e)
				);
		return geoloc;
	}
}