
package com.moviedb.movies;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.moviedb.movies.domain.GenreRepository;
import com.moviedb.movies.domain.Movie;
import com.moviedb.movies.domain.MovieRepository;
import com.moviedb.movies.domain.Review;
import com.moviedb.movies.domain.ReviewRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {
	
	
	@Autowired
	private MovieRepository mrepository;
	

	@Autowired
	private ReviewRepository rrepository;
	
	@Autowired
	private GenreRepository grepository;

	//Tests creating a new review
	@Test
	public void newReview() {

		Review review = new Review(4, "A great movie 10/10", new Movie(
				"https://m.media-amazon.com/images/M/MV5BMzc0MWMzZWYtMmYzZS00YTZlLTgyMjAtYjk3YzVjNjdlMzEzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SY1000_CR0,0,700,1000_AL_.jpg", 
				"An Extremely Goofy Movie", 
				"Douglas McCarthy", 
				"Bill Farmer, Jason Marsden, Jeff Bennett",
				"Goofy's son goes to college, but Goofy causes trouble.",
				2000,
				grepository.findByGenreName("Animated").get(0)
				));
		rrepository.save(review);

		assertThat(review.getReviewId()).isNotNull();
		assertThat(review).hasFieldOrPropertyWithValue("rating", 4);
	}
	
	//Tests deleting a review
	@Test
	public void deleteReview() {

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
	
		Review review = new Review(3, "A very goofy movie", mrepository.findByTitle("An Extremely Goofy Movie").get(0));
		rrepository.save(review);

		Long id = review.getReviewId();

		rrepository.deleteById(id);
		Optional<Review> deletedReview = rrepository.findById(id);

		assertThat(deletedReview).isEmpty();
	}
	
	//Tests finding a review by its ID
	@Test
	public void FindRatingById() {
		
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
	
		Review review = new Review(4, "I liked this movie", mrepository.findByTitle("An Extremely Goofy Movie").get(0));
		rrepository.save(review);

		Long id = review.getReviewId();
		
		Review review2 = rrepository.findById(id).get();

		assertThat(review2).isNotNull();
		assertThat(review2).hasFieldOrPropertyWithValue("rating", 4);
	}
	
	//Tests finding a review by the movie it is associated with
	@Test
	public void FindReviewByMovie() {
			
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
		
			Review review = new Review(4, "I liked this movie", mrepository.findByTitle("An Extremely Goofy Movie").get(0));
			rrepository.save(review);

			Review review2 = new Review(3, "It was okay", mrepository.findByTitle("An Extremely Goofy Movie").get(0));
			rrepository.save(review2);
			
			double[] ratings = rrepository.findRatingByMovie(movie);
			assertThat(ratings).isNotEmpty();
			assertThat(ratings).hasSize(2);
		}

}