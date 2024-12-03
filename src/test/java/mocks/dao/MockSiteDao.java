package mocks.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import business.trip.places.Position;
import business.trip.places.Site;
import dao.SiteDao;

public class MockSiteDao implements SiteDao {
	
	private final List<Site> sites = List.of(
			new Site("Site 1", new Position(-17.6394, -149.4229), BigDecimal.valueOf(59.99), true, true),
			new Site("Site 2", new Position(-16.6394, -148.2323), BigDecimal.valueOf(172.99), false, false),
			new Site("Site 3", new Position(-18.6394, -150.4229), BigDecimal.valueOf(30.0), false, false),
			new Site("Site 4", new Position(-17.0112, -149.1111), BigDecimal.valueOf(29.99), true, false),
			new Site("Site 5", new Position(-17.1111, -150.4229), BigDecimal.valueOf(333.33), false, false),
			new Site("Site 6", new Position(-17.1111, -150.4229), BigDecimal.valueOf(12.99), false, false),
			new Site("Site 7", new Position(-17.1111, -150.4229), BigDecimal.valueOf(0.00), false, false),
			new Site("Site 8", new Position(-17.3333, -148.4229), BigDecimal.valueOf(0.00), true, false));

	@Override
	public Iterator<Site> findAll() {
		return sites.iterator();
	}

	@Override
	public List<Site> findByRelevance(String query) {
		// Return first 3 sites
		return sites.stream()
				.collect(Collectors.toList());
	}

}
