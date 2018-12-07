package com.moviedb.movies.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity //Creates a genre entity
public class Genre {
	@Id //Specifies a primary key for the entity
	@GeneratedValue(strategy=GenerationType.AUTO) //Creates automatically generated values for the primary key
	private Long genreId;
	
	public String genreName;
	
	//Creates one-to-many relationship with movies, since one genre can have many movies
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
	private List<Movie> movies;
	
	public Genre() {}
		
	//Parametric constructor for genre
	public Genre(String genreName) {
		super();
		this.genreName = genreName;
	}
	
	//Genre ID getter and setter
	public Long getGenreId() {
		return genreId;
	}
	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}
	
	//Genre name getter and setter
	public String getGenreName() {
		return genreName;
	}
	
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	//Movie list getter and setter
	public List<Movie> getMovies() {
		return movies;
	}
	
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	@Override
	public String toString() {
		return "Genre [genreId=" + genreId + ", genreName=" + genreName + "]";
	}
} 
