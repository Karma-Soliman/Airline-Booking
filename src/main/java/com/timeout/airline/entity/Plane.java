package com.timeout.airline.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "planes")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlane;
    
    
    @Column(nullable = false)
    private int capacity;
    
    @OneToMany(mappedBy = "plane")
    private List<Flight> flights;
    	
    	
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
