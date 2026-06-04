package com.example.swiftroute.dispatch.infrastructure.web;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swiftroute.dispatch.application.service.DispatchApplicationService;
import com.example.swiftroute.dispatch.domain.model.Vehicle;
import com.example.swiftroute.dispatch.infrastructure.web.dto.CreateVehicleRequest;
import com.example.swiftroute.dispatch.infrastructure.web.dto.VehicleResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final DispatchApplicationService dispatchService;

    public VehicleController(DispatchApplicationService dispatchService){
        this.dispatchService = dispatchService;
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> createVehicle(@Valid @RequestBody CreateVehicleRequest request){
        Vehicle vehicle = dispatchService.createVehicle(request.getType(), request.getLicensePlate(), request.getMaxWeightKg(), request.getMaxVolumeM3());
        return ResponseEntity.status(HttpStatus.CREATED).body(VehicleResponse.from(vehicle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicle(@PathVariable UUID id){
        Vehicle vehicle = dispatchService.getVehicle(id);
        return ResponseEntity.ok(VehicleResponse.from(vehicle));
    }
}
