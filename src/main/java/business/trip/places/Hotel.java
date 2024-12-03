package business.trip.places;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel extends Place {

	private double comfort;
	private int nbPrestation;
	private List<Prestation> prestations;

	public Hotel(String name, Position position, BigDecimal price, List<Prestation> prestations) throws IllegalArgumentException {
		super(name, position, price, true, false);

		this.nbPrestation = prestations.size();
		this.prestations = prestations;
		this.comfort = calculateComfort();
	}

	private double calculateComfort() {
		// The comfort depends only on the number of prestations, and not the quality of
		// them (for now)
		double res;
		if (nbPrestation > 6) {
			res = 5.0;
		} else if (nbPrestation > 3) {
			res = 4.0;
		} else if (nbPrestation > 2) {
			res = 3.5;
		} else if (nbPrestation > 1) {
			res = 2.0;
		} else {
			res = 1.0;
		}
		return res;
	}

	public int getNbPrestation() {
		return nbPrestation;
	}

	public List<Prestation> getPrestations() {
		return prestations;
	}

	public double getComfort() {
		return comfort;
	}

	@Override
	public String toString() {
		return "[" + super.toString() + " - Comfort: " + comfort + " - NbPrestations:" + nbPrestation + ", Prestations:" + prestations + "]\n";
	}
	
	
}
