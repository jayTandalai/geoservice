package jay.geocode.dao;

import java.util.List;

import jay.geocode.controller.RestController;
import jay.geocode.model.GeoLoc;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class DataDaoImpl implements DataDao {
	static final Logger logger = Logger.getLogger(DataDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	public long create(GeoLoc geoloc) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		geoloc = (GeoLoc) session.save(geoloc);
		tx.commit();
		session.close();

		return geoloc.getId();
	}

	public GeoLoc get(double lat, double lon) throws Exception {
		session = sessionFactory.openSession();
		Query query = session.getNamedQuery("jay.geotest.core.GeoLoc.findByLatLon")
				.setDouble("lat", lat).setDouble("lon", lon);

		logger.info("lat:"+lat+" lon:"+lon);
		logger.info(query.toString());
		GeoLoc geoloc = (GeoLoc) query.uniqueResult();
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return geoloc;
	}

	@SuppressWarnings("unchecked")
	public List<GeoLoc> getAll() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<GeoLoc> employeeList = session.createCriteria(GeoLoc.class).list();
		tx.commit();
		session.close();
		return employeeList;
	}

}
