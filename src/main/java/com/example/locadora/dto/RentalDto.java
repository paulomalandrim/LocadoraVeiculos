package com.example.locadora.dto;

import com.example.locadora.model.RentalModel;
import com.example.locadora.model.VehicleModel;

import java.time.LocalDate;
import java.util.UUID;

public class RentalDto {
    private UUID id;
    private String customerId;
    private String vehicleId;
    private LocalDate initialDate;
    private LocalDate finalDate;

    public RentalDto() {
    }

    public RentalDto(UUID id, String customerId, String vehicleId, LocalDate initialDate, LocalDate finalDate) {
        this.id = id;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public RentalDto(String customerId, String vehicleId, LocalDate initialDate, LocalDate finalDate) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public RentalModel toEntity(){
        // Todo
    }
}
