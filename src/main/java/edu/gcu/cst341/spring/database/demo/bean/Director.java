package edu.gcu.cst341.spring.database.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Director {

	@Id								// primary key
	@GeneratedValue					// generate unique id
	private int id;

	@Column(nullable = false)
	private String name;

	// This is child of movie (Movie will store the DIRECTOR_ID
	// Without this BOTH tables would have reference fields
	@OneToOne(mappedBy="director")
	Movie movie;

	public Director() {}

	// Used for INSERT
	public Director(String name) {
		super();
		this.name = name;
	}
	// Used for UPDDATE
	public Director(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "\n==> DIRECTOR ==> \n [id=" + id + ", name=" + name + "]\n";
	}
}

