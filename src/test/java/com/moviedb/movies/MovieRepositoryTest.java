package com.moviedb.movies;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.moviedb.movies.domain.GenreRepository;
import com.moviedb.movies.domain.Movie;
import com.moviedb.movies.domain.MovieRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

	
	@Autowired
	private MovieRepository mrepository;
	
	@Autowired
	private GenreRepository grepository;
	
	//Tests creating a new movie
	@Test
	public void newMovie() {

		Movie movie = new Movie(
				"https://m.media-amazon.com/images/M/MV5BMzc0MWMzZWYtMmYzZS00YTZlLTgyMjAtYjk3YzVjNjdlMzEzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SY1000_CR0,0,700,1000_AL_.jpg", 
				"An Extremely Goofy Movie", 
				"Douglas McCarthy", 
				"Bill Farmer, Jason Marsden, Jeff Bennett",
				"Goofy's son goes to college, but Goofy causes trouble.",
				2000,
				grepository.findByGenreName("Animated").get(0)
				);
		mrepository.save(movie);

		assertThat(movie.getId()).isNotNull();
		assertThat(movie).hasFieldOrPropertyWithValue("title", "An Extremely Goofy Movie");
	}
	
	//Tests deleting a movie
	@Test
	public void movieDelete() {
		
		Movie movie = new Movie(
				"https://m.media-amazon.com/images/M/MV5BMzc0MWMzZWYtMmYzZS00YTZlLTgyMjAtYjk3YzVjNjdlMzEzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SY1000_CR0,0,700,1000_AL_.jpg", 
				"An Extremely Goofy Movie", 
				"Douglas McCarthy", 
				"Bill Farmer, Jason Marsden, Jeff Bennett",
				"Goofy's son goes to college, but Goofy causes trouble.",
				2000,
				grepository.findByGenreName("Animated").get(0)
				);
		mrepository.save(movie);

		Long id = movie.getId();

		mrepository.deleteById(id);
		Optional<Movie> deletedMovie = mrepository.findById(id);

		assertThat(deletedMovie).isEmpty();
	}
	
	//Tests finding a movie by ID
	@Test
	public void findMovieById() {
		
		Movie movie = new Movie(
				"https://m.media-amazon.com/images/M/MV5BMzc0MWMzZWYtMmYzZS00YTZlLTgyMjAtYjk3YzVjNjdlMzEzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SY1000_CR0,0,700,1000_AL_.jpg", 
				"An Extremely Goofy Movie", 
				"Douglas McCarthy", 
				"Bill Farmer, Jason Marsden, Jeff Bennett",
				"Goofy's son goes to college, but Goofy causes trouble.",
				2000,
				grepository.findByGenreName("Animated").get(0)
				);
		mrepository.save(movie);

		Long id = movie.getId();
		
		Movie movie2 = mrepository.findById(id).get();

		assertThat(movie2).isNotNull();
		assertThat(movie2).hasFieldOrPropertyWithValue("title", "An Extremely Goofy Movie");
		
	}
	
	//Tests finding a movie by title
	@Test
	public void findByMovieTitle() {
		
		List<Movie> movie = mrepository.findByTitle("Finding Nemo");

		assertThat(movie).isNotEmpty();
		assertThat(movie).hasSize(1);
		assertThat(movie.get(0)).hasFieldOrPropertyWithValue("title", "Finding Nemo");
		
	}
}