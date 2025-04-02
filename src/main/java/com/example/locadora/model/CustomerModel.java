package com.example.locadora.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "customer")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String cpf;

    public CustomerModel() {
    }

    public CustomerModel(UUID id, String name, String cpf){
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public CustomerModel(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public UUID getId() {
        return id;
    }
}
