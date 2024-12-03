package persistence.apiBDe.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mysql.cj.jdbc.result.ResultSetMetaData;


/**
 * Implementation of Iterator that will be used to navigate through database
 * answer
 * 
 * @author nico
 *
 * @param <E>
 */
public class ResultIterator implements Iterator<Map<String, Object>> {

	private ResultSet resultSet;
	private Statement stmt;
	private boolean hasNext;

	@Override
	public boolean hasNext() {
		return hasNext;
	}

	public ResultIterator( Statement stmt) {
		this.stmt = stmt;
		try {
			resultSet = stmt.getResultSet();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		goToNext();
	}

	private void goToNext() {
        try {
        	this.hasNext = resultSet.next();
        } catch (SQLException e) {
            this.hasNext = false;
            try {
				stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    @Override
    public Map<String, Object> next() {
        ResultSetMetaData rsmd;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            rsmd = (ResultSetMetaData) resultSet.getMetaData();
            int nbCols = rsmd.getColumnCount();
            for (int i = 1; i <= nbCols; i++) {
                map.put(rsmd.getColumnName(i), resultSet.getObject(i));
            }
        	this.hasNext = resultSet.next();

            return map;
        } catch (SQLException e) {
        	System.out.println("error");
            return null;
        }
    }

}
