package com.timeout.airline.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingRequestDto {
    private String lastname;
    private String firstname;
    private String passportNumber;
    private LocalDate birthdate;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureHour;
    private LocalDateTime arrivalHour;
    private String flightNumber;
    private String typeOfSeat; 
    
    // Optional fields for creating new client
    private String email;
    private String phone;
    private String address;

    public BookingRequestDto() {
    }

    public BookingRequestDto(String lastname, String firstname, String passportNumber, 
                         LocalDate birthdate, String departureCity, String arrivalCity, 
                         LocalDateTime departureHour, LocalDateTime arrivalHour, 
                         String flightNumber, String typeOfSeat, String email, 
                         String phone, String address) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.passportNumber = passportNumber;
        this.birthdate = birthdate;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureHour = departureHour;
        this.arrivalHour = arrivalHour;
        this.flightNumber = flightNumber;
        this.typeOfSeat = typeOfSeat;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public LocalDateTime getDepartureHour() {
        return departureHour;
    }

    public LocalDateTime getArrivalHour() {
        return arrivalHour;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getTypeOfSeat() {
        return typeOfSeat;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setDepartureHour(LocalDateTime departureHour) {
        this.departureHour = departureHour;
    }

    public void setArrivalHour(LocalDateTime arrivalHour) {
        this.arrivalHour = arrivalHour;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setTypeOfSeat(String typeOfSeat) {
        this.typeOfSeat = typeOfSeat;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}