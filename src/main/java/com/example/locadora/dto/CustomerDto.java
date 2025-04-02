package com.example.locadora.dto;

import com.example.locadora.model.CustomerModel;

import java.util.UUID;

public class CustomerDto {
    private UUID id;
    private String name;
    private String cpf;

    public CustomerDto() {
    }

    public CustomerDto(UUID id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public CustomerModel toEntity(){
        return new CustomerModel(
                this.name,
                this.cpf);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

}
