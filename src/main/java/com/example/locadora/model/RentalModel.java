package com.example.locadora.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "rental")
public class RentalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleModel vehicle;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private Double total;

    public RentalModel() {
    }
    public RentalModel(UUID id, CustomerModel customer, VehicleModel vehicle, LocalDate initialDate, LocalDate finalDate, Double total) {
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.total = total;
    }

    public RentalModel(CustomerModel customer, VehicleModel vehicle, LocalDate initialDate, LocalDate finalDate, Double total) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.total = total;
    }

    public UUID getId() {
        return id;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public VehicleModel getVehicle() {
        return vehicle;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public Double getTotal() {
        return total;
    }
}
