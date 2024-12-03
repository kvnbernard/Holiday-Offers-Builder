package persistence.apiBDe.database;

/**
 * This interface allow to communicate with the EDB(Extended Database) in order
 * to say which table and column will have the text files and where will they be
 * stored
 * 
 * @author nico
 *
 */
public interface DatabaseManager {

	/**
	 * Define the table and column that will be concerned by the EDB, and where will
	 * the files be stored
	 * 
	 * @param table       : String
	 * @param indexColumn : String
	 * @param folder      : String
	 * @return Boolean : True if method worked, False if not
	 */
	public void manageDB(String table, String indexColumn, String folder);

	/**
	 * Set the path where the EDB will be stored
	 * 
	 * @param path : String
	 */
	public void setPath(String path);

	/**
	 * Add a text to a specific value of the keyColumn
	 * 
	 * @param text     : String
	 * @param keyvalue : String
	 * @return Boolean : True if method worked, False if not
	 */
	public boolean addText(String text, String keyvalue);

	/**
	 * Create the index on the text files stored
	 * 
	 * @return Boolean : True if method worked, False if not
	 */
	public boolean createIndex();

}
