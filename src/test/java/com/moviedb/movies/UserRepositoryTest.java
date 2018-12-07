package com.moviedb.movies;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.moviedb.movies.domain.User;
import com.moviedb.movies.domain.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	//Tests creating a new user
	@Test
	public void newUser() {

		User user = new User("moviewatcher", "$2a$10$37jGlxDwJK4mRpYqYvPmyu8mqQJfeQJVSdsyFY5UNAm9ckThf2Zqa", "USER");
		userRepository.save(user);

		assertThat(user).isNotNull();
		assertThat(user).hasFieldOrPropertyWithValue("username", "moviewatcher");
	}
	
	//Tests finding user by their username
	@Test
	public void FindUserByUsername() {

		User user = new User("moviewatcher", "$2a$10$37jGlxDwJK4mRpYqYvPmyu8mqQJfeQJVSdsyFY5UNAm9ckThf2Zqa", "USER");
		userRepository.save(user);
		
		String username = user.getUsername();
		User user4 = userRepository.findByUsername(username);
	
		assertThat(user4).isNotNull();
		assertThat(user4).hasFieldOrPropertyWithValue("username", "moviewatcher");
		
	}
	
}
