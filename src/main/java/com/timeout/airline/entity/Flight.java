package com.timeout.airline.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFlight;
    
    @ManyToOne
    @JoinColumn(name = "departure_airport_code", nullable = false)
    private Airport departureAirport;
    
    @ManyToOne
    @JoinColumn(name = "arrival_airport_code", nullable = false)
    private Airport arrivalAirport;
    
    @ManyToOne
    @JoinColumn(name = "plane_type", nullable = false)
    private Plane plane;
    
    @OneToMany(mappedBy = "flight")
    @JsonIgnore
    private List<Book> bookings;
    
    @OneToMany(mappedBy = "flight")
    @JsonIgnore
    private List<MilesReward> milesRewards;
    


	@Column(nullable = false, unique = true)
    private String flightNumber;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;
    
    @Column(nullable = true)
    private Double duration;
    
    @Column(nullable = false)
    private Integer seats;
    
    @Column(nullable = true)
    private Integer firstClassSeats;
    
    @Column(nullable = true)
    private Integer premiumClassSeats;
    
    @Column(nullable = true)
    private Integer businessClassSeats;
    
    @Column(nullable = true)
    private Integer economyClassSeats;
    
    @Column(nullable = true)
    private Double firstClassPrice;
    
    @Column(nullable = true)
    private Double premiumClassPrice;
    
    @Column(nullable = true)
    private Double businessClassPrice;
    
    @Column(nullable = true)
    private Double economyClassPrice;
 
//    @Column(nullable = true)
//    private String status;
    
    public List<Book> getBookings() {
		return bookings;
	}

	public void setBookings(List<Book> bookings) {
		this.bookings = bookings;
	}

	public List<MilesReward> getMilesRewards() {
		return milesRewards;
	}

	public void setMilesRewards(List<MilesReward> milesRewards) {
		this.milesRewards = milesRewards;
	}
    public Long getIdFlight() {
		return idFlight;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getFirstClassSeats() {
		return firstClassSeats;
	}

	public void setFirstClassSeats(Integer firstClassSeats) {
		this.firstClassSeats = firstClassSeats;
	}

	public Integer getPremiumClassSeats() {
		return premiumClassSeats;
	}

	public void setPremiumClassSeats(Integer premiumClassSeats) {
		this.premiumClassSeats = premiumClassSeats;
	}

	public Integer getBusinessClassSeats() {
		return businessClassSeats;
	}

	public void setBusinessClassSeats(Integer businessClassSeats) {
		this.businessClassSeats = businessClassSeats;
	}

	public Integer getEconomyClassSeats() {
		return economyClassSeats;
	}

	public void setEconomyClassSeats(Integer economyClassSeats) {
		this.economyClassSeats = economyClassSeats;
	}

	public Double getFirstClassPrice() {
		return firstClassPrice;
	}

	public void setFirstClassPrice(Double firstClassPrice2) {
		this.firstClassPrice = firstClassPrice2;
	}

	public Double getPremiumClassPrice() {
		return premiumClassPrice;
	}

	public void setPremiumClassPrice(Double premiumClassPrice) {
		this.premiumClassPrice = premiumClassPrice;
	}

	public Double getBusinessClassPrice() {
		return businessClassPrice;
	}

	public void setBusinessClassPrice(Double businessClassPrice) {
		this.businessClassPrice = businessClassPrice;
	}

	public Double getEconomyClassPrice() {
		return economyClassPrice;
	}

	public void setEconomyClassPrice(Double economyClassPrice) {
		this.economyClassPrice = economyClassPrice;
	}

	public void setIdFlight(Long idFlight) {
		this.idFlight = idFlight;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

}
