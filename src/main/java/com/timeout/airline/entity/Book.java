package com.timeout.airline.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    
    @ManyToOne
    @JoinColumn(name = "id_flight", nullable = false)
    private Flight flight;
    
    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;
    
    @Column(nullable = false)
    private String typeOfSeat; // FIRST, PREMIUM, BUSINESS, ECONOMICS
    
    @Column(nullable = false)
    private LocalDate bookingDate;

    // Constructors
    public Book() {
    }

    public Book(Long idReservation, Flight flight, Client client, String typeOfSeat, LocalDate bookingDate) {
        this.idReservation = idReservation;
        this.flight = flight;
        this.client = client;
        this.typeOfSeat = typeOfSeat;
        this.bookingDate = bookingDate;
    }

    // Getters
    public Long getIdReservation() {
        return idReservation;
    }

    public Flight getFlight() {
        return flight;
    }

    public Client getClient() {
        return client;
    }

    public String getTypeOfSeat() {
        return typeOfSeat;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    // Setters
    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setTypeOfSeat(String typeOfSeat) {
        this.typeOfSeat = typeOfSeat;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}