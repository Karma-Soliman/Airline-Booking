package com.timeout.airline.service;

//import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.timeout.airline.entity.Plane;
import com.timeout.airline.exception.ResourceNotFoundException;
import com.timeout.airline.repository.PlaneRepository;

@Service
public class PlaneService {

	@Autowired
	private PlaneRepository planeRepo;
	
	public Plane createPlane(Long id, Plane plane) {
		if (plane.getCapacity() <= 0) {
			throw new IllegalArgumentException("Plane must havea greater capcity");
		}
		return planeRepo.save(plane);
	}
	
	//READ
	public List<Plane> getAllPlanes(){
		return planeRepo.findAll();
	}
		
	public Plane getPlaneById(Long id){
	return planeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Plane not found with id: " + id));
	}
	//Scheduling
//	public List<Plane> getAvailablePlanes(LocalDateTime start, LocalDateTime end){
//		return planeRepo.findAvailablePlanes(start, end);
//	}
	
	//UPDATE
	public Plane updatePlane(Long id, Plane planeInfo) {
		Plane plane = getPlaneById(id);
		plane.setCapacity(planeInfo.getCapacity());
	
		return planeRepo.save(plane);
	}
	
	
	//DELETE
	public void deletePlane(Long id) {
		Plane plane = getPlaneById(id);
		//check if airport has flights
	    if (!plane.getFlights().isEmpty()) {
	        throw new IllegalStateException("Cannot delete plane with existing flights");
	    }
		planeRepo.delete(plane);
	}
		
}
