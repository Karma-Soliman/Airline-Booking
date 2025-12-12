package com.timeout.airline.repository;

import com.timeout.airline.entity.Book;
import com.timeout.airline.entity.Flight; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByClientIdUser(Long clientId);
    
    List<Book> findByFlightFlightNumber(String flightNumber);
    
     // Custom query to count the number of seats already booked for a specific flight and seat type.
    @Query("SELECT COUNT(b) FROM Book b WHERE b.flight = :flight AND UPPER(b.typeOfSeat) = UPPER(:typeOfSeat)")
    Long countBookedSeatsByFlightAndSeatType(@Param("flight") Flight flight, @Param("typeOfSeat") String typeOfSeat);
}