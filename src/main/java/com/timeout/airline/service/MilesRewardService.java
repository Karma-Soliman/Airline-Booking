package com.timeout.airline.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeout.airline.entity.Book;
import com.timeout.airline.entity.MilesReward;
import com.timeout.airline.repository.MilesRewardRepository;

@Service
public class MilesRewardService {
	
    @Autowired
    private MilesRewardRepository milesRewardRepo;
    
    public MilesReward recordFlight(Book booking) {
    	
        Integer currentYear = LocalDate.now().getYear();
	
	    // Record in MilesReward 
	    MilesReward reward = new MilesReward();
	    reward.setClient(booking.getClient());
	    reward.setFlight(booking.getFlight());
	    reward.setDate(booking.getBookingDate());
	    reward.setYear(currentYear);
	    
	    MilesReward savedReward = milesRewardRepo.save(reward);
	    
	    //count number of flight to decide whether to generate discount count 
	    Long flightCount = milesRewardRepo.countFlightsByClientAndYear(booking.getClient().getNumPassport(), currentYear);
	    
	    //generate discount code]
	    if (flightCount == 3) {
	    	String discountCode = generateDiscount();
	    	savedReward.setDiscountCode(discountCode);
	    	milesRewardRepo.save(savedReward);
	    }
	    return savedReward;
	
	}
    
     private String generateDiscount() {
            String prefix = "MILES";
            String randomPart = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            return prefix + "-" + randomPart;
        }
     
  public String getClientDiscountCode(Book booking) {
     Integer currentYear = LocalDate.now().getYear();
      List<MilesReward> rewards = milesRewardRepo.findDiscountCodeByClientAndYear(booking.getClient().getNumPassport(), currentYear);
      
         if (!rewards.isEmpty()) {
             return rewards.get(0).getDiscountCode();
         }
         return null;
     }
    
     // Get flight count for current year
     public Long getClientFlightCount(Book booking) {
         Integer currentYear = LocalDate.now().getYear();
         return milesRewardRepo.countFlightsByClientAndYear(booking.getClient().getNumPassport(), currentYear);
     }
}
