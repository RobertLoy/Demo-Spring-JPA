package edu.gcu.cst341.spring.database.demo;
/* Built Spring Initialzr Application from Start.spring.io
 * DEPENDENCIES: Web, JDBC, JPA, H2
 * Maven, Java 8
 * H2 = In memory DB
 * http://localhost:8080/h2-console/
 * jdbc:h2:mem:testdb
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.gcu.cst341.spring.database.demo.bean.Director;
import edu.gcu.cst341.spring.database.demo.bean.Movie;
import edu.gcu.cst341.spring.database.demo.bean.Star;
import edu.gcu.cst341.spring.database.demo.jpa.DirectorJPARepo;
import edu.gcu.cst341.spring.database.demo.jpa.MovieJPARepo;

// CommandLineRunner allows us to run code like a main
@SpringBootApplication
public class JPADemoApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// Autowired to allow Spring to manage object creation
	@Autowired
	MovieJPARepo repo;
	@Autowired
	DirectorJPARepo directorrepo;

	// Does the component scan for all annotation
	public static void main(String[] args) {
		SpringApplication.run(JPADemoApplication.class, args);
	}

	// Part of the commandline runner class
	@Override
	public void run(String... args) throws Exception {
		/*
		 * CREATE SIMPLE
		 */	
		// Need to pass a Movie object WITH NO ID back to method for add
		Movie n;
		// Make a new Movie
		n = new Movie("Knights Tale", 4.22,"PG", true);
		// Do an update but no new ID
		logger.info("\nAdd Movie using Update -> {} added", repo.update(n));
		// Make a new Movie
		n = new Movie("Batman Returns", 3.89,"PG-13", true);
		// Do an insert but no new ID
		logger.info("\nAdd Movie using Update -> {} added", repo.insert(n));
		
		// Print out all the Movies	
		logger.info("\nAll Movies AFTER CREATE (SIMPLE) -> {}", repo.findAll());
		
		/*
		 * CREATE ADVANCE 
		 */	
		// Need to pass a Movie object WITH NO ID back to method for add
		// Make a new Director
		Director director = new Director("James Cameron");
		// Make a new Movie
		Movie movie = new Movie("Avatar", 4.79,"PG", true);
		// Do an insert but no new ID
		repo.insertWithDirector(movie,director);
		
		// Print out all the Movies	
		logger.info("\nAll Movies AFTER CREATE (ADVANCED) -> {}", repo.findAll());
		
		/*
		 * READ ALL
		 */
		logger.info("\nAll Movies -> {}", repo.findAll());
		
		/*
		 * READ SINGLE
		 */		
		logger.info("\nFind single Movie[201] -> {}", repo.findById(201));

		/*
		 * UPDATE
		 */
		Movie m;
		// Update Movie by getting original movie
		m = repo.findById(2);
		// Make changes to the movie
		m.setName(m.getName() + " - UPDATED");
		// Need to pass the Movie object WITH ID back to method for update
		logger.info("\nUpdate Single Movie [2] -> {} updated", repo.update(m));
		
		// Update Movie by getting original movie
		m = repo.findById(203);
		// Make changes to the movie
		m.setName(m.getName() + " - UPDATED");
		// Need to pass the Movie object WITH ID back to method for update
		logger.info("\nUpdate Single Movie [203] -> {} updated", repo.update(m));
		// Print out all the Movies	
		logger.info("\nAll Movies AFTER UPDATE -> {}", repo.findAll());
		
		/*
		 * DELETE
		 */	
		// Void method so cannot use Logger
		repo.deleteById(1);
		// Print out all the Movies	
		logger.info("\nAll Movies AFTER DELETE -> {}", repo.findAll());
		
		/*
		 *  MANY TO ONE EXAMPLE
		 */
	
		Star star1 = new Star("Karen Allen", false, 7500.01);
		Star star2 = new Star("John Rhys-Davies", false, 4500.99);
		repo.addStar(201, star1);
		repo.addStar(201, star2);
		//repo.getStars(201);
		logger.info("\nAll Movies AFTER STARS Added -> {}", repo.findAll());				
	}
}
