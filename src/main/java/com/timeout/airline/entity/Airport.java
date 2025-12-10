package com.timeout.airline.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "airports")
public class Airport {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAirport;
    
    
    @Column(unique = true, nullable = false)
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String city;
    
   
	@Column(nullable = false)
    private String country;   
    
    //flights departing from this airport
    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departingFlights;
   
    //flights arriving to this airport
    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivalFlights;
    
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getIdAirport() {
		return idAirport;
	}

	public void setIdAirport(Long idAirport) {
		this.idAirport = idAirport;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Flight> getDepartingFlights() {
		return departingFlights;
	}

	public void setDepartingFlights(List<Flight> departingFlights) {
		this.departingFlights = departingFlights;
	}

	public List<Flight> getArrivalFlights() {
		return arrivalFlights;
	}

	public void setArrivalFlights(List<Flight> arrivalFlights) {
		this.arrivalFlights = arrivalFlights;
	}
    
    
}
