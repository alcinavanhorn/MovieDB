package com.moviedb.movies;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.moviedb.movies.domain.Genre;
import com.moviedb.movies.domain.GenreRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GenreRepositoryTest {

	@Autowired
	private GenreRepository grepository;
	
	//Tests finding a genre by name and returning its ID
	@Test
	public void findByGenreNameReturnId() {
		List<Genre> genres = grepository.findByGenreName("Adventure");

		assertThat(genres).hasSize(1);
		assertThat(genres.get(0).getGenreId()).isEqualTo(1);
	}
	
	//Tests creating a new genre "comedy"
	@Test
	public void newGenre() {
		Genre genre = new Genre("Comedy");
		grepository.save(genre);
		assertThat(genre.getGenreId()).isNotNull();
	}

}