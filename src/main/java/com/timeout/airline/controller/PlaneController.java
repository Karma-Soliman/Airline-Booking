package com.timeout.airline.controller;

//import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.timeout.airline.entity.Plane;
import com.timeout.airline.service.PlaneService;
@RestController
@RequestMapping("/api/plane")
public class PlaneController {
	@Autowired
	private PlaneService planeService;
	
	@PostMapping
	public ResponseEntity<Plane> createPlane(@RequestBody Plane plane){
		Plane createsPlane = planeService.createPlane(plane);
		return new ResponseEntity<>(createsPlane, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Plane>> getAllPlanes(){
		List<Plane> planes = planeService.getAllPlanes();
		return ResponseEntity.ok(planes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Plane> getPlaneById(@PathVariable Long id){
		Plane plane = planeService.getPlaneById(id);
		return ResponseEntity.ok(plane);
	} 
	
	//if we decide to add scheduling
//	@GetMapping("/{id}")
//	public ResponseEntity<Plane> getPlaneAvailability(@PathVariable LocalDateTime start, LocalDateTime end){
//		Plane planeAvailability = planeService.getAvailablePlanes(start, end);
//		return ResponseEntity.ok(plane);
//	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<Plane> updatePlane(@PathVariable Long id, @RequestBody Plane plane){
		Plane updatePlane = planeService.updatePlane(id, plane);
		return ResponseEntity.ok(updatePlane);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Plane> deletePlane(@PathVariable Long id){
		planeService.deletePlane(id);
		return ResponseEntity.noContent().build();
	} 
}
