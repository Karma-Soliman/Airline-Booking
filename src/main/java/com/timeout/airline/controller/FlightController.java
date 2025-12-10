package com.timeout.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.timeout.airline.entity.Flight;
import com.timeout.airline.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

		@Autowired
		private FlightService flightService;
		
		@PostMapping
		public ResponseEntity<Flight> createFlight(@RequestBody Flight flight){
			Flight createFlight = flightService.createFlight(flight);
			return new ResponseEntity<>(createFlight, HttpStatus.CREATED);
		}
		
		@GetMapping
		public ResponseEntity<List<Flight>> getAllFlights(){
			List<Flight> flights = flightService.getAllFlights();
			return ResponseEntity.ok(flights);
			
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
			Flight flight = flightService.getFlightById(id);
			return ResponseEntity.ok(flight);
		} 
		
		@GetMapping("/{code}")
		public ResponseEntity<Flight> getFlightByNumber(@PathVariable String code){
			Flight flightCode = flightService.getFlightByNumber(code);
			return ResponseEntity.ok(flightCode);
		} 
		
		@GetMapping
		public ResponseEntity<List<Flight>> getByDepartureAirport(@PathVariable String code){
			List<Flight> flightDepart = flightService.getByDepartureAirport(code);
			return ResponseEntity.ok(flightDepart);
			
		}
		
		@GetMapping
		public ResponseEntity<List<Flight>> getByArrivalAirport(@PathVariable String code){
			List<Flight> flightArrival = flightService.getByArrivalAirport(code);
			return ResponseEntity.ok(flightArrival);
			
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight){
			Flight updateFlight = flightService.updateFlight(id, flight);
			return ResponseEntity.ok(updateFlight);
		} 
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Flight> deleteFlight(@PathVariable Long id){
			flightService.deleteFlight(id);
			return ResponseEntity.noContent().build();
		} 
}
