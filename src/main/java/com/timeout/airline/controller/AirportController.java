package com.timeout.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.timeout.airline.entity.Airport;
import com.timeout.airline.service.AirportService;

@RestController
@RequestMapping("/api/airport")
public class AirportController {
	@Autowired
	private AirportService airportService;
	
	@PostMapping
	public ResponseEntity<Airport> createAirport(@RequestBody Airport airport){
		Airport createAir = airportService.createAirport(airport);
		return new ResponseEntity<>(createAir, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Airport>> getAllAirports(){
		List<Airport> airports = airportService.getAllAirports();
		return ResponseEntity.ok(airports);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Airport> getAirportById(@PathVariable Long id){
		Airport airport = airportService.getAirportById(id);
		return ResponseEntity.ok(airport);
	} 
	
	@GetMapping("/{code}")
	public ResponseEntity<Airport> getAirportByCode(@PathVariable String code){
		Airport airport = airportService.getAirportByCode(code);
		return ResponseEntity.ok(airport);
	} 
	
	@GetMapping("/{city}")
	public ResponseEntity<List<Airport>> searchAirportByCity(@PathVariable String city){
		List<Airport> airport = airportService.searchAirportByCity(city);
		return ResponseEntity.ok(airport);
	} 
	
	@GetMapping("/{country}")
	public ResponseEntity<List<Airport>> searchAirportByCountry(@PathVariable String country){
		List<Airport> airport = airportService.searchAirportByCountry(country);
		return ResponseEntity.ok(airport);
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airport){
		Airport updateAirport = airportService.updateAirport(id, airport);
		return ResponseEntity.ok(updateAirport);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Airport> deleteAirport(@PathVariable Long id){
		airportService.deleteAirport(id);
		return ResponseEntity.noContent().build();
	} 
	
	
}
