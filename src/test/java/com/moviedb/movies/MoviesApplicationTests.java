package com.moviedb.movies;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.moviedb.movies.web.MovieController;
import com.moviedb.movies.web.ReviewController;
import com.moviedb.movies.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesApplicationTests {

	@Autowired 
	private MovieController mcontroller;
	
	@Autowired
	private UserController ucontroller;
	
	@Autowired
	private ReviewController rcontroller;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(mcontroller).isNotNull();
		assertThat(ucontroller).isNotNull();
		assertThat(rcontroller).isNotNull();
}

}
