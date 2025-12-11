package com.timeout.airline.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.timeout.airline.dto.FlightDto;
import com.timeout.airline.entity.Flight;
import com.timeout.airline.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

		@Autowired
		private FlightService flightService;
		
		@PostMapping
		public ResponseEntity<Flight> createFlight(@RequestBody FlightDto dto){
			Flight createFlight = flightService.createFlight(dto);
			return new ResponseEntity<>(createFlight, HttpStatus.CREATED);
		}
		
		@GetMapping
		public ResponseEntity<List<Flight>> getAllFlights(){
			List<Flight> flights = flightService.getAllFlights();
			return ResponseEntity.ok(flights);
			
		}
		
		@GetMapping("/id/{id}")
		public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
			Flight flight = flightService.getFlightById(id);
			return ResponseEntity.ok(flight);
		} 
		
		@GetMapping("/number/{code}")
		public ResponseEntity<Flight> getFlightByNumber(@PathVariable String code){
			Flight flightCode = flightService.getFlightByNumber(code);
			return ResponseEntity.ok(flightCode);
		} 
		
		@GetMapping("/departure/{code}")
		public ResponseEntity<List<Flight>> getByDepartureAirport(@PathVariable String code){
			List<Flight> flightDepart = flightService.getByDepartureAirport(code);
			return ResponseEntity.ok(flightDepart);
			
		}
		
		@GetMapping("/arrival/{code}")
		public ResponseEntity<List<Flight>> getByArrivalAirport(@PathVariable String code){
			List<Flight> flightArrival = flightService.getByArrivalAirport(code);
			return ResponseEntity.ok(flightArrival);
		}
		
		//FOR BOOKING LATER
	// GET /flight/search?departureCity=Paris&arrivalCity=London&departureDate=2024-12-15
		
		@GetMapping("/search")
		public ResponseEntity<List<Flight>> searchFlights(@RequestParam String departureCity, @RequestParam String arrivalCity, 
				@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate){ 
			//this is for spring to properly comprehend that it is 
			List<Flight> flights = flightService.searchFlights(departureCity, arrivalCity, departureDate);
			return ResponseEntity.ok(flights);
		}
		
//		@GetMapping("/search")
//		public ResponseEntity<List<Flight>> searchFlightsByCode(@RequestParam String from, 
//										@RequestParam String to){
//			List<Flight> flightsByCodes = flightService.searchFlightsByCode(from, to);
//			return ResponseEntity.ok(flightsByCodes);
//		}
		
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
