package persistence.apiBDe.request;

import java.util.Iterator;
import java.util.Map;

/**
 * This interface allow the user to execute all his SQL request and get the
 * response as an Iterator
 * 
 * @author nico
 *
 * @param <E>
 */
public interface RequestManager {

	/**
	 * Execute an SQL Code and get the response as an Iterator
	 * 
	 * @param request : String - The SQL Request
	 * @return Iterator
	 */
	public Iterator<Map<String, Object>> request(String request);

}
