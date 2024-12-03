package business.trip.places;

public class Position {
	private double latitude;
	private double longitude;

	public Position(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * This method implements the Haversine formula to calculate the distance
	 * between two points
	 * from latitude and longitude.
	 * 
	 * @return double the distance in kilometers
	 */
	public double computeDistance(Position arrival) {
		double departure_long = longitude;
		double departure_lat = latitude;
		double arrival_long = arrival.getLongitude();
		double arrival_lat = arrival.getLatitude();

		double a = Math.pow(Math.sin((arrival_lat - departure_lat) / 2), 2)
				+ Math.cos(departure_lat) * Math.cos(arrival_lat) * Math.pow(Math.sin((arrival_long - departure_long) / 2), 2);

		double c = 2 * Math.atan(Math.sqrt(a) / (Math.sqrt(1 - a)));

		double distance = 6371 * c; // distance in meters

		distance = distance / 1000; // distance in km

		return distance;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
