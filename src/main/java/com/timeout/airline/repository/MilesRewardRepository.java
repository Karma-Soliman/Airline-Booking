package com.timeout.airline.repository;

import com.timeout.airline.entity.MilesReward;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MilesRewardRepository extends JpaRepository<MilesReward, Long> {
	
    // Count flights for a client in a specific year
    @Query("SELECT COUNT(m) FROM MilesReward m WHERE m.client.numPassport = :numPassport AND m.year = :year")
    Long countFlightsByClientAndYear(@Param("numPassport") String numPassport, @Param("year") Integer year);
    
    // Get all rewards for a client in a year
    List<MilesReward> findByClientNumPassportAndYear(String numPassport, Integer year);
    
    
    // Find discount code for client in year
    @Query("SELECT m FROM MilesReward m WHERE m.client.numPassport = :numPassport AND m.year = :year AND m.discountCode IS NOT NULL")
    List<MilesReward> findDiscountCodeByClientAndYear(@Param("numPassport") String numPassport, @Param("year") Integer year);
}