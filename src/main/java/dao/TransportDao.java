package dao;

import java.util.Iterator;

import business.trip.transports.Transport;

public interface TransportDao {
	
	Iterator<Transport> findAll();
	
	Iterator<Transport> findAquaticTransports();
	
	Iterator<Transport> findLandTransports();
}
