package com.timeout.airline.controller;

import com.timeout.airline.dto.BookingRequestDto;
import com.timeout.airline.entity.Book;
import com.timeout.airline.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    // CREATE BOOKING - POST /api/bookings
    @PostMapping
    public ResponseEntity<Book> createBooking(@RequestBody BookingRequestDto request) {
        Book booking = bookingService.createBooking(request);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
    
    // GET ALL BOOKINGS - GET /api/bookings
    @GetMapping
    public ResponseEntity<List<Book>> getAllBookings() {
        List<Book> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }
    
    // GET BOOKING BY ID - GET /api/bookings/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookingById(@PathVariable Long id) {
        Book booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }
    
    // GET BOOKINGS BY CLIENT - GET /api/bookings/client/{clientId}
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Book>> getBookingsByClient(@PathVariable Long clientId) {
        List<Book> bookings = bookingService.getBookingsByClientId(clientId);
        return ResponseEntity.ok(bookings);
    }
    
    // GET BOOKINGS BY FLIGHT - GET /api/bookings/flight/{flightNumber}
    @GetMapping("/flight/{flightNumber}")
    public ResponseEntity<List<Book>> getBookingsByFlight(@PathVariable String flightNumber) {
        List<Book> bookings = bookingService.getBookingsByFlightNumber(flightNumber);
        return ResponseEntity.ok(bookings);
    }
    
    // UPDATE BOOKING - PUT /api/bookings/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBooking(@PathVariable Long id, @RequestParam String seatType) {
        Book updatedBooking = bookingService.updateBooking(id, seatType);
        return ResponseEntity.ok(updatedBooking);
    }
    
    // DELETE BOOKING - DELETE /api/bookings/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}