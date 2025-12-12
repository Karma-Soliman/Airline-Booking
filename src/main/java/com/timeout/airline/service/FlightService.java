package com.timeout.airline.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeout.airline.dto.FlightDto;
import com.timeout.airline.entity.Airport;
import com.timeout.airline.entity.Flight;
import com.timeout.airline.entity.Plane;
import com.timeout.airline.exception.ResourceNotFoundException;
import com.timeout.airline.repository.AirportRepository;
import com.timeout.airline.repository.FlightRepository;
import com.timeout.airline.repository.PlaneRepository;

@Service
public class FlightService {
	
	@Autowired
	private AirportRepository airportRepo;
	
	@Autowired
	private PlaneRepository planeRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	//CREATE
	public Flight createFlight(FlightDto dto) {
		
	    Airport departure = airportRepo.findByCode(dto.departureAirportCode)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid departure airport code: " + dto.departureAirportCode));

	        Airport arrival = airportRepo.findByCode(dto.arrivalAirportCode)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid arrival airport code: " + dto.arrivalAirportCode));

	        Plane plane = planeRepo.findByType(dto.planeType)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid plane type: " + dto.planeType));
	        
	        Integer totalSeats = (dto.firstClassSeats != null ? dto.firstClassSeats : 0) +
                    (dto.businessClassSeats != null ? dto.businessClassSeats : 0) +
                    (dto.premiumClassSeats != null ? dto.premiumClassSeats : 0) +
                    (dto.economyClassSeats != null ? dto.economyClassSeats : 0);
	        
	        //makes sure number of seats available is equal to total number of each class.
	        if (dto.seats != null && !dto.seats.equals(totalSeats)) {
	        	throw new IllegalArgumentException("Number of seats available does not match total number of seats");
	        }
	        // makes sure total number of seats is not more than the capacity of the plane.
	        if (totalSeats > plane.getCapacity()) {
	        	 throw new IllegalArgumentException("Total number of seats exceeds plane capacity");
	        }

	        Flight flight = new Flight();
	        //flight scheduling
	        flight.setFlightNumber(dto.flightNumber);
	        flight.setDepartureAirport(departure);
	        flight.setArrivalAirport(arrival);
	        flight.setPlane(plane);
	        flight.setDepartureTime(dto.departureTime);
	        flight.setArrivalTime(dto.arrivalTime);
	        
	        //seats & prices
	        flight.setSeats(dto.seats);
	        flight.setFirstClassSeats(dto.firstClassSeats);
	        flight.setPremiumClassSeats(dto.premiumClassSeats);
	        flight.setBusinessClassSeats(dto.businessClassSeats);
	        flight.setEconomyClassSeats(dto.economyClassSeats);
	        flight.setFirstClassPrice(dto.firstClassPrice);
	        flight.setBusinessClassPrice(dto.businessClassPrice);
	        flight.setPremiumClassPrice(dto.premiumClassPrice);
	        flight.setEconomyClassPrice(dto.economyClassPrice);
	        
	      

	// 1. check flight number is unique
		if (flightRepo.findByFlightNumber(dto.flightNumber).isPresent()) {
	        throw new IllegalArgumentException("Flight number already exists: " + dto.flightNumber);
	    }
	    
	// 2. validate times
	    if (dto.departureTime.isAfter(dto.arrivalTime)) {
	        throw new IllegalArgumentException("Departure time must be before arrival time");
	    }
	    
	// 3. validate airports are different
	    if (departure.getIdAirport().equals(arrival.getIdAirport())) {
	        throw new IllegalArgumentException("Departure and arrival airports must be different");
	    }
	    
	// 4. calculate duration
	    long minutes = java.time.Duration.between(dto.departureTime, dto.arrivalTime).toMinutes();
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
	
	//for booking and search feature
//	public List<Flight> searchFlightsByCode(String departureCode, String arrivalCode) {
//	    return flightRepo.findByDepartureAirportCodeAndArrivalAirportCode(departureCode, arrivalCode);
//	}
	
	//search feature for city and date
	public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDate departureDate) {
	    LocalDateTime startOfDay = departureDate.atStartOfDay();
	    LocalDateTime startOfNextDay = departureDate.plusDays(1).atStartOfDay();
	    
	    return flightRepo.findByDepCityAndArrivalCityAndDepDate(
	        departureCity, 
	        arrivalCity, 
	        startOfDay, 
	        startOfNextDay
	    );
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
