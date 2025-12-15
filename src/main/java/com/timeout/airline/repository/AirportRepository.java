package com.timeout.airline.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.timeout.airline.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository <Airport, Long> {
	Optional<Airport> findByCode(String code);
	List<Airport> findByCity(String city);
	List<Airport> findByCountry(String country);
	List<Airport> findByCityAndCountry(String city, String country);
	Optional<Airport> findByName(String name);
}
