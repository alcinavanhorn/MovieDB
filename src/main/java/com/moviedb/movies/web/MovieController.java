package com.moviedb.movies.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moviedb.movies.domain.Genre;
import com.moviedb.movies.domain.GenreRepository;
import com.moviedb.movies.domain.Movie;
import com.moviedb.movies.domain.MovieRepository;
import com.moviedb.movies.domain.Review;
import com.moviedb.movies.domain.ReviewRepository;

@Controller
public class MovieController {
	@Autowired
	private MovieRepository mrepository; 
	
	@Autowired
	private GenreRepository grepository;
	
	@Autowired
	private ReviewRepository rrepository;

	
	//Login
	@RequestMapping(value="/login")
		public String login() {
			return "login";
	}
	
	//Shows all movies
	@RequestMapping(value="/homepage")
	public String movieList(Model model) {
		model.addAttribute("movies", mrepository.findAll());
		model.addAttribute("genres", grepository.findAll()); //For adding genre to the dropdown
		
		return "homepage";
	}
	
	//Add a movie
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addMovie(Model model) {
		model.addAttribute("movie", new Movie());
		model.addAttribute("genres", grepository.findAll());
		return "addmovie";
	}
		
	//Sets up dropdown for genres
	@RequestMapping(value = "/filter")
	public String dropdown(Model model) {
		model.addAttribute("genres", grepository.findAll());
		return "filter";
	}
	
	//Filter movies by genre
	@RequestMapping(value = "/findmovie", method = RequestMethod.GET)
	public String filterMovies(@RequestParam("genre") Long genreId,	Model model) {

		Genre genre = grepository.findByGenreId(genreId);

		// Get movies based on the genre in the list
		List<Movie> moviefilter = new ArrayList<Movie>(genre.getMovies());

		model.addAttribute("movies", moviefilter);
		model.addAttribute("genres", grepository.findAll());
		
		return "filter";
	}
	
	//
	@RequestMapping(value = "/showmovie/{id}", method = RequestMethod.GET)
	public String showDetails(@PathVariable("id") Long movieId, Model model) {

		Movie movie = mrepository.findById(movieId).get();
		model.addAttribute("movie", movie);

		List<Review> reviews = rrepository.findByMovie(movie);
		Collections.reverse(reviews); //Reverses list to have newest reviews at the top
		model.addAttribute("reviews", reviews);

		model.addAttribute("review", new Review()); //Adds new rating to JQueryForm

		double[] allRatings = rrepository.findRatingsByMovie(movie);
		double sum = 0;

		//A loop to calculate the average
		for (int i = 0; i < allRatings.length; i++) {
			sum = sum + allRatings[i];

			double average = (sum / allRatings.length);
			DecimalFormat decimal = new DecimalFormat("0.0");
			
			model.addAttribute("average", decimal.format(average));
		}

		return "details";
	}
	
	
   //ADMIN functions
       
  //Edit a movie
  	@RequestMapping(value="/edit/{id}")
  	public String editMovie(@PathVariable("id") Long movieId, Model model) {
  		model.addAttribute("movie", mrepository.findById(movieId));
  		model.addAttribute("genres", grepository.findAll());
  		return "editmovie";
  	}
  	
  	//Save movies form
  	@RequestMapping(value="/save", method = RequestMethod.POST)
  	public String saveMovie(Movie movie) {
  		mrepository.save(movie);
  		return "redirect:/homepage";
  	}
  	
  	//Remove a movie
  	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
  	public String removeMovie(@PathVariable("id") Long movieId, Model model) {
  		mrepository.deleteById(movieId);
  		return "redirect:/homepage";
  	}
  	
	
	//RESTFUL SERVICES//
  	
	//RESTful services to get all movies
    @RequestMapping(value="/movies", method = RequestMethod.GET)
    public @ResponseBody List<Movie> movieListRest() {
    	return (List<Movie>) mrepository.findAll();
    }
    
	//RESTful services to get movies by id
    @RequestMapping(value="/movie/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Movie> findMovieRest(@PathVariable("id") Long movieId) {
    	return mrepository.findById(movieId);
    }
    
	//RESTful services to get all genres
    @RequestMapping(value="/genres", method = RequestMethod.GET)
    public @ResponseBody List<Genre> genreListRest() {
    	return (List<Genre>) grepository.findAll();
    }
    
	//RESTful services to get genres by id
    @RequestMapping(value="/genre/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Genre> findGenreRest(@PathVariable("id") Long genreId) {
    	return grepository.findById(genreId);
    }
    
}
