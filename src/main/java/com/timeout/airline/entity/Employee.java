package com.timeout.airline.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee extends User {
    
    @Column(unique = true, nullable = false)
    private String numEmp;
    
    private String profession;
    
    private String title;

    // Constructors
    public Employee() {
        super();
    }

    public Employee(Long idUser, String firstname, String lastname, String address, 
                    String email, String phone, LocalDate birthdate, 
                    String numEmp, String profession, String title) {
        super(idUser, firstname, lastname, address, email, phone, birthdate);
        this.numEmp = numEmp;
        this.profession = profession;
        this.title = title;
    }

    // Getters
    public String getNumEmp() {
        return numEmp;
    }

    public String getProfession() {
        return profession;
    }

    public String getTitle() {
        return title;
    }

    // Setters
    public void setNumEmp(String numEmp) {
        this.numEmp = numEmp;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}