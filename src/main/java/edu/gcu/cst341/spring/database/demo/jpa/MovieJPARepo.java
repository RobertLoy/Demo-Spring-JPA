package edu.gcu.cst341.spring.database.demo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.gcu.cst341.spring.database.demo.bean.Director;
import edu.gcu.cst341.spring.database.demo.bean.Movie;
import edu.gcu.cst341.spring.database.demo.bean.Star;

@Repository
@Transactional
public class MovieJPARepo {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DirectorJPARepo dRepo;
	
	@PersistenceContext
	EntityManager em;
	
	public List<Movie> findAll() {
		TypedQuery<Movie> namedQuery = em.createNamedQuery("find_all_movies", Movie.class);
		return namedQuery.getResultList();
	}
	
	public Movie findById(int id){
		return em.find(Movie.class, id);
	}
	
	// If ID does exist in Movie Object it does Update
	// If ID does NOT exists in Movie Object it does Insert
	public Movie update(Movie movie) {
		return em.merge(movie);
	}
	// If ID does NOT exists in Movie Object it does Insert
	public Movie insert(Movie movie) {
		return em.merge(movie);
	}
	
	// If ID does NOT exists in Movie Object it does Insert
	public void deleteById(int id) {
		Movie movie = findById(id);
		em.remove(movie);
	}
	
	public void insertWithDirector(Movie a, Director b) {
		em.persist(b);
		a.setDirector(b);
		em.persist(a);
	}
	
	public void getStars(int id) {
		Movie movie = findById(id);	
		logger.info("\nGet Stars => {}", movie.getMovies());
	}
	
	public void addStar(int id, Star s) {
		Movie m = findById(id);
		s.setMovie(m);
		em.persist(s);
	}
	
}
