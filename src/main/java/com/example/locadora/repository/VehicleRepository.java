package com.example.locadora.repository;

import com.example.locadora.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface VehicleRepository extends JpaRepository<VehicleModel, UUID> {

    Optional<VehicleModel> findByPlate(String plate);

}
