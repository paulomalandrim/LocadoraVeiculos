package com.example.locadora.dto;

import com.example.locadora.model.CustomerModel;
import com.example.locadora.model.RentalModel;
import com.example.locadora.model.VehicleModel;

import java.time.LocalDate;
import java.util.UUID;

public class RentalDto {
    private UUID id;
    private UUID customerId;
    private UUID vehicleId;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private Double total;

    public RentalDto(UUID id, UUID customerId, UUID vehicleId, LocalDate initialDate, LocalDate finalDate, Double total) {
        this.id = id;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.total = total;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getVehicleId() {
        return vehicleId;
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
