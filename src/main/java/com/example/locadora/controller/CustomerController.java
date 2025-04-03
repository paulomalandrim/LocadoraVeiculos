package com.example.locadora.controller;

import com.example.locadora.dto.CustomerDto;
import com.example.locadora.model.CustomerModel;
import com.example.locadora.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PutMapping
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto){
        CustomerModel customerToSave = customerDto.toEntity();
        CustomerModel customerSaved = customerRepository.save(customerToSave);
        CustomerDto customerToReturn = new CustomerDto(
                customerSaved.getId(),
                customerSaved.getName(),
                customerSaved.getCpf()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerToReturn);


    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        List<CustomerModel> customerModelList = customerRepository.findAll();

        List<CustomerDto> customerDtoList = customerModelList
                .stream()
                .map(c -> new CustomerDto(c.getId(), c.getName(),c.getCpf()))
                .toList();

        if (customerDtoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable UUID id){
        CustomerModel customerModel = customerRepository.findById(id).orElse(null);

        if (customerModel == null){
            return ResponseEntity.noContent().build();
        }

        CustomerDto customerDto = new CustomerDto(
                customerModel.getId(),
                customerModel.getName(),
                customerModel.getCpf());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if (! customerRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> changeById(@PathVariable UUID id,
                                                  @RequestBody CustomerDto customerDto){

        if (id != customerDto.getId()){
            return ResponseEntity.badRequest().build();
        }

        if (!customerRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        CustomerModel customerToUpdate = customerDto.toEntity();
        CustomerModel customerUpdated = customerRepository.save(customerToUpdate);
        CustomerDto customerToReturn = new CustomerDto(
                customerUpdated.getId(),
                customerUpdated.getName(),
                customerUpdated.getCpf()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerToReturn);
    }
}
