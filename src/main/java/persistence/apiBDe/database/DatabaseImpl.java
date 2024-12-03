package persistence.apiBDe.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.Spring;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import business.spring.SpringIoC;
import persistence.config.LuceneConfig;

public class DatabaseImpl implements DatabaseManager {

	// --- Variable ---

	private DatabaseInfos infos = DatabaseInfos.getInstance();

	// --- Methods ---

	public DatabaseImpl() {

	}

	@Override
	public void manageDB(String table, String indexColumn, String folder) {
		infos.setValues(table, indexColumn, folder);
	}

	@Override
	public boolean addText(String text, String keyvalue) {
		try {
			File directory = Path.of(infos.getPath(), infos.getFolder()).toFile();
			if (!directory.exists()) {
				directory.mkdirs();
			}
			try(BufferedWriter writer = new BufferedWriter(
					new FileWriter(Path.of(infos.getPath(), infos.getFolder(), keyvalue + ".txt").toFile()))) {
				writer.write(text);
			}
		} catch (IOException e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean createIndex() {

		try {
			Analyzer analyser = new StandardAnalyzer();

			File f = Path.of(infos.getPath(), infos.getFolder() + "index").toFile();
			if (f.exists()) {
				for (File elt : f.listFiles())
					elt.delete();
			}
			Directory index = FSDirectory.open(f.toPath());

			IndexWriterConfig config = new IndexWriterConfig(analyser);
			IndexWriter writer = new IndexWriter(index, config);

			File folder = Path.of(infos.getPath(), infos.getFolder()).toFile();
			File[] listOfFiles = folder.listFiles();

			for (File textFile : listOfFiles) {
				File file = Path.of(infos.getPath(), infos.getFolder(), textFile.getName()).toFile();

				Document document = new Document();
				document.add(new Field("name", file.getName(), TextField.TYPE_STORED));
				document.add(new Field("content", new FileReader(file), TextField.TYPE_NOT_STORED));
				writer.addDocument(document);

			}
			writer.close();

		} catch (IOException e) {
			return false;
		}

		return true;
	}

	public DatabaseInfos getInfos() {
		return infos;
	}

	public void setInfos(DatabaseInfos infos) {
		this.infos = infos;
	}

	@Override
	public void setPath(String path) {
		infos.setPath(path);
	}

}
