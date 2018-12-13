package com.moviedb.movies.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //Creates a review entity
public class Review {
	@Id //Specifies a primary key for the entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Creates automatically generated values for the primary key
	private Long reviewId;
	 
	@Size(min=0, max=250) //Specifies a size for the review text
	private String reviewtext;
	
	@NotNull //Makes sure that the value is not null
	@Range(min = 0, max = 5) //Specifies the range for the rating
	private int rating;
	
	//Creates many-to-one relationship with movies, since one movie can have many reviews
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "movieId")
	private Movie movie;

	
	public Review() {} 
	
	//Parametric constructor for reviews
	public Review(int rating, String reviewtext, Movie movie) { 
		super();
		this.rating = rating;
		this.reviewtext = reviewtext;
		this.movie = movie;
		
	}

	//Basic review getters and setters
	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewtext() {
		return reviewtext;
	}

	public void setReviewtext(String reviewtext) {
		this.reviewtext = reviewtext;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	//Movie getter and setter
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
