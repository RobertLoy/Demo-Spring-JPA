package edu.gcu.cst341.spring.database.demo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import edu.gcu.cst341.spring.database.demo.bean.Director;
import edu.gcu.cst341.spring.database.demo.bean.Movie;

@Repository
@Transactional
public class DirectorJPARepo {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Movie> findAll() {
		TypedQuery<Movie> namedQuery = em.createNamedQuery("find_all_movies", Movie.class);
		return namedQuery.getResultList();
	}
	
	public Director findById(Director director){
		return em.find(Director.class, director.getId());
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
	
	public boolean insertWithDirector(){
	
		Director d = new Director("George Lucas");
		em.persist(d);
		
		Movie n = new Movie("Empire Strikes Back", 4.11, "PG-13", true);
		n.setDirector(d);
		em.persist(n);
		return true;
		
	}
}
