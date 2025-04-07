package com.example.locadora.controller;

import com.example.locadora.dto.RentalDto;
import com.example.locadora.dto.VehicleDto;
import com.example.locadora.model.CustomerModel;
import com.example.locadora.model.RentalModel;
import com.example.locadora.model.VehicleModel;
import com.example.locadora.repository.CustomerRepository;
import com.example.locadora.repository.RentalRepository;
import com.example.locadora.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalRepository rentalRepository;
    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;

    public RentalController(RentalRepository rentalRepository,
                            VehicleRepository vehicleRepository,
                            CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    @PutMapping
    public ResponseEntity<RentalDto> save(@RequestBody RentalDto rentalDto){
        VehicleModel vehicleModel = vehicleRepository.findById(rentalDto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        CustomerModel customerModel = customerRepository.findById(rentalDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        RentalModel rentalToSave = new RentalModel(
                rentalDto.getId(),
                customerModel,
                vehicleModel,
                rentalDto.getInitialDate(),
                rentalDto.getFinalDate(),
                rentalDto.getTotal()
        );

        RentalModel rentalSaved = rentalRepository.save(rentalToSave);
        RentalDto rentalToReturn = new RentalDto(
                rentalSaved.getId(),
                rentalSaved.getCustomer().getId(),
                rentalSaved.getVehicle().getId(),
                rentalSaved.getInitialDate(),
                rentalSaved.getFinalDate(),
                rentalSaved.getTotal()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(rentalToReturn);
    }

    @GetMapping
    public ResponseEntity<List<RentalDto>> getAllRentals(){
        List<RentalModel> rentalModelList = rentalRepository.findAll();

        List<RentalDto> rentalDtoList = rentalModelList
                .stream()
                .map(r -> new RentalDto(
                        r.getId(),
                        r.getCustomer().getId(),
                        r.getVehicle().getId(),
                        r.getInitialDate(),
                        r.getFinalDate(),
                        r.getTotal()))
                .toList();

        if (rentalDtoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(rentalDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getById(@PathVariable UUID id){
        RentalModel rentalModel = rentalRepository.findById(id)
                .orElse(null);

        if (rentalModel == null){
            return ResponseEntity.noContent().build();
        }

        RentalDto rentalDto = new RentalDto(
                rentalModel.getId(),
                rentalModel.getCustomer().getId(),
                rentalModel.getVehicle().getId(),
                rentalModel.getInitialDate(),
                rentalModel.getFinalDate(),
                rentalModel.getTotal()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rentalDto);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<RentalDto>> getAllByCustomer(@PathVariable UUID customerId){

        List<RentalModel> rentalModelList = rentalRepository.findByCustomer_Id(customerId);

        if (rentalModelList == null || rentalModelList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<RentalDto> rentalDtoList = rentalModelList
                .stream()
                .map(r -> new RentalDto(
                        r.getId(),
                        r.getCustomer().getId(),
                        r.getVehicle().getId(),
                        r.getInitialDate(),
                        r.getFinalDate(),
                        r.getTotal()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rentalDtoList);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<RentalDto>> getAllByVehicle(@PathVariable UUID vehicleId){
        List<RentalModel> rentalModelList = rentalRepository.findByVehicle_Id(vehicleId);

        if (rentalModelList == null || rentalModelList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<RentalDto> rentalDtoList = rentalModelList
                .stream()
                .map(r -> new RentalDto(
                        r.getId(),
                        r.getCustomer().getId(),
                        r.getVehicle().getId(),
                        r.getInitialDate(),
                        r.getFinalDate(),
                        r.getTotal()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rentalDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        if (!rentalRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        rentalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RentalDto> changeById(@PathVariable UUID id,
                                                 @RequestBody RentalDto rentalDto){

        if (!id.equals(rentalDto.getId())){
            return ResponseEntity.badRequest().build();
        }

        RentalModel rentalToUpdate = rentalRepository.findById(id).orElse(null);

        if (rentalToUpdate == null){
            return ResponseEntity.notFound().build();
        }

        CustomerModel newCustomer = null;
        try{
            newCustomer = customerRepository
                    .findById(rentalDto.getCustomerId())
                    .orElseThrow( ()-> new RuntimeException("Customer not found."));
        } catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }

        VehicleModel newVehicle = null;
        try {
            newVehicle = vehicleRepository
                    .findById(rentalDto.getVehicleId())
                    .orElseThrow(()-> new RuntimeException("Vehicle not found."));

        } catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }

        rentalToUpdate.setCustomer(newCustomer);
        rentalToUpdate.setVehicle(newVehicle);
        rentalToUpdate.setInitialDate(rentalDto.getInitialDate());
        rentalToUpdate.setFinalDate(rentalDto.getFinalDate());
        rentalToUpdate.setTotal(rentalDto.getTotal());

        RentalModel rentalUpdated = rentalRepository.save(rentalToUpdate);
        RentalDto rentalToReturn = new RentalDto(
                rentalUpdated.getId(),
                rentalUpdated.getCustomer().getId(),
                rentalUpdated.getVehicle().getId(),
                rentalUpdated.getInitialDate(),
                rentalUpdated.getFinalDate(),
                rentalUpdated.getTotal()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rentalToReturn);
    }

}
