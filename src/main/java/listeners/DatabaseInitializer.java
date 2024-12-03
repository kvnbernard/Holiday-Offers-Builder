package listeners;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.spring.SpringIoC;
import business.trip_finder.best_trip_finder.BestTripFinder;
import persistence.apiBDe.database.DatabaseManager;
import persistence.apiBDe.request.JdbcConnection;
import persistence.config.BdeConfig;
import persistence.config.JdbcConfig;
import persistence.config.LuceneConfig;

/**
 * The class responsible for createing Extended databse system and adding text
 * files.
 * 
 * @author Aldric Vitali Silvestre
 */
public class DatabaseInitializer {
	
	private JdbcConfig jdbcConfig;
	private BdeConfig bdeConfig;
	private DatabaseManager databaseManager;
	private LuceneConfig luceneConfig;

	public void init() {
		setupConfig();
		insertTextes();
		launchIndexation();
	}
	
	private void setupConfig() {
		JdbcConnection.setConfig(jdbcConfig);
		databaseManager.manageDB(bdeConfig.getTable(), bdeConfig.getIndexColumn(), bdeConfig.getFolder());
		databaseManager.setPath(luceneConfig.getPathIndex());
	}
	
	private void insertTextes() {
		ClassLoader classLoader = getClass().getClassLoader();
		URI uri;
		try {
			uri = classLoader.getResource("files").toURI();
		} catch (URISyntaxException exception) {
			throw new RuntimeException(exception);
		}
		File filesDirectory = Path.of(uri).toFile();
		Arrays.stream(filesDirectory.listFiles())
			.filter(File::isFile)
			.forEach(this::insertFile);
	}
	
	private void launchIndexation() {
		if (!databaseManager.createIndex()) {
			throw new RuntimeException("Error happened during indexation");
		}
	}

	private void insertFile(File file) {
		// We have only one '.' in our file names
		String name = file.getName().replace(".txt", "");
		String content;
		try {
			content = Files.readString(file.toPath());
		} catch (IOException exception) {
			String message = "Cannot read file content : " + exception.getMessage();
			throw new RuntimeException(message);
		}
		databaseManager.addText(content, name);
	}
	
	public JdbcConfig getJdbcConfig() {
		return jdbcConfig;
	}

	public void setJdbcConfig(JdbcConfig jdbcConfig) {
		this.jdbcConfig = jdbcConfig;
	}

	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}

	public void setDatabaseManager(DatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}

	public LuceneConfig getLuceneConfig() {
		return luceneConfig;
	}

	public void setLuceneConfig(LuceneConfig luceneConfig) {
		this.luceneConfig = luceneConfig;
	}

	public BdeConfig getBdeConfig() {
		return bdeConfig;
	}

	public void setBdeConfig(BdeConfig bdeConfig) {
		this.bdeConfig = bdeConfig;
	}
}
