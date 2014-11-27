package jay.geotest.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jay.geotest.core.GeoLoc;
import jay.geotest.service.GeoLocationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

@Path("/geotest")
@Produces(MediaType.APPLICATION_JSON)
public class GeoLocationResource {

	final static Logger logger = LoggerFactory.getLogger(GeoLocationResource.class);

	private GeoLocationService geoTestService;

	public GeoLocationResource(GeoLocationService geoTestService) {
		this.geoTestService = geoTestService;
	}

	@GET
	@Timed
	@Path("/{lat}/{lon}")
	@UnitOfWork
	public Response getData(@PathParam("lat") float lat, @PathParam("lon") float lon) {
		logger.debug("lat-lon:"+ lat+"--"+lon);
		GeoLoc geoloc =  geoTestService.get(lat, lon);
		return Response.status(Response.Status.OK).entity(geoloc).build();
	}

	@GET
	@Timed
	@Path("/")
	@UnitOfWork
	public Response getAllDataSets() {
		List<GeoLoc> geolocs = geoTestService.getAll();
		return Response.status(Response.Status.OK).entity(geolocs).build();
	}

	@POST
	@Timed
	@UnitOfWork
	/*
	 * save Persists an entity. Will assign an identifier if one doesn't exist.
	 * 
	 * @param item
	 * 
	 * @return Response with 200 ok and Id of entity created
	 */
	public Response addData(GeoLoc geoloc) {
		long id = geoTestService.save(geoloc);
		if (id != 0l) {
			return Response.status(Response.Status.CREATED).entity(id).build();
		} else {
			return Response.notAcceptable(null).build();
		}
	}
}
