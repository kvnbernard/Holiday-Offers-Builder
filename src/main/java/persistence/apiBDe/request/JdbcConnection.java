package persistence.apiBDe.request;

import java.sql.Connection;
import java.sql.DriverManager;

import persistence.config.JdbcConfig;

public class JdbcConnection {
	private static String host = "localhost";
	private static String base = "database";
	private static String user = "root";
	private static String password = "";
	private static String url = "jdbc:mysql://" + host + "/" + base;

	/**
	 * Lazy singleton instance.
	 */
	private static Connection connection;


	public static Connection getConnection() {
		if (connection == null) {
			try {
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				System.err.println("Connection failed : " + e.getMessage());
				throw new RuntimeException(e);
			}
		}
		return connection;
	}
	
	public static void setConfig(JdbcConfig config) {
		JdbcConnection.base = config.getDatabaseName();
		JdbcConnection.host = config.getHost();
		JdbcConnection.user = config.getUsername();
		JdbcConnection.password = config.getPassword();
		JdbcConnection.url = "jdbc:mysql://" + host + "/" + base;
	}
}
