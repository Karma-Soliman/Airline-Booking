package com.timeout.airline.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.timeout.airline.entity.MilesReward;
import com.timeout.airline.service.MilesRewardService;

@RestController
@RequestMapping("/miles")
public class MilesRewardController {

	@Autowired
    private MilesRewardService milesRewardService;


    // GET CLIENT'S MILES REWARDS - GET /miles/passport/{passportNumber}
    @GetMapping("/passport/{passportNumber}")
    public ResponseEntity<List<MilesReward>> getClientMilesRewards(@PathVariable String passportNumber) {
        List<MilesReward> rewards = milesRewardService.getClientMilesRewards(passportNumber);
        return ResponseEntity.ok(rewards);
        
    }
    @GetMapping("/passport/{passportNumber}/discount")
    public ResponseEntity<Map<String, Object>> getClientDiscount(@PathVariable String passportNumber) {
        Integer currentYear = java.time.LocalDate.now().getYear();
        
        Long flightCount = milesRewardService.getClientFlightCountByPassport(passportNumber);
        String discountCode = milesRewardService.getClientDiscountCodeByPassport(passportNumber);
        
        Map<String, Object> response = new HashMap<>();
        response.put("passportNumber", passportNumber);
        response.put("year", currentYear);
        response.put("flightCount", flightCount);
        response.put("discountCode", discountCode);
        response.put("hasDiscount", discountCode != null);
        
        return ResponseEntity.ok(response);
    }
}
