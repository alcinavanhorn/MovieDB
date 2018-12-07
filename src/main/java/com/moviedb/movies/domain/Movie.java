package com.moviedb.movies.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity//Creates a movie entity
public class Movie {
	@Id //Specifies a primary key for the entity
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Creates automatically generated values for the primary key
	private Long id;
	
	private String poster;
	private String title;
	private String director;
	private String actor;
	private String synopsis;
	private int year;
	
	//Creates many-to-one relationship with genres, since many movies are in one genre
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "genreId")
	private Genre genre;
	
	//Creates one-to-many relationship with reviews, since a movie can have many reviews
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
	private List<Review> ratings;
	
	public Movie() {}
	
	//Parametric constructor for movie
	public Movie(String poster, String title, String director, String actor, String synopsis, int year, Genre genre) {
		super();
		this.poster = poster;
		this.title = title;
		this.director = director;
		this.actor = actor;
		this.synopsis = synopsis;
		this.year = year;
		this.genre = genre;
	}
	
	//Basic movie getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}
	
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	//Review list getters and setters
	public List<Review> getRatings() {
		return getRatings();
	}

	public void setRatings(List<Review> ratings) {
		this.ratings = ratings;
	}
	
	//Genre getter and setter
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
}
