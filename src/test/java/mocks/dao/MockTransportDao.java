package mocks.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import business.trip.transports.AquaticTransport;
import business.trip.transports.LandTransport;
import business.trip.transports.Transport;
import dao.TransportDao;

public class MockTransportDao implements TransportDao {

	private final List<Transport> transports = List.of(
			new LandTransport("Autobus", BigDecimal.valueOf(0.10), 50, 3.5),
			new LandTransport("Feet", BigDecimal.valueOf(0), 7, 1.2),
			new AquaticTransport("Boat", BigDecimal.valueOf(0.20), 70, 4.0)
			);
	
	@Override
	public Iterator<Transport> findAll() {
		return transports.iterator();
	}

	@Override
	public Iterator<Transport> findAquaticTransports() {
		return transports.stream()
				.filter(this::isAquatic)
				.iterator();
	}

	@Override
	public Iterator<Transport> findLandTransports() {
		return transports.stream()
				.filter(this::isLand)
				.iterator();
	}

	public boolean isAquatic(Transport transport) {
		return transport.canCrossSea();
	}
	
	public boolean isLand(Transport transport) {
		return !transport.canCrossSea();
	}
}
