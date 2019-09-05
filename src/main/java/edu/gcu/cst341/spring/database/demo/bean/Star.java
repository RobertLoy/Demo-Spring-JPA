package edu.gcu.cst341.spring.database.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Star {

	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=true)
	private boolean isAList = false;
	private double salary;

	@ManyToOne
	private Movie movie;

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

	public boolean isAList() {
		return isAList;
	}

	public void setAList(boolean isAList) {
		this.isAList = isAList;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Star(String name, boolean isAList, double salary) {
		this.name = name;
		this.isAList = isAList;
		this.salary = salary;
	}
	public Star(int id, String name, boolean isAList, double salary, Movie movie) {
		super();
		this.id = id;
		this.name = name;
		this.isAList = isAList;
		this.salary = salary;
		this.movie = movie;
	}

	public Star() {}

	@Override
	public String toString() {
		return "\n==> STAR ==> \n[id=" + id + ", name=" + name + ", isAList=" + isAList + ", salary=" + salary + ", movie=" + movie
				+ "]";
	}
}
