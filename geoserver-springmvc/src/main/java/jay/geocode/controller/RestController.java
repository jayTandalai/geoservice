package jay.geocode.controller;

import java.util.List;

import jay.geocode.model.GeoLoc;
import jay.geocode.services.DataServices;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/geotest")
public class RestController {

	@Autowired
	DataServices dataServices;

	static final Logger logger = Logger.getLogger(RestController.class);

	/* Submit form in Spring Restful Services */
	@RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	long addData(@RequestBody GeoLoc geoloc) {
		try {
			long id = dataServices.save(geoloc);
			if (id != 0l) {
				return id;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;

	}

	/* Ger a single objct in Json form in Spring Rest Services */
	@RequestMapping(value = "/{lat}{lon}", method = RequestMethod.GET)
	public @ResponseBody
	GeoLoc getData(@PathVariable("lat") double lat,
			@PathVariable("lon") double lon) {
		GeoLoc geoloc = null;
		try {
			logger.info("lat:"+lat+" lon:"+lon);
			geoloc = dataServices.get(lat, lon);

		} catch (Exception e) {
			logger.error(e);
		}
		return geoloc;
	}

	/* Getting List of objects in Json format in Spring Restful Services */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody
	List<GeoLoc> getAll() {

		List<GeoLoc> geolocs = null;
		try {
			geolocs = dataServices.getAll();

		} catch (Exception e) {
			logger.error(e);
		}

		return geolocs;
	}
}
