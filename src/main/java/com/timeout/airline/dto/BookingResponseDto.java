package com.timeout.airline.dto;

import java.time.LocalDate;

import com.timeout.airline.entity.Book;
import com.timeout.airline.entity.Client;
import com.timeout.airline.entity.Flight;

public class BookingResponseDto {

	private Long bookingId;
    private String typeOfSeat;
    private LocalDate bookingDate;
    private Flight flight;
    private Client client;
    private String discountCode;  // only for display

    public BookingResponseDto(Book booking, String discountCode) {
        this.bookingId = booking.getIdReservation();
        this.typeOfSeat = booking.getTypeOfSeat();
        this.bookingDate = booking.getBookingDate();
        this.flight = booking.getFlight();
        this.client = booking.getClient();
        this.discountCode = discountCode;
    }

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getTypeOfSeat() {
		return typeOfSeat;
	}

	public void setTypeOfSeat(String typeOfSeat) {
		this.typeOfSeat = typeOfSeat;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
}
