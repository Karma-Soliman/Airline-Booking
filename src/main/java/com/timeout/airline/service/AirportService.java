package com.timeout.airline.service;

import org.springframework.stereotype.Service;

import com.timeout.airline.entity.Airport;
import com.timeout.airline.exception.ResourceNotFoundException;
import com.timeout.airline.repository.AirportRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AirportService {
	
	@Autowired
	private AirportRepository airportRepo;
	
	//CREATE
	public Airport createAirport(Airport airport) {
		//check if airport already exists
		if (airportRepo.findByCode(airport.getCode()).isPresent()) {
			throw new IllegalArgumentException("Airport with code" + airport.getCode() + "already exists.");
		}
		return airportRepo.save(airport);
	}
	
	//READ
	public List<Airport> getAllAirports(){
		return airportRepo.findAll();
	}
	
	public Airport getAirportById(Long id){
		return airportRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Airport not found with id: " + id));
	}
	
	public Airport getAirportByCode(String code){
		return airportRepo.findByCode(code)
				.orElseThrow(() -> new ResourceNotFoundException("Airport not found with code: " + code));
	}
	
	public List<Airport> searchAirportByCity(String city){
		return airportRepo.findByCity(city);
	}
	
	public List<Airport> searchAirportByCountry(String country){
		return airportRepo.findByCountry(country);
	}
	
	//UPDATE
	public Airport updateAirport(Long id, Airport airportInfo) {
		Airport air = getAirportById(id);
		air.setCode(airportInfo.getCode());
		air.setName(airportInfo.getName());
		air.setCity(airportInfo.getCity());
		air.setCountry(airportInfo.getCountry());
		
		return airportRepo.save(air);
	}
	
	
	//DELETE
	public void deleteAirport(Long id) {
		Airport air = getAirportById(id);
		//check if airport has flights
	    if (!air.getDepartingFlights().isEmpty() || !air.getArrivalFlights().isEmpty()) {
	        throw new IllegalStateException("Cannot delete airport with existing flights");
	    }
		airportRepo.delete(air);
	}
}
