package com.example.locadora.dto;

import com.example.locadora.model.VehicleModel;

import java.util.UUID;

public class VehicleDto {
    private UUID id;
    private String model;
    private String plate;
    private Double dailyRate;

    public VehicleDto() {
    }

    public VehicleDto(String model, String plate, Double dailyRate) {
        this.model = model;
        this.plate = plate;
        this.dailyRate = dailyRate;
    }

    public VehicleDto(UUID id, String model, String plate, Double dailyRate) {
        this.id = id;
        this.model = model;
        this.plate = plate;
        this.dailyRate = dailyRate;
    }

    public UUID getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public Double getDailyRate() {
        return dailyRate;
    }

    public VehicleModel toEntity(){
        return new VehicleModel(
                this.id,
                this.model,
                this.plate,
                this.dailyRate
        );
    }
}
