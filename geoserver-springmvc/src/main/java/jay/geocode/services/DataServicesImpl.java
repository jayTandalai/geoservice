package jay.geocode.services;

import java.util.ArrayList;
import java.util.List;

import jay.geocode.controller.RestController;
import jay.geocode.dao.DataDao;
import jay.geocode.model.GeoLoc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class DataServicesImpl implements DataServices {
	static final Logger logger = Logger.getLogger(DataServicesImpl.class);

	@Autowired
	DataDao dataDao;

	@Override
	public long save(GeoLoc geoloc) {
		try {
			return dataDao.create(geoloc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return 0;
	}

	@Override
	public GeoLoc get(double lat, double lon) {
		try {
			logger.info("lat:"+lat+" lon:"+lon);
			return dataDao.get(lat, lon);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return new GeoLoc();
	}

	@Override
	public List<GeoLoc> getAll() {
		try {
			return dataDao.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return new ArrayList<GeoLoc>();
	}

}