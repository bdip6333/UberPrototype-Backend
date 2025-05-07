package com.example.uber_backend.Repository;

import com.example.uber_backend.Entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    @Query(value = "SELECT * FROM driver WHERE cab_id = :cabId", nativeQuery = true)
    Driver getDriverByCabId(@Param("cabId") int cabId);
}