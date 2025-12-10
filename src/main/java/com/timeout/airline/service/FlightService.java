package com.timeout.airline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeout.airline.entity.Flight;
import com.timeout.airline.exception.ResourceNotFoundException;
import com.timeout.airline.repository.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	private FlightRepository flightRepo;
	
	//CREATE
	public Flight createFlight(Flight flight) {

	// 1. check flight number is unique
		if (flightRepo.findByFlightNumber(flight.getFlightNumber()).isPresent()) {
	        throw new IllegalArgumentException("Flight number already exists: " + flight.getFlightNumber());
	    }
	    
	// 2. validate times
	    if (flight.getDepartureTime().isAfter(flight.getArrivalTime())) {
	        throw new IllegalArgumentException("Departure time must be before arrival time");
	    }
	    
	// 3. validate airports are different
	    if (flight.getDepartureAirport().getIdAirport().equals(flight.getArrivalAirport().getIdAirport())) {
	        throw new IllegalArgumentException("Departure and arrival airports must be different");
	    }
	    
	// 4. calculate duration
	    long minutes = java.time.Duration.between(flight.getDepartureTime(), flight.getArrivalTime()).toMinutes();
	    flight.setDuration(minutes / 60.0); // Convert to hours
	    
		return flightRepo.save(flight);
	}
	
	//READ
	public List<Flight> getAllFlights(){
		return flightRepo.findAll();
	}
	
	public Flight getFlightById(Long id){
		return flightRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));
	}
	
	public Flight getFlightByNumber(String code){
		return flightRepo.findByFlightNumber(code)
				.orElseThrow(() -> new ResourceNotFoundException("Flight not found with flight number: " + code));
	}
	
	public List<Flight> getByDepartureAirport(String code){
		return flightRepo.findByDepartureAirportCode(code);
	}
	
	public List<Flight> getByArrivalAirport(String code){
	    return flightRepo.findByArrivalAirportCode(code);
	}
	
	//for booking
	public List<Flight> searchFlights(String departureCode, String arrivalCode) {
	    return flightRepo.findByDepartureAirportCodeAndArrivalAirportCode(departureCode, arrivalCode);
	}
	
	//UPDATE
	public Flight updateFlight(Long id, Flight flightInfo) {
	    Flight flight = getFlightById(id);
	    
	    flight.setFlightNumber(flightInfo.getFlightNumber());
	    //mapped from airport
	    flight.setDepartureAirport(flightInfo.getDepartureAirport());
	    flight.setArrivalAirport(flightInfo.getArrivalAirport());
	    //mapped from plane
	    flight.setPlane(flightInfo.getPlane());
	    
	    flight.setDepartureTime(flightInfo.getDepartureTime());
	    flight.setArrivalTime(flightInfo.getArrivalTime());
	    
	    // recalculate duration
	    long minutes = java.time.Duration.between(flight.getDepartureTime(), flight.getArrivalTime()).toMinutes();
	    flight.setDuration(minutes / 60.0);
	    
	    return flightRepo.save(flight);
	}
	
	//DELETE
	public void deleteFlight(Long id) {
	    Flight flight = getFlightById(id);
	    
	    // check if flight has bookings booking entity not created yet.
	    // if (!flight.getBookings().isEmpty()) {
	    //     throw new IllegalStateException("Cannot delete flight with existing bookings");
	    // }
	    
	    flightRepo.delete(flight);
	}
	
}
