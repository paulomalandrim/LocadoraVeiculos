package com.example.locadora.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class VehicleDtoUnitTest {

    @Test
    void should_create_a_VehicleDto() {
        // Arrange
        VehicleDto vehicleDto = new VehicleDto(
                UUID.randomUUID(),
                "Toyota Corolla",
                "ABC1234",
                100.0
        );

        // Act
        UUID id = vehicleDto.getId();
        String model = vehicleDto.getModel();
        String plate = vehicleDto.getPlate();
        Double dailyRate = vehicleDto.getDailyRate();

        // Assert
        Assertions.assertNotNull(id);
        Assertions.assertEquals("Toyota Corolla", model);
        Assertions.assertEquals("ABC1234", plate);
        Assertions.assertEquals(100.0, dailyRate);
    }
}
