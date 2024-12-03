package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import business.trip.Trip;
import mocks.MockBestTripFinder;

@ManagedBean
@RequestScoped
public class SearchTripResultBean {

	@ManagedProperty(value = "#{tripEntryBean}")
	private TripEntryBean tripEntryBean;

	public SearchTripResultBean() {
		
	}
	
	public int indexOf(Trip trip) {
		return tripEntryBean.getBestTrips().indexOf(trip);
	}

	public TripEntryBean getTripEntryBean() {
		return tripEntryBean;
	}

	public void setTripEntryBean(TripEntryBean tripEntryBean) {
		this.tripEntryBean = tripEntryBean;
	}

	public List<Trip> getBestTrips() {
		return tripEntryBean.getBestTrips();
	}

	public void setBestTrips(List<Trip> bestTrips) {
		tripEntryBean.setBestTrips(bestTrips);
	}

}
