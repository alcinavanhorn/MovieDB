package com.moviedb.movies.domain;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends CrudRepository <Review, Long> {

	List<Review> findByMovie(Movie movie); //Finds reviews by movie
	
	@Query("SELECT rating FROM Review WHERE movie =:movie") //Query used to find ratings for the chosen movie
	double[] findRatingsByMovie(@Param(value="movie")Movie movie); //Handles the array list of ratings to calculate average

}
