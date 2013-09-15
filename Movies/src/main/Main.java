package main;


import com.hp.hpl.jena.tdb.TDB;

import parse.Parser;
import persistance.DataModelManager;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//Parser.parseMovie("http://www.imdb.com/title/tt1921064/");
			//Parser.parseIMDB("http://www.imdb.com/search/title?at=0&genres=drama&sort=user_rating,desc&start=1&title_type=feature");
			Parser.parseIMDB("http://www.imdb.com/search/title?genres=thriller&title_type=feature");

			//DataModelManager.getInstance().write("Movies.rdf", "TURTLE");
			TDB.sync(DataModelManager.getInstance().getDataset());
			DataModelManager.getInstance().closeDataModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
