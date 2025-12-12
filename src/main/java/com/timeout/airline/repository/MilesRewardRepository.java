package com.timeout.airline.repository;

import com.timeout.airline.entity.MilesReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilesRewardRepository extends JpaRepository<MilesReward, Long> {
}