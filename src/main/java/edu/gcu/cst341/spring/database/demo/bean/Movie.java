package edu.gcu.cst341.spring.database.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="favorite_movies")	// optional
@NamedQuery(name="find_all_movies", query="select m from Movie m")
public class Movie {

	@Id				// primary key
	@GeneratedValue				// generate unique id
	private int movieId;
	@Column(nullable = false)
	private String name;
	private double reviews;
	private String rating;
	private boolean isReleased = false;
	
	@OneToOne
	private Director director;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="movie")
	private List<Star> movies = new ArrayList<>();
	
	
	@Column(name = "created_date")
	@CreationTimestamp
	private Date createdDate;
	@Column(name = "last_updated_date")
	@UpdateTimestamp
	private Date lastUpdatedDate;

	public Movie() {}
	// Used for INSERT
	public Movie(String name, double reviews, String rating, boolean isReleased) {
		super();
		this.name = name;
		this.reviews = reviews;
		this.rating = rating;
		this.isReleased = isReleased;
	}
	// Used for UPDDATE
	public Movie(int id, String name, double reviews, String rating, boolean isReleased) {
		super();
		this.movieId = id;
		this.name = name;
		this.reviews = reviews;
		this.rating = rating;
		this.isReleased = isReleased;
	}
	// Used for INSERT
	public Movie(String name, double reviews, String rating, boolean isReleased, Director director) {
		super();
		this.name = name;
		this.reviews = reviews;
		this.rating = rating;
		this.isReleased = isReleased;
		this.director = director;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public List<Star> getMovies() {
		return movies;
	}
	public void setMovies(List<Star> movies) {
		this.movies = movies;
	}
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		this.director = director;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getReviews() {
		return reviews;
	}
	public void setReviews(double reviews) {
		this.reviews = reviews;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public boolean isReleased() {
		return isReleased;
	}
	public void setReleased(boolean isReleased) {
		this.isReleased = isReleased;
	}
	@Override
	public String toString() {
		String stars = "";
		if (!movies.isEmpty())
			for(Star s : movies)
				stars += s.toString();
		return "\n==> MOVIE ==> \n [id=" + movieId + ", name=" + name + ", reviews=" + reviews + ", rating=" + rating + ", isReleased="
				+ isReleased + ", director = " + director + "]";
	}
}
