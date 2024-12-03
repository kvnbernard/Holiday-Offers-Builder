package business.trip_finder.repository;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import business.trip.places.Hotel;
import business.trip.places.Place;
import business.trip.places.Site;

public class PlacesUnion {

	private List<Hotel> hotels;
	
	private List<Site> sites;

	public PlacesUnion(List<Hotel> hotels, List<Site> sites) {
		super();
		this.hotels = hotels;
		this.sites = sites;
	}
	
	/**
	 * Create a new list each call
	 * @return
	 */
	public List<Place> getPlaces() {
		return Stream.concat(hotels.stream(), sites.stream())
				.collect(Collectors.toList());
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	@Override
	public String toString() {
		StringBuilder sbHotels = new StringBuilder();
		StringBuilder sbSites = new StringBuilder();
		forEachAppendNewLine(hotels, sbHotels);
		forEachAppendNewLine(sites, sbSites);
		
		return "Hotels: \n\t" + sbHotels.toString() + "\n" 
		+ "-------------------------"
		+ "\n\nSites: \n\t" + sbSites.toString();
	}
	
	public static <T> void forEachAppendNewLine(List<T> list, StringBuilder sb) {
		Consumer<String> consumer = line -> {
			String str = line + "\n\t";
			sb.append(str);
		};
		
		for(T t : list) {
			consumer.accept(t.toString());
		}
	}
	
	
}
