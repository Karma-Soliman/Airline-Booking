package com.timeout.airline.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "planes")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlane;
    
    
    @Column(nullable = false)
    private int capacity;
    

	@Column(nullable = false)
    private String type;
	
	@Column(nullable = false)
    private String manYear;

	@OneToMany(mappedBy = "plane")
    @JsonIgnore
    private List<Flight> flights;
	
	
	public String getManYear() {
		return manYear;
	}

	public void setManYear(String manYear) {
		this.manYear = manYear;
	}
	
    public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}	
    public Long getIdPlane() {
		return idPlane;
	}


	public void setIdPlane(Long idPlane) {
		this.idPlane = idPlane;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public List<Flight> getFlights() {
		return flights;
	}


	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

}
