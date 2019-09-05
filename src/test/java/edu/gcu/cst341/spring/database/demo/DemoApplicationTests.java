package edu.gcu.cst341.spring.database.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import edu.gcu.cst341.spring.database.demo.bean.Movie;
import edu.gcu.cst341.spring.database.demo.jpa.DirectorJPARepo;
import edu.gcu.cst341.spring.database.demo.jpa.MovieJPARepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	
	@Autowired
	MovieJPARepo jrepository;

	@Autowired
	DirectorJPARepo drepository;

	@Autowired
	EntityManager em;
	
	@Before
	public void connectDB() {
		System.out.println("\n\nCONNECTING TO DB...\n\n");
	}
	
	@After
	public void disconnectDB() {
		System.out.println("\n\nDISCONNECTING TO DB...\n\n");
	}

	@Test
	public void contextLoads() {
	}
	
//	@Test
//	@Transactional  // if Update Fails, all changes rolled-back (Persistance Context)
//	public void someTest() {
//		
//		Movie m = em.find(Movie.class, 201);
//		Director d = m.getDirector();
//		
//		logger.info("Movie Details => {}", m);
//		logger.info("Director Details => {}", d);
//		
//		m.setName(m.getName() + " - UPDATED");
//		d.setName(d.getName() + " - UPDATED");
//		
//		
//		logger.info("Movie Details => {}", m);
//		logger.info("Director Details => {}", d);
//	}
	
//	@Test
//	//@Transactional  // if Update Fails, all changes rolled-back (Persistance Context)
//	public void getMovieWithDirector() {
//		
//		Movie m = em.find(Movie.class, 201);
//		Director d = m.getDirector();
//		
//		logger.info("Movie Details => {}", m);
//		logger.info("Director Details => {}", d);
//	}
	
//	@Test
//	@Transactional  // if Update Fails, all changes rolled-back (Persistance Context)
//	public void getDirectorWithMovie() {
//		
//		Director d = em.find(Director.class, 102);
//		Movie m = d.getMovie();
//		
//		logger.info("Movie Details => {}", m);
//		logger.info("Director Details => {}", d);
//	}
	
	@Test
	@DirtiesContext		// Prevents the DB from being changed
	public void movieInsertTest1() {
		System.out.println("TESTING ---- movieInsertTest1()");
		// Even though code 999, Generated Values updates
		Movie movie = new Movie(999, "Knights Tale", 4.22,"PG", true);
		Movie a = jrepository.insert(movie);
		Movie b = em.find(Movie.class, a.getMovieId());
		assertEquals(a.getName(), b.getName());
	}
	
	@Test
	@DirtiesContext		// Prevents the DB from being changed
	public void movieInsertTest2() {
		System.out.println("TESTING ---- movieInsertTest2()");
		// Even though code 999, Generated Values updates
		Movie movie = new Movie(999, "Knights Tale", 4.22,"PG", true);
		Movie c = em.find(Movie.class,999);
		// Can try notNull
		assertNotNull(c);
	}
	
	@Test
	@DirtiesContext		// Prevents the DB from being changed
	public void movieDeleteTest() {
		System.out.println("TESTING ---- movieDeleteTest()");
		jrepository.deleteById(201);
		assertNull(jrepository.findById(201));
	}

}
