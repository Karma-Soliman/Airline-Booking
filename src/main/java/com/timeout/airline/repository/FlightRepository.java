package com.timeout.airline.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.timeout.airline.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository <Flight, Long> {
	Optional<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findByDepartureAirportCode(String code);
    List<Flight> findByArrivalAirportCode(String code);
	//for booking
    List<Flight> findByDepartureAirportCodeAndArrivalAirportCode(String departureCode, String arrivalCode);
}
