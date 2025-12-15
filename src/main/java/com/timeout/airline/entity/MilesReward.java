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
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "id_flight", nullable = false)
    private Flight flight;
    
    @Column(nullable = false)
    private LocalDate bookingDate;
    
    @Column(nullable = true)
    private String discountCode;

   
	@Column(nullable = true)
    private Boolean discountUsed;
    
    @Column(nullable = false)
    private Integer year;

    public MilesReward() {
    	this.discountUsed = false;
        this.year = LocalDate.now().getYear();
    }
//maybe add Book booking
    public MilesReward(Long id, Client client, Flight flight, LocalDate date) {
        this.id = id;
        this.client = client;
        this.flight = flight;
        this.bookingDate = date;
        // this.booking = booking;
    }

    
    public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public Boolean getDiscountUsed() {
		return discountUsed;
	}
	public void setDiscountUsed(Boolean discountUsed) {
		this.discountUsed = discountUsed;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
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
        return bookingDate;
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
        this.bookingDate = date;
    }
}