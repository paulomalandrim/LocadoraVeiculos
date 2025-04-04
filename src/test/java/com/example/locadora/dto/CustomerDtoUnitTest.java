package com.example.locadora.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class CustomerDtoUnitTest {

    @Test
    void should_create_a_CustomerDto() {
        // Arrange
        CustomerDto customerDto = new CustomerDto(
                UUID.randomUUID(),
                "John Doe",
                "12345678901"
        );

        // Act
        UUID id = customerDto.getId();
        String name = customerDto.getName();
        String cpf = customerDto.getCpf();

        // Assert
        Assertions.assertNotNull(id);
        Assertions.assertEquals("John Doe", name);
        Assertions.assertEquals("12345678901", cpf);
    }
}
