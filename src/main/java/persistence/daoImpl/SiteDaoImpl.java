package persistence.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import business.trip.places.Position;
import business.trip.places.Site;
import dao.SiteDao;
import persistence.apiBDe.request.RequestImpl;
import persistence.apiBDe.request.RequestManager;

public class SiteDaoImpl implements SiteDao {

	private RequestManager manager;

	public SiteDaoImpl() {
		this.manager = new RequestImpl();
	}

	@Override
	public Iterator<Site> findAll() {
		String request = "SELECT name, ST_X(position) as lat , ST_Y(position) as lon, price, isSeaSided, isintoSea, type from site";
		Iterator<Map<String, Object>> result = manager.request(request);

		List<Site> siteList = new ArrayList<Site>();

		while (result.hasNext()) {
			Map<String, Object> elt = result.next();
			String name = (String) elt.get("name");

			Position pos = new Position((double) elt.get("lat"), (double) elt.get("lon"));
			BigDecimal price = BigDecimal.valueOf((float)elt.get("price"));
			boolean isSeaSided = (Boolean) elt.get("isSeaSided");
			boolean isIntoSea = (Boolean) elt.get("isIntoSea");

			Site site = new Site(name, pos, price, isSeaSided, isIntoSea);

			siteList.add(site);
		}
		return siteList.iterator();
	}

	@Override
	public List<Site> findByRelevance(String query) {
		if (query.isBlank()) {
			List<Site> list = new ArrayList<>();
			this.findAll().forEachRemaining(list::add);
			return list;
		}
		String request = "SELECT name, ST_X(position) as lat , ST_Y(position) as lon, price, isSeaSided, isintoSea, type FROM site with " + query;
		Iterator<Map<String, Object>> result = manager.request(request);

		List<Site> siteList = new ArrayList<Site>();

		while (result.hasNext()) {
			Map<String, Object> elt = result.next();
			String name = (String) elt.get("name");

			Position pos = new Position((double) elt.get("lat"), (double) elt.get("lon"));
			BigDecimal price = BigDecimal.valueOf((float)elt.get("price"));
			boolean isSeaSided = (Boolean) elt.get("isSeaSided");
			boolean isIntoSea = (Boolean) elt.get("isIntoSea");

			Site site = new Site(name, pos, price, isSeaSided, isIntoSea);
			
			siteList.add(site);
		}
		return siteList;
	}

}
