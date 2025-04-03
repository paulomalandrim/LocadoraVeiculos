package com.example.locadora.repository;

import com.example.locadora.model.CustomerModel;
import com.example.locadora.model.RentalModel;
import com.example.locadora.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface RentalRepository extends JpaRepository<RentalModel, UUID> {

    Optional<List<RentalModel>> findAllByCustomer(CustomerModel customer);
    Optional<List<RentalModel>> findAllByVehicle(VehicleModel vehicle);

}
