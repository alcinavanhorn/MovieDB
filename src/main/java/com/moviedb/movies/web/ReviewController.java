package com.moviedb.movies.web;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moviedb.movies.domain.Movie;
import com.moviedb.movies.domain.MovieRepository;
import com.moviedb.movies.domain.Review;
import com.moviedb.movies.domain.ReviewRepository;

@Controller
public class ReviewController {
	
	@Autowired
	private MovieRepository mrepository;

	@Autowired
	private ReviewRepository rrepository;

	//Saves a rating and adds them to details page
	@RequestMapping(value = "/savereview/{id}", method = RequestMethod.POST)
	public String saveReviews(@Valid Review review, @PathVariable("id") Long movieId) {
		Movie movie = mrepository.findById(movieId).get();
		review.setMovie(movie);
		rrepository.save(review);

		return "redirect:/showmovie/" + movieId;
	}

	//Deletes a review by ID
	@RequestMapping(value = "/deletereview/{reviewid}/{movieid}", method = RequestMethod.GET)
	public String deleteRating(@PathVariable("reviewid") Long reviewId, @PathVariable("movieid") Long movieId,
			Model model) {
		rrepository.deleteById(reviewId);
		
		return "redirect:/showmovie/" + movieId;
	}
	
	
	//ADMIN functions
	
	//RESTful service to get all ratings
	@RequestMapping(value = "/reviews", method = RequestMethod.GET)
	public @ResponseBody List<Review> ratingsListRest() {
		return (List<Review>) rrepository.findAll();
	}
	
	//RESTful service to get rating by id
    @RequestMapping(value="/review/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Review> findRatingRest(@PathVariable("id") Long reviewId) {	
    	return rrepository.findById(reviewId);
    }  
	
}
