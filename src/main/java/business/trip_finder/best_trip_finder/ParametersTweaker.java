package business.trip_finder.best_trip_finder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.trip_finder.TripParameters;
import business.trip_finder.filter.FilterParameters;

/**
 * From a {@link FilterParameters} instance, return new ones with parameters a
 * bit changed.
 * 
 * @author Aldric Vitali Silvestre
 */
public class ParametersTweaker {

	private static final BigDecimal PRICE_TWEAK_RATIO = BigDecimal.valueOf(0.1);
	private static final double COMFORT_TWEAK_RATIO = 0.3;

	public List<TripParameters> tweak(TripParameters parameters) {
		// Generate lists of prices and comforts
		PriceRange basePriceRange = new PriceRange(parameters.getMinPrice(), parameters.getMaxPrice());
		List<PriceRange> prices = List.of(
				basePriceRange,
				basePriceRange.addRatio(PRICE_TWEAK_RATIO),
				basePriceRange.substractRatio(PRICE_TWEAK_RATIO));

		double baseComfort = parameters.getComfort();
		double ratioComfort = baseComfort * COMFORT_TWEAK_RATIO;
		List<Double> comforts = List.of(
				baseComfort,
				baseComfort + ratioComfort,
				baseComfort - ratioComfort
				);
		// Things that doesn't change
		String filterBy = parameters.getFilterBy();
		String keywords = parameters.getKeywords();
		Integer nbDays = parameters.getNbDays();
		// Create this list !
		List<TripParameters> parameterList = new ArrayList<>();
		for (PriceRange priceRange : prices) {
			for (double comfort : comforts) {
				TripParameters newParameters = new TripParameters(
						priceRange.getMinPrice(),
						priceRange.getMaxPrice(),
						comfort,
						keywords,
						nbDays,
						filterBy);
				parameterList.add(newParameters);
			}
		}
		return parameterList;
	}
}
