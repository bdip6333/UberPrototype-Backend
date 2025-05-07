package com.example.uber_backend.Repository;

import com.example.uber_backend.Entity.Cab;
import com.example.uber_backend.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CabRepository extends JpaRepository<Cab, Integer> {

    @Query(value = "SELECT * FROM cab WHERE available = true ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Cab getAvailableCabRandomly();
}
