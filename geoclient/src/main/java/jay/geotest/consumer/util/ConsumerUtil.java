package jay.geotest.consumer.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jay.geotest.consumer.entity.City;

import org.supercsv.cellprocessor.FmtBool;
import org.supercsv.cellprocessor.FmtNumber;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class ConsumerUtil {

	/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::                                                                         :*/
	/*::  This routine calculates the distance between two points (given the     :*/
	/*::  latitude/longitude of those points). It is being used to calculate     :*/
	/*::  the distance between two locations using GeoDataSource (TM) prodducts  :*/
	/*::                                                                         :*/
	/*::  Definitions:                                                           :*/
	/*::    South latitudes are negative, east longitudes are positive           :*/
	/*::                                                                         :*/
	/*::  Passed to function:                                                    :*/
	/*::    lat1, lon1 = Latitude and Longitude of point 1 (in decimal degrees)  :*/
	/*::    lat2, lon2 = Latitude and Longitude of point 2 (in decimal degrees)  :*/
	/*::    unit = the unit you desire for results                               :*/
	/*::           where: 'M' is statute miles                                   :*/
	/*::                  'K' is kilometers (default)                            :*/
	/*::                  'N' is nautical miles                                  :*/
	/*::  Worldwide cities and other features databases with latitude longitude  :*/
	/*::  are available at http://www.geodatasource.com                          :*/
	/*::                                                                         :*/
	/*::  For enquiries, please contact sales@geodatasource.com                  :*/
	/*::                                                                         :*/
	/*::  Official Web site: http://www.geodatasource.com                        :*/
	/*::                                                                         :*/
	/*::           GeoDataSource.com (C) All Rights Reserved 2014                :*/
	/*::                                                                         :*/
	/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

	public double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
	  double theta = lon1 - lon2;
	  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	  dist = Math.acos(dist);
	  dist = rad2deg(dist);
	  dist = dist * 60 * 1.1515;
	  if (unit == 'K') {
	    dist = dist * 1.609344;
	  } else if (unit == 'N') {
	  	dist = dist * 0.8684;
	    }
	  return (dist);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::  This function converts decimal degrees to radians             :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private double deg2rad(double deg) {
	  return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::  This function converts radians to decimal degrees             :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private double rad2deg(double rad) {
	  return (rad * 180 / Math.PI);
	}


	/*
	 * Sets up the processors used for the examples. There are 10 CSV columns, so 10 processors are defined. All values
	 * are converted to Strings before writing (there's no need to convert them), and null values will be written as
	 * empty columns (no need to convert them to "").
	 * 
	 * @return the cell processors
	 * */
	public static CellProcessor[] getProcessors(Map<String, City> cities) {
		final List<CellProcessor> processors = new ArrayList<CellProcessor>();
		processors.add(new NotNull()); // lat
		processors.add(new NotNull()); // lon
		processors.add(new Optional(new FmtBool("Y", "N"))); // inUS

		for (Map.Entry<String, City> entry : cities.entrySet()) {
			processors.add(new Optional(new FmtBool("Y", "N"))); // is the specific city or not
			processors.add(new Optional(new FmtNumber(new DecimalFormat()))); // the distance 
		}
		return processors.toArray(new CellProcessor[processors.size()]);
	}
	
	public static void main(String[] args) {
		ConsumerUtil cu = new ConsumerUtil();
		System.out.println(cu.distance(32.9697, -96.80322, 29.46786, -98.53506, 'M') + " Miles\n");
		System.out.println(cu.distance(32.9697, -96.80322, 29.46786, -98.53506, 'K') + " Kilometers\n");
		System.out.println(cu.distance(32.9697, -96.80322, 29.46786, -98.53506, 'N') + " Nautical Miles\n");
	}
}
