package com.moviedb.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.moviedb.movies.domain.Genre;
import com.moviedb.movies.domain.GenreRepository;
import com.moviedb.movies.domain.Movie;
import com.moviedb.movies.domain.MovieRepository;
import com.moviedb.movies.domain.User;
import com.moviedb.movies.domain.UserRepository;

@SpringBootApplication
public class MoviesApplication {
	private static final Logger log = LoggerFactory.getLogger(MoviesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner movieDemo(MovieRepository mrepository, GenreRepository grepository, UserRepository urepository) {
		return (args) -> {
			//Adds all the genres used by the movies
			grepository.save(new Genre("Adventure")); 
			grepository.save(new Genre("Drama")); 
			grepository.save(new Genre("Romance"));
			grepository.save(new Genre("Action"));
			grepository.save(new Genre("Fantasy"));
			grepository.save(new Genre("Sci-Fi"));
			grepository.save(new Genre("Family")); 
			grepository.save(new Genre("Animated")); 
			grepository.save(new Genre("Thriller"));

			//Adds some movies and their information
			mrepository.save(new Movie(
					"http://www.gstatic.com/tv/thumb/v22vodart/31890/p31890_v_v8_ac.jpg", 
					"Finding Nemo", 
					"Andrew Stanton", 
					"Ellen DeGeneres, Albert Brooks, Alenxader Gould", 
					"A fish tries to find his lost son", 
					2003, 
					grepository.findByGenreName("Family").get(0))
					);
			
			mrepository.save(new Movie(
					"https://m.media-amazon.com/images/M/MV5BNTk4ODQ1MzgzNl5BMl5BanBnXkFtZTgwMTMyMzM4MTI@._V1_SY1000_CR0,0,658,1000_AL_.jpg",
					"Spider-Man: Homecoming", 
					"Jon Watts", 
					"Tom Holland, Michael Keaton, Robert Downey Jr.",
					"Teenager gets superpowers and becomes a hero", 
					2017, 
					grepository.findByGenreName("Adventure").get(0))
					);
			
			mrepository.save(new Movie(
					"https://m.media-amazon.com/images/M/MV5BY2IzZGY2YmEtYzljNS00NTM5LTgwMzUtMzM1NjQ4NGI0OTk0XkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_.jpg",
					"Casablanca",
					"Michael Curtiz",
					"Humphrey Bogart, Ingrid Bergman, Paul Henreid",
					"A cynical nightclub owner protects an old flame and her husband from Nazis in Morocco",
					1942, 
					grepository.findByGenreName("Drama").get(0))
					);
			
			mrepository.save(new Movie(
					"https://m.media-amazon.com/images/M/MV5BNWQ3NDhhZjAtNWJiMy00YjY3LThlZjgtN2U5NzM5ZDUzNWM1XkEyXkFqcGdeQXVyMzQwODY4MzQ@._V1_.jpg", 
					"Moomins on the Riviera",
					"Xavier Picard, Hanna Hemilä",
					"Maria Sid, Russell Tovey, Mats Långbacka", 
					"The Moomins travel to the Riviera", 
					2014,
					grepository.findByGenreName("Animated").get(0))
					);
			
			mrepository.save(new Movie(
					"https://m.media-amazon.com/images/M/MV5BMTY4NzcwODg3Nl5BMl5BanBnXkFtZTcwNTEwOTMyMw@@._V1_SY1000_CR0,0,674,1000_AL_.jpg",
					"Eternal Sunshine of the Spotless Mind", 
					"Michel Gondry",
					"Jim Carrey, Kate Winslet", 
					"A couple undergoes a medical procedure to have each other erased from their memories",
					2004,
					grepository.findByGenreName("Romance").get(0))
					);
			
			mrepository.save(new Movie(
					"https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SY1000_CR0,0,675,1000_AL_.jpg", 
					"The Dark Knight",
					"Christopher Nolan",
					"Christian Bale, Heath Ledger",
					"Batman tries to stop Joker from executing his evil plan", 
					2008, 
					grepository.findByGenreName("Action").get(0))
					);
			
			mrepository.save(new Movie(
					"https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,675,1000_AL_.jpg", 
					"The Lord of the Rings: The Return of the King",
					"Peter Jackson", "Elijah Wood, Viggo Mortensen, Ian McKellen",
					"A hobbit has to deliver a magical ring to a volcano ", 
					2003, 
					grepository.findByGenreName("Fantasy").get(0))
					);
			
			mrepository.save(new Movie(
					"https://m.media-amazon.com/images/M/MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,641,1000_AL_.jpg", 
					"Star Wars: Episode V - The Empire Strikes Back", 
					"Irvin Kershner", 
					"Mark Hamill, Harrison Ford, Carrie Fisher", 
					"Rebels fight a powerful Empire in space as Luke trains as a space wizard", 
					1980, grepository.findByGenreName("Sci-Fi").get(0))
					);
			
			mrepository.save(new Movie(
					"https://m.media-amazon.com/images/M/MV5BNTQwNDM1YzItNDAxZC00NWY2LTk0M2UtNDIwNWI5OGUyNWUxXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,672,1000_AL_.jpg",
					"Psycho",
					"Alfred Hitchcock",
					"Anthony Perkins, Janet Leigh, Vera Miles",
					"A guy kills people in a motel",
					1960, 
					grepository.findByGenreName("Thriller").get(0))
					);

			//Sets up user information	
			User user1 = new User("user", "$2a$10$xAncbXWAsuS5uSHucAMeSudtvyymtS7nxk9RIDDfhTFVdo2OkycxK", "USER"); //User / password
			User user2 = new User("admin", "$2a$10$mrvwOz6GYVXmKs1RNlWAxukKbXhVzw7OGKGmgb3XrlI0DXkusifOq", "ADMIN"); //Admin / password
			User user3 = new User("movielover", "$2a$10$37jGlxDwJK4mRpYqYvPmyu8mqQJfeQJVSdsyFY5UNAm9ckThf2Zqa", "USER"); //movielover / moviesaregood
			//Saves the users to the repository
			urepository.save(user1); 
			urepository.save(user2);
			urepository.save(user3);
			
			//Fetches all the movies from the repository
			for (Movie movie : mrepository.findAll()) {
				log.info(movie.toString());
			}
		};
	}
}
