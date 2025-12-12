package com.timeout.airline.service;

import com.timeout.airline.dto.BookingRequestDto; 
import com.timeout.airline.entity.Book;
import com.timeout.airline.entity.Client;
import com.timeout.airline.entity.Flight;
import com.timeout.airline.entity.MilesReward; 
import com.timeout.airline.exception.ResourceNotFoundException;
import com.timeout.airline.exception.ValidationException; 
import com.timeout.airline.repository.BookRepository;
import com.timeout.airline.repository.ClientRepository;
import com.timeout.airline.repository.FlightRepository;
import com.timeout.airline.repository.MilesRewardRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
public class BookingService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private FlightRepository flightRepository;
    
    @Autowired
    private MilesRewardRepository milesRewardRepository; 
    
 
    @Transactional
    public Book createBooking(BookingRequestDto request) { 
        
        // Find or create client by passport number
        Client client = clientRepository.findByNumPassport(request.getPassportNumber())
                .orElseGet(() -> createNewClient(request));
        
       
        // Use the correct repository method to find by the business key (flightNumber)
        Flight flight = flightRepository.findByFlightNumber(request.getFlightNumber()) 
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Flight not found with number: " + request.getFlightNumber()));
        
        // Validate flight details match request
        if (!flight.getDepartureAirport().getCity().equalsIgnoreCase(request.getDepartureCity())) { 
            throw new ValidationException(
                "Departure city does not match. Expected: " + flight.getDepartureAirport().getCity() + 
                ", Got: " + request.getDepartureCity());
        }
        
        // Get city from the associated Airport entity
        if (!flight.getArrivalAirport().getCity().equalsIgnoreCase(request.getArrivalCity())) { 
            throw new ValidationException(
                "Arrival city does not match. Expected: " + flight.getArrivalAirport().getCity() + 
                ", Got: " + request.getArrivalCity());
        }
        
        //  Check seat availability
        int totalCapacity = flight.getPlane().getCapacity(); 
        Long bookedSeats = (long) bookRepository.findByFlightFlightNumber(request.getFlightNumber()).size();
        
        if (bookedSeats >= totalCapacity) {
            throw new ValidationException(
                "No seats available for flight " + request.getFlightNumber() + 
                ". Capacity: " + totalCapacity + ", Booked: " + bookedSeats);
        }
        
        // Validate seat type
        String seatType = request.getTypeOfSeat().toUpperCase();
        if (!isValidSeatType(seatType)) {
            throw new ValidationException(
                "Invalid seat type: " + request.getTypeOfSeat() + 
                ". Must be: FIRST, PREMIUM, BUSINESS, or ECONOMICS");
        }
        
        //  Create booking
        Book booking = new Book();
        booking.setClient(client);
        booking.setFlight(flight);
        booking.setTypeOfSeat(seatType);
        booking.setBookingDate(LocalDate.now());
        booking = bookRepository.save(booking);
        
        
        // Record in MilesReward 
        MilesReward reward = new MilesReward();
        reward.setClient(client);
        reward.setFlight(flight);
        reward.setDate(LocalDate.now());
        milesRewardRepository.save(reward);
        
        
        return booking;
    }
    
    // Helper: Create new client if doesn't exist
    private Client createNewClient(BookingRequestDto request) { 
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
             throw new ValidationException("Email is required to create a new client.");
        }

        Client client = new Client();
        client.setFirstname(request.getFirstname());
        client.setLastname(request.getLastname());
        client.setNumPassport(request.getPassportNumber());
        client.setBirthdate(request.getBirthdate());
        
        // Set fields from DTO (email is validated above)
        client.setEmail(request.getEmail());
        // Use provided contact info or defaults
        client.setPhone(request.getPhone());
        client.setAddress(request.getAddress());
        
        return clientRepository.save(client);
    }
    

    
    // Helper: Validate seat type
    private boolean isValidSeatType(String seatType) {
        return seatType.equals("FIRST") || 
               seatType.equals("PREMIUM") || 
               seatType.equals("BUSINESS") || 
               seatType.equals("ECONOMICS");
    }
    
    //  Get all bookings
    public List<Book> getAllBookings() {
        return bookRepository.findAll();
    }
    
    //  Get booking by ID
    public Book getBookingById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Booking not found with id: " + id));
    }
    
    //  Get bookings by client
    public List<Book> getBookingsByClientId(Long clientId) {
        return bookRepository.findByClientIdUser(clientId);
    }
    
    // Get bookings by flight
    public List<Book> getBookingsByFlightNumber(String flightNumber) {
        return bookRepository.findByFlightFlightNumber(flightNumber);
    }
    
    //  Update booking (change seat type)
    @Transactional
    public Book updateBooking(Long id, String newSeatType) {
        Book booking = getBookingById(id);
        
        String seatType = newSeatType.toUpperCase();
        if (!isValidSeatType(seatType)) {
            throw new ValidationException("Invalid seat type: " + newSeatType);
        }
        
        booking.setTypeOfSeat(seatType);
        return bookRepository.save(booking);
    }
    
    // Cancel booking
    @Transactional
    public void deleteBooking(Long id) {
        Book booking = getBookingById(id);
        
        // Delete the booking
        bookRepository.delete(booking);
    }
}