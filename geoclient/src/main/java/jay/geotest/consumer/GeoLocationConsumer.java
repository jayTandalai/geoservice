package jay.geotest.consumer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jay.geotest.consumer.entity.City;
import jay.geotest.consumer.entity.GeoLoc;
import jay.geotest.consumer.entity.OpenMapQuestResponse;
import jay.geotest.consumer.util.ConsumerUtil;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class GeoLocationConsumer {
	private static final String LOCAL_GEOLOC_LIST = "http://localhost:8080/geotest/";
	private static final String OPEN_MAPQUEST_URI = "http://open.mapquestapi.com/nominatim/v1/reverse.php";
	private static final String US = "us";
	private static final String JSON = "json";
	private static ICsvListWriter listWriter = null;
	private List<String> header = null;
	private CellProcessor[] processors = null;
	private ConsumerUtil consumerUtil;

	// static list of cities to compute distance from
	private static final Map<String, City> cities;
	static {
		cities = new HashMap<String, City>();
		cities.put("Tokyo", new City("Tokyo", "Japan", 35.685, 139.7513889));
		cities.put("Sydney", new City("Sydney", "Australia", -33.8833333,
				151.2166667));
		cities.put("Riyadh", new City("Riyadh", "Saudi Arabia", 24.6408333,
				46.7727778));
		cities.put("Zurich",
				new City("Zurich", "Switzerland", 47.3666667, 8.55));
		cities.put("Reykjavik", new City("Reykjavik", "Iceland", 64.15, -21.95));
		cities.put("Mexico City", new City("Mexico City", "Mexico", 19.4284700,
				-99.1276600));
		cities.put("Lima", new City("Lima", "Peru", -12.05, -77.05));
	}

	public GeoLocationConsumer() throws IOException {
		listWriter = new CsvListWriter(new FileWriter("./output.csv"),
				CsvPreference.STANDARD_PREFERENCE);

		// the header elements are used to map the bean values to each
		// column (names must match)
		header = new ArrayList();
		header.add("lat");
		header.add("lon");
		header.add("inUS");

		// add additional header fields for maintaining the distance from the
		// static list of countries above
		for (Map.Entry<String, City> entry : cities.entrySet()) {
			header.add("Within 500mi of " + entry.getKey());
			header.add("Distance_from_" + entry.getKey() + "(in mi)");
		}
		processors = consumerUtil.getProcessors(cities);

		// write the header
		listWriter.writeHeader(header.toArray(new String[header.size()]));

		consumerUtil = new ConsumerUtil();
	}

	public void consume() throws NumberFormatException, IOException, InterruptedException {
		boolean inUS;
		double specificCityLat=0.0, specificCityLon=0.0, distance=0;
		List<Object> geoDetails= new ArrayList<Object>();
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		Client client = Client.create(clientConfig);

		WebResource geoLocalResource = client.resource(LOCAL_GEOLOC_LIST);
		WebResource mapQuestResource = null;

		ClientResponse geoLocalResponse = geoLocalResource.accept(
				"application/json").get(ClientResponse.class);
		ClientResponse openMapQuestResponse = null;

		if (geoLocalResponse.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ geoLocalResponse.getStatus());
		}

		List<GeoLoc> geolocs = geoLocalResponse
				.getEntity(new GenericType<List<GeoLoc>>() {
				});

		int i = 0;
		for (GeoLoc geoloc : geolocs) {
			if(i>=9850) {
			// call mapquest to find if in USA
			mapQuestResource = client.resource(OPEN_MAPQUEST_URI)
					.queryParam("format", JSON)
					.queryParam("lat", Double.toString(geoloc.getLat()))
					.queryParam("lon", Double.toString(geoloc.getLon()));
			System.out.println("Querying "+i+" open mapquest for:"
					+ mapQuestResource.getURI().toString());
			openMapQuestResponse = mapQuestResource.accept("application/json")
					.get(ClientResponse.class);
			if (openMapQuestResponse.getStatus() != 200) {
				System.out.println("Failed : HTTP error code : "
						+ openMapQuestResponse.getStatus());
			}
			OpenMapQuestResponse openMapQuest = openMapQuestResponse
					.getEntity(OpenMapQuestResponse.class);

			// is the place within US
			// if open mapquest gives a null, then assuming the place
			// is not in US and computing distance
			if(openMapQuest.getAddress()==null) {
				inUS = false;
			} else {
				inUS = openMapQuest.getAddress().getCountryCode().equalsIgnoreCase(US);
			}

			// add common fields to the list object to be 
			// populated in the csv
			geoDetails.clear();
			geoDetails.add(geoloc.getLat());
			geoDetails.add(geoloc.getLon());
			geoDetails.add(inUS);
			
			if (!inUS) {
				// find distance from the static list of countries
				for (Map.Entry<String, City> entry : cities.entrySet()) {
					specificCityLat = entry.getValue().getLat();
					specificCityLon = entry.getValue().getLon();
					distance = consumerUtil.distance(
						geoloc.getLat(), 
						geoloc.getLon(), 
						specificCityLat, specificCityLon, 'M');
					geoDetails.add(distance > 0 && distance < 500);
					geoDetails.add(distance);
					
				}
			} else {
				// find distance from the static list of countries
				for (Map.Entry<String, City> entry : cities.entrySet()) {
					geoDetails.add(null);
					geoDetails.add(null);
				}
			}
			// write to csv
			listWriter.write(geoDetails, processors);
			if(i%500 ==0) {
				listWriter.flush();
				Thread.sleep(2000);
			}
			}
			i++;
		}
	}
	
	public static void main(String[] args) 
		throws IOException, NumberFormatException, InterruptedException {
		try {
			GeoLocationConsumer gs = new GeoLocationConsumer();
			gs.consume();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (listWriter != null) {
				listWriter.close();
			}
		}

	}
}
