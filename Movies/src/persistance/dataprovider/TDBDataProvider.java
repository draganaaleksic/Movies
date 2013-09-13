package persistance.dataprovider;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;

public class TDBDataProvider implements DataProvider {
	
	private static final String dir = "C:\\Users\\TOSHIBA\\workspace\\Movies\\tdb";
	private Dataset dataset;
	
	public TDBDataProvider() {
		dataset = TDBFactory.createDataset(dir);
	}

	public Model getDataModel() {
		return dataset.getDefaultModel();
	}


	public void close() {
		dataset.close();
	}

	
	public Dataset getDataset() {
		return dataset;
	}
}
