package com.example.locadora.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "vehicle")
public class VehicleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String model;
    private String plate;
    private Double dailyRate;

    public VehicleModel() {
    }

    public VehicleModel(UUID id, String model, String plate, Double dailyRate) {
        this.id = id;
        this.model = model;
        this.plate = plate;
        this.dailyRate = dailyRate;
    }

    public VehicleModel(String model, String plate, Double dailyRate) {
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
}
