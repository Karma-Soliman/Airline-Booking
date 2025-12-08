package com.timeout.airline.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
public class Client extends User {
    
    @Column(unique = true, nullable = false)
    private String numPassport;

    // Constructors
    public Client() {
        super();
    }

    public Client(Long idUser, String firstname, String lastname, String address, 
                  String email, String phone, LocalDate birthdate, String numPassport) {
        super(idUser, firstname, lastname, address, email, phone, birthdate);
        this.numPassport = numPassport;
    }

    // Getter
    public String getNumPassport() {
        return numPassport;
    }

    // Setter
    public void setNumPassport(String numPassport) {
        this.numPassport = numPassport;
    }
}