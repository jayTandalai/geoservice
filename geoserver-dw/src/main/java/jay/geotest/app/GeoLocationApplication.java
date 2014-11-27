package jay.geotest.app;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import jay.geotest.core.GeoLoc;
import jay.geotest.db.GeoLocationDAO;
import jay.geotest.resources.GeoLocationResource;
import jay.geotest.service.GeoLocationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeoLocationApplication extends Application<GeoLocationConfiguration> {
	final static Logger logger = LoggerFactory.getLogger(GeoLocationApplication.class);
	public static void main(String[] args) throws Exception {
		new GeoLocationApplication().run(args);
	}

	@Override
	public String getName() {
		return "GeoTest";
	}
	
	private final HibernateBundle<GeoLocationConfiguration> hibernate = 
			new HibernateBundle<GeoLocationConfiguration>(GeoLoc.class) {
	    @Override
	    public DataSourceFactory getDataSourceFactory(GeoLocationConfiguration configuration) {
	        return configuration.getDataSourceFactory();
	    }
	};


	@Override
	public void initialize(Bootstrap<GeoLocationConfiguration> bootstrap) {
		bootstrap.addBundle(hibernate);
	}

	@Override
	public void run(GeoLocationConfiguration configuration, Environment environment) {
	    final GeoLocationDAO dao = new GeoLocationDAO(hibernate.getSessionFactory());
		final GeoLocationService todoService = new GeoLocationService(dao);
		final GeoLocationResource todoResource = new GeoLocationResource(todoService);
	    
		environment.jersey().register(todoResource);
	}

}
