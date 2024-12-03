package business.trip_finder.orderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.trip.Trip;
import business.trip_finder.orderer.strategy.ComfortAscendingStrategy;
import business.trip_finder.orderer.strategy.ComfortDescendingStrategy;
import business.trip_finder.orderer.strategy.GlobalStrategy;
import business.trip_finder.orderer.strategy.PriceAscendingStrategy;
import business.trip_finder.orderer.strategy.PriceDescendingStrategy;

public class TripOrdererImpl implements TripOrderer {
	
	private Map<OrderingStrategyType, OrderingStrategy> orderingStrategy;
	
	public TripOrdererImpl() {
		orderingStrategy = new HashMap<OrderingStrategyType, OrderingStrategy>();
		ComfortAscendingStrategy cas = new ComfortAscendingStrategy();
		ComfortDescendingStrategy cds = new ComfortDescendingStrategy();
		PriceAscendingStrategy  pas = new PriceAscendingStrategy();
		PriceDescendingStrategy pds = new PriceDescendingStrategy();
		GlobalStrategy gs = new GlobalStrategy();
		orderingStrategy.put(OrderingStrategyType.COMFORT_ASCENDING, cas);
		orderingStrategy.put(OrderingStrategyType.COMFORT_DECSCENDING, cds);
		orderingStrategy.put(OrderingStrategyType.PRICE_ASCENDING, pas);
		orderingStrategy.put(OrderingStrategyType.PRICE_DECSCENDING, pds);
		orderingStrategy.put(OrderingStrategyType.GLOBAL, gs);
		
	}
	
	public List<Trip> orderTrips(List<Trip> trips, OrderingStrategyType type) {
		List<Trip> orderedTrip;
		switch (type) {
		case COMFORT_ASCENDING:
			orderedTrip = orderingStrategy.get(OrderingStrategyType.COMFORT_ASCENDING).orderTrip(trips);
			break;
		case COMFORT_DECSCENDING:
			orderedTrip = orderingStrategy.get(OrderingStrategyType.COMFORT_DECSCENDING).orderTrip(trips);
			break;
		case PRICE_ASCENDING:
			orderedTrip = orderingStrategy.get(OrderingStrategyType.PRICE_ASCENDING).orderTrip(trips);
			break;
		case PRICE_DECSCENDING:
			orderedTrip = orderingStrategy.get(OrderingStrategyType.PRICE_DECSCENDING).orderTrip(trips);
			break;
		case GLOBAL:
			orderedTrip = orderingStrategy.get(OrderingStrategyType.GLOBAL).orderTrip(trips);
			break;
		default:
			orderedTrip = null;
			throw new NullPointerException("Wrong strategy given, please give the right strategy.");
		}
		return orderedTrip;
	}

}
