package tests.manual;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import persistence.apiBDe.database.DatabaseImpl;
import persistence.apiBDe.database.DatabaseInfos;
import persistence.apiBDe.database.DatabaseManager;

public class APITest {
	
	
	@Test
	public void manageDBTest() {
		DatabaseInfos infos = DatabaseInfos.getInstance();
		String table = "A";
		String indexcolumn = "B";
		String folder = "C";
		String path = ".";
		
		infos.setFolder(folder);
		infos.setTable(table);
		infos.setKeyColumn(indexcolumn);
		infos.setPath(path);
		
		DatabaseImpl infosRef = new DatabaseImpl();
		infosRef.manageDB(table, indexcolumn, folder);
		infosRef.setPath(path);
		DatabaseInfos infostest = infosRef.getInfos(); 
		assertEquals(infos.toString(), infostest.toString());
		
	}

	@Test
	public void addTextTest() {
		String table = "A";
		String indexcolumn = "B";
		String folder = "Test";
		String path = ".";

		DatabaseManager impl = new DatabaseImpl();
		impl.manageDB(table, indexcolumn, folder);
		impl.setPath(path);
		impl.addText("hfuezihfi oezpahfuipaezhfuiz aephfuopza ehfuo ipaegfp", "valeur");
		
		File file = new File(folder+"/valeur.txt");
		if(file.exists()) {
			assert(true);
		}
	}
	

/*	public void sqlRequestTest() {
		String table = "A";
		String indexcolumn = "B";
		String folder = "Test";
		
		DatabaseImpl impl = new DatabaseImpl();
		impl.manageDB(table, indexcolumn, folder);
		impl.createIndex();
		
		RequestImpl<PertinenceResult> req = new RequestImpl<PertinenceResult>();
		
		ResultIterator resIter =  req.sqlRequest("SELECT * FROM A");
		while(resIter.hasNext()) {
			PertinenceResult res = (PertinenceResult)resIter.next();
			System.out.println(res.getName() + " --> " + res.getScore());
		}
	}*/

}
