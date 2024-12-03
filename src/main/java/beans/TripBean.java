package beans;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import business.trip.Day;
import business.trip.Trip;

@ManagedBean
@RequestScoped
public class TripBean {
	
	@ManagedProperty(value = "#{searchTripResultBean}")
	private SearchTripResultBean searchTripResultBean;
	
	private Trip trip;

	
	public void defineTrip(int index){
		this.setTrip(searchTripResultBean.getBestTrips().get(index));
	}
	
	public SearchTripResultBean getSearchTripResultBean() {
		return searchTripResultBean;
	}

	public void setSearchTripResultBean(SearchTripResultBean searchTripResultBean) {
		this.searchTripResultBean = searchTripResultBean;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	public BigDecimal getPrice(){
		return trip.getPrice();
	}

	public List<Day> getDays() {
		return trip.getDays();
	}
	
	public double getComfort() {
		return trip.getComfort();
	}

}
