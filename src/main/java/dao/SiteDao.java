package dao;

import java.util.Iterator;
import java.util.List;

import business.trip.places.Site;

public interface SiteDao {

	Iterator<Site> findAll();
	
	List<Site> findByRelevance(String query);
}
