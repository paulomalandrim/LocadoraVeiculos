package com.example.locadora.controller;

import com.example.locadora.dto.VehicleDto;
import com.example.locadora.model.VehicleModel;
import com.example.locadora.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @PutMapping
    public ResponseEntity<VehicleDto> save(@RequestBody VehicleDto vehicleDto){
        VehicleModel vehicleToSave = vehicleDto.toEntity();
        VehicleModel vehicleSaved = vehicleRepository.save(vehicleToSave);
        VehicleDto vehicleToReturn = new VehicleDto(
                vehicleSaved.getId(),
                vehicleSaved.getModel(),
                vehicleSaved.getPlate(),
                vehicleSaved.getDailyRate()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vehicleToReturn);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAllVehicles(){
        List<VehicleModel> vehicleModelList = vehicleRepository.findAll();

        List<VehicleDto> vehicleDtoList = vehicleModelList
                .stream()
                .map(v -> new VehicleDto(v.getId(), v.getModel(), v.getPlate(), v.getDailyRate()))
                .toList();

        if (vehicleDtoList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(vehicleDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> getById(@PathVariable UUID id){
        VehicleModel vehicleModel = vehicleRepository.findById(id)
                .orElse(null);

        if (vehicleModel == null){
            return ResponseEntity.noContent().build();
        }

        VehicleDto vehicleDto = new VehicleDto(
                vehicleModel.getId(),
                vehicleModel.getModel(),
                vehicleModel.getPlate(),
                vehicleModel.getDailyRate());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vehicleDto);
    }

    @GetMapping("/plate/{plate}")
    public ResponseEntity<VehicleDto> getByPlate(@PathVariable String plate){
        VehicleModel vehicleModel = vehicleRepository.findByPlate(plate)
                .orElse(null);

        if (vehicleModel == null){
            return ResponseEntity.noContent().build();
        }

        VehicleDto vehicleDto = new VehicleDto(
                vehicleModel.getId(),
                vehicleModel.getModel(),
                vehicleModel.getPlate(),
                vehicleModel.getDailyRate());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vehicleDto);
    }
}
