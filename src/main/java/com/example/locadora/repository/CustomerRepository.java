package com.example.locadora.repository;

import com.example.locadora.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CustomerRepository extends JpaRepository<CustomerModel, UUID> {

}
