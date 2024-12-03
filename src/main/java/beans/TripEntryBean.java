package beans;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import business.spring.SpringIoC;
import business.trip.Trip;
import business.trip_finder.TripParameters;
import business.trip_finder.best_trip_finder.BestTripFinder;

@ManagedBean
@SessionScoped
public class TripEntryBean {

	private String error = "";
	private List<Trip> bestTrips;
	private TripParameters tripParameters = new TripParameters();
	private BestTripFinder bestTripFinder = SpringIoC.getBean(BestTripFinder.class);
	
	public TripEntryBean() {
	}

	public String search() {
		try {
			tripParameters.validateParameters();
		} catch (Exception e) {
			this.setError(e.getMessage());
			return "index";
		}
		bestTrips = bestTripFinder.findBestTrips(tripParameters);
		return "result";
	}
	
	public BigDecimal getMaximumPrice() {
		return TripParameters.getMaximumPrice();
	}

	public BigDecimal getMinimumPrice() {
		return TripParameters.getMinimumPrice();
	}

	
	public int getMaximumNbDays() {
		return TripParameters.getMaximumNbDays();
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<Trip> getBestTrips() {
		return bestTrips;
	}

	public void setBestTrips(List<Trip> bestTrips) {
		this.bestTrips = bestTrips;
	}

	public TripParameters getTripParameters() {
		return tripParameters;
	}

	public void setTripParameters(TripParameters tripParameters) {
		this.tripParameters = tripParameters;
	}

	public BestTripFinder getBestTripFinder() {
		return bestTripFinder;
	}

	public void setBestTripFinder(BestTripFinder bestTripFinder) {
		this.bestTripFinder = bestTripFinder;
	}

	public BigDecimal getMinPrice() {
		return tripParameters.getMinPrice();
	}

	public void setMinPrice(BigDecimal minPrice) {
		tripParameters.setMinPrice(minPrice);
	}

	public BigDecimal getMaxPrice() {
		return tripParameters.getMaxPrice();
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		tripParameters.setMaxPrice(maxPrice);
	}

	public Double getComfort() {
		return tripParameters.getComfort();
	}

	public void setComfort(Double comfort) {
		tripParameters.setComfort(comfort);
	}

	public String getKeywords() {
		return tripParameters.getKeywords();
	}

	public void setKeywords(String keywords) {
		tripParameters.setKeywords(keywords);
		;
	}

	public Integer getNbDays() {
		return tripParameters.getNbDays();
	}

	public void setNbDays(Integer nbDays) {
		tripParameters.setNbDays(nbDays);
	}

	public String getFilterBy() {
		return tripParameters.getFilterBy();
	}

	public void setFilterBy(String filterBy) {
		tripParameters.setFilterBy(filterBy);
	}

	public void displayError() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(error));
	}

}
