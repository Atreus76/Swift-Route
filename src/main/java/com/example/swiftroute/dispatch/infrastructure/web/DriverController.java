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
import com.example.swiftroute.dispatch.domain.model.Driver;
import com.example.swiftroute.dispatch.infrastructure.web.dto.CreateDriverRequest;
import com.example.swiftroute.dispatch.infrastructure.web.dto.DriverResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    private final DispatchApplicationService dispatchService;

    public DriverController(DispatchApplicationService dispatchService){
        this.dispatchService = dispatchService;
    }

    @PostMapping
    public ResponseEntity<DriverResponse> createDriver(@Valid @RequestBody CreateDriverRequest request){
        Driver driver = dispatchService.createDriver(request.getName(), request.getLicenseNumber(), request.getPhoneNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(DriverResponse.from(driver));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getDriver(@PathVariable UUID id){
        Driver driver = dispatchService.getDriver(id);
        return ResponseEntity.ok(DriverResponse.from(driver));
    }
}
