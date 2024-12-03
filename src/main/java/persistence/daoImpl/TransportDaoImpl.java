package persistence.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import business.trip.places.Position;
import business.trip.places.Site;
import business.trip.transports.AquaticTransport;
import business.trip.transports.LandTransport;
import business.trip.transports.Transport;
import dao.TransportDao;
import persistence.apiBDe.request.RequestImpl;
import persistence.apiBDe.request.RequestManager;

public class TransportDaoImpl implements TransportDao{
	
	private RequestManager manager;
	
	public TransportDaoImpl() {
		this.manager = new RequestImpl();
	}

	@Override
	public Iterator<Transport> findAll() {
		String request = "SELECT name, pricePerKm, speed, comfort from transport";
		Iterator<Map<String, Object>> result = manager.request(request);

		List<Transport> transportList = new ArrayList<Transport>();

		while (result.hasNext()) {
			Map<String, Object> elt = result.next();
			String name = (String) elt.get("name");

			BigDecimal price = BigDecimal.valueOf((double) Float.parseFloat(elt.get("pricePerKm").toString()));
			int speed = Math.round((float) elt.get("speed"));
			float icomfort =  (float) elt.get("comfort");
			double dcomfort = Double.valueOf(icomfort);
			Transport transport = null;
			if(name.contentEquals("boat")) {
				transport = new AquaticTransport(name, price, speed, dcomfort);
			}
			else if(name.contentEquals("bus") || name.contentEquals("on foot")) {
				transport = new LandTransport(name, price, speed, dcomfort);
			}
			

			transportList.add(transport);
		}
		return transportList.iterator();
	}

	@Override
	public Iterator<Transport> findAquaticTransports() {
		String request = "SELECT name, pricePerKm, speed, comfort from transport";
		Iterator<Map<String, Object>> result = manager.request(request);

		List<Transport> transportList = new ArrayList<Transport>();

		while (result.hasNext()) {
			Map<String, Object> elt = result.next();
			String name = (String) elt.get("name");

			BigDecimal price =BigDecimal.valueOf((float)elt.get("pricePerKm"));
			int speed = Math.round((float) elt.get("speed"));
			float icomfort =  (float) elt.get("comfort");
			double dcomfort = Double.valueOf(icomfort);
			Transport transport = null;
			if(name.contentEquals("boat")) {
				transport = new AquaticTransport(name, price, speed, dcomfort);
				transportList.add(transport);

			}

		}
		return transportList.iterator();
	}

	@Override
	public Iterator<Transport> findLandTransports() {
		String request = "SELECT name, pricePerKm, speed, comfort from transport";
		Iterator<Map<String, Object>> result = manager.request(request);

		List<Transport> transportList = new ArrayList<Transport>();

		while (result.hasNext()) {
			Map<String, Object> elt = result.next();
			String name = (String) elt.get("name");

			BigDecimal price =BigDecimal.valueOf((float)elt.get("pricePerKm"));
			int speed = Math.round((float) elt.get("speed"));
			float icomfort =  (float) elt.get("comfort");
			double dcomfort = Double.valueOf(icomfort);			
			Transport transport = null;
			if(name.contentEquals("bus") || name.contentEquals("on foot")) {
				transport = new LandTransport(name, price, speed, dcomfort);
				transportList.add(transport);

			}
			

		}
		return transportList.iterator();
	}

}
