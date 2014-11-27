package jay.geotest.service.external.google;

import java.io.IOException;

import jay.geotest.service.external.GeocodingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;

public class GoogleGeocodingService implements GeocodingService {
	
	final static Logger logger = LoggerFactory.getLogger(GoogleGeocodingService.class);
	Geocoder geocoder;

	public GoogleGeocodingService(GoogleConfiguration googleConfiguration) {
		geocoder = new Geocoder();
	}

	@Override
	public boolean find(float lat, float lon, String destination) {
		GeocoderRequest geocoderRequest = 
				new GeocoderRequestBuilder().setAddress("Paris, France").setLanguage("en").getGeocoderRequest();
		try {
			GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
			logger.debug("Google response:" + geocoderResponse.toString());
		} catch (IOException e) {
			logger.debug("Exception in finding Geo coordinates using Google Maps client:", e);
			return false;
		}
		return true;
	}
}
