package service;

import java.util.Collection;


import domain.Movie;
import domain.Review;

public interface MovieServiceInterface {
	public Collection<Movie> getAllMovies(String name, String genre,
			String director, String actor, String minAggregateRating,
			String minDuration, String maxDuration, String hasAward,
			String minReleaseYear, String maxReleaseYear, String limit);
	
	Review getReviewForMovie(String id, String name, String author);

}
