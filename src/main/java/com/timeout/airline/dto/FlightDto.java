package com.timeout.airline.dto;

import java.time.LocalDateTime;

public class FlightDto {
	//scheduling flights
    public String departureAirportCode;
    public String arrivalAirportCode;
    public String planeType;
    public String flightNumber;
    public LocalDateTime departureTime;
    public LocalDateTime arrivalTime;
    
    //seat information
    public Integer seats;
    public Integer firstClassSeats;
    public Integer premiumClassSeats;
    public Integer businessClassSeats;
    public Integer economyClassSeats;
    
    //price information
    public Double firstClassPrice;
    public Double premiumClassPrice;
    public Double businessClassPrice;
    public Double economyClassPrice;
  
    
}
