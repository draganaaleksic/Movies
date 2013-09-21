package service;

import java.util.Collection;

import domain.Movie;
import domain.Review;

public class MovieService implements MovieServiceInterface {

	@Override
	public Collection<Movie> getAllMovies(String name, String genre,
			String director, String actor, String minAggregateRating,
			String minDuration, String maxDuration, String hasAward,
			String minReleaseYear, String maxReleaseYear, String limit) {

		QueryService qs = new QueryService();
		Collection<Movie> movies = qs.getAllMovies(name, genre, director,
				actor, minAggregateRating, minDuration, maxDuration, hasAward,
				minReleaseYear, maxReleaseYear, limit);
		return movies;
	}

	@Override
	public Review getReviewForMovie(String id, String name, String author) {
		QueryService qs = new QueryService();
		Movie m = qs.getMovie(id);
		Review r = m.getReview();
		Review rr = new Review();

		if (r != null) {
			if (!name.isEmpty() && r.getName().contains(name)) {
				rr.setName(r.getName());
				rr.setAuthor(r.getAuthor());
			}
			if (!author.isEmpty() && r.getAuthor().contains(author)) {
				rr.setName(r.getName());
				rr.setAuthor(r.getAuthor());
			}
		}
		return rr;
	}

}
