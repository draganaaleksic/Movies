package service;

import java.util.ArrayList;
import java.util.Collection;

import persistance.DataModelManager;
import service.QueryExecutor;
import util.Constants;
import domain.Movie;
import domain.Review;

public class QueryService {

	private QueryExecutor queryExecutor = new QueryExecutor();

	public Collection<Movie> getAllMovies(String name, String genre,
			String director, String actor, String minAggregateRating,
			String minDuration, String maxDuration, String hasAward,
			String minReleaseYear, String maxReleaseYear, String limit) {

		Collection<Movie> movies = new ArrayList<Movie>();

		String where = " ?movie a schema:Movie. ";
		String filter = "";

		if (!name.isEmpty()) {
			where += "?movie schema:name ?name. ";
			filter += "FILTER regex( ?name, \"" + name + "\", \"i\" ) ";
		}
		if (!director.isEmpty()) {
			where += "?movie schema:director ?director. "
					+ "?director schema:name ?name. ";
			filter += "FILTER regex( ?name, \"" + director + "\", \"i\" ) ";
		}
		if (!actor.isEmpty()) {
			where += "?movie schema:actor ?actor. "
					+ "?actor schema:name ?name. ";
			filter += "FILTER regex( ?name, \"" + actor + "\", \"i\" ) ";
		}

		if (!genre.isEmpty()) {
			String[] categories = genre.split(",");
			for (String string : categories) {
				string = string.trim();
				where += "?movie schema:genre ?" + string + ". ";
				filter += "FILTER regex( ?" + string + ", \"" + string
						+ "\", \"i\" ) ";
			}
		}

		if (!minAggregateRating.isEmpty()) {
			where += "?movie schema:aggregateRating ?aggregateRating. "
					+ "?aggregateRating schema:ratingValue ?ratingValue. ";
			filter += "FILTER ( ?ratingValue >=" + minAggregateRating + " ) ";
		}

		if (!minDuration.isEmpty() || !maxDuration.isEmpty()) {
			where += "?movie schema:duration ?duration. ";

			if (!minDuration.isEmpty())
				filter += "FILTER ( ?duration >=" + minDuration + " ) ";
			if (!maxDuration.isEmpty())
				filter += "FILTER ( ?duration <=" + maxDuration + " ) ";
		}

		if (!hasAward.isEmpty()) {
			where += "?movie schema:awards ?awards. ";
			// filter += "FILTER (bound(?awards) )";
		}

		
		if (!minReleaseYear.isEmpty() || !maxReleaseYear.isEmpty()) {
			where += "?movie schema:datePublished ?datePublished. ";
					

			if (!minDuration.isEmpty())
				// (bif:year( bif:stringdate(?sdate)) AS ?syear)
				filter += "FILTER ( YEAR(?datePublished) >=" + minReleaseYear + " ) ";

			if (!maxDuration.isEmpty())
				filter += "FILTER (  YEAR(?datePublished) <=" + maxReleaseYear + " ) ";
		}

		String query = "PREFIX schema: <" + Constants.SCHEMA + "> "
				+ "SELECT ?movie " + "WHERE { " + where + filter + " } LIMIT "
				+ limit;

		Collection<String> movieUris = queryExecutor
				.executeOneVariableSelectSparqlQuery(query, "movie",
						DataModelManager.getInstance().getModel());

		for (String string : movieUris) {
			Movie movie = getMovie(string);
			movies.add(movie);
		}
		return movies;
	}

	public Movie getMovie(String uri) {
		Movie movie = queryExecutor.getMovie(uri);
		return movie;
	}
	
	public Review getAllReviews(String uriMovie, String name, String author){
		
		String where = "?review a schema:Review.";
		String filter = "";
		if (!name.isEmpty()) {
			where += "?review schema:name ?name. ";
			filter += "FILTER regex( ?name, \"" + name + "\", \"i\" ) ";
		}
		if (!author.isEmpty()) {
			where += "?review schema:author ?author. "
					+ "?director schema:name ?name. ";
			filter += "FILTER regex( ?name, \"" + author + "\", \"i\" ) ";
		}
		String query = "PREFIX schema: <" + Constants.SCHEMA + "> "
				+ "SELECT ?review " 
				+ "FROM <"+ uriMovie+">"
				+ "WHERE { " + where + filter + " } ";
				

		Collection<String> reviewUris = queryExecutor
				.executeOneVariableSelectSparqlQuery(query, "review",
						DataModelManager.getInstance().getModel());
		
		for (String string : reviewUris) {
			Review review = getReview(string);
			return review;
		}
		return null;
		
		
	}

	public Review getReview(String uri) {
		Review review = queryExecutor.getReview(uri);
		return review;
	}

}
