package com.moviedb.movies.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findByGenreName(String genreName); //Finds genres by name
	Genre findByGenreId(Long genreId); //Finds genres by id

}