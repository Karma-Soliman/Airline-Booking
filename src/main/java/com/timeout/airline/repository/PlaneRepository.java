package com.timeout.airline.repository;

//import java.time.LocalDateTime;
//import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.timeout.airline.entity.Plane;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

@Repository
public interface PlaneRepository extends JpaRepository <Plane, Long> {
	Optional<Plane> findById(Long idPlane);
//	@Query("Select distinct p from Plane p where p.idPlane not in "
//			+ "(select f.plane.Idplane from Flight f where f.departureTime < :end and f.arrivalTime > :start)")
//	List<Plane> findAvailablePlanes(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
