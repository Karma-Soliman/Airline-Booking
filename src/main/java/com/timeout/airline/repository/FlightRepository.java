package com.timeout.airline.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.timeout.airline.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository <Flight, Long> {
	Optional<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findByDepartureAirportCode(String code);
    List<Flight> findByArrivalAirportCode(String code);
    //find using airport code
//    List<Flight> findByDepartureAirportCodeAndArrivalAirportCode(String departureCode, String arrivalCode);
    //searches to find available flights for this city, to that city for this specific date
    @Query("Select f from Flight f where f.departureAirport.city = :departureCity and "
    		+ "f.arrivalAirport.city = :arrivalCity and " +
            "f.departureTime >= :startOfDay AND " +
            "f.departureTime < :startOfNextDay")
    List<Flight> findByDepCityAndArrivalCityAndDepDate( @Param("departureCity") String departureCity, 
    		 @Param("arrivalCity") String arrivalCity, @Param("startOfDay") LocalDateTime startOfDay,
    	        @Param("startOfNextDay") LocalDateTime startOfNextDay);
}
