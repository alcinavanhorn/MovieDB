package com.moviedb.movies.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false) //Sets the specification for user SQL columns
    private Long id;

    @Column(name = "username", nullable = false, unique = true) //Sets the specification for username SQL columns
    private String username;

    @Column(name = "password", nullable = false) //Sets the specification for password SQL columns
    private String passwordHash;

    @Column(name = "role", nullable = false) //Sets the specification for role SQL columns
    private String role;
	
    public User() {
    }

    //Parametric constructor for User
	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	//Basic user getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
