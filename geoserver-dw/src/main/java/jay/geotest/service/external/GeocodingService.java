package jay.geotest.service.external;

public interface GeocodingService {
	public boolean find(float lat, float lon, String destination);
}
