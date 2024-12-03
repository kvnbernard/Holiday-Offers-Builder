package persistence.config;

public class BdeConfig {

	private String table;
	
	private String indexColumn;
	
	private String folder;

	public BdeConfig() {
		super();
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getIndexColumn() {
		return indexColumn;
	}

	public void setIndexColumn(String indexColumn) {
		this.indexColumn = indexColumn;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
}
