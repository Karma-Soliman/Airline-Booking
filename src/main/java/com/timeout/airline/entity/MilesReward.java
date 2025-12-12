package com.timeout.airline.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "miles_rewards")
public class MilesReward {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "flight_number", nullable = false)
    private Flight flight;
    
    @Column(nullable = false)
    private LocalDate date;

    public MilesReward() {
    }

    public MilesReward(Long id, Client client, Flight flight, LocalDate date) {
        this.id = id;
        this.client = client;
        this.flight = flight;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Flight getFlight() {
        return flight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}