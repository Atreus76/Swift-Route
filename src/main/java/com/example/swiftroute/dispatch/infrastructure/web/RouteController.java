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
import com.example.swiftroute.dispatch.domain.model.Route;
import com.example.swiftroute.dispatch.infrastructure.web.dto.AddStopRequest;
import com.example.swiftroute.dispatch.infrastructure.web.dto.AssignDriverRequest;
import com.example.swiftroute.dispatch.infrastructure.web.dto.CreateRouteRequest;
import com.example.swiftroute.dispatch.infrastructure.web.dto.RouteResponse;

@RestController
@RequestMapping("/api/routes")
public class RouteController {
    private final DispatchApplicationService dispatchService;

    public RouteController(DispatchApplicationService dispatchService){
        this.dispatchService = dispatchService;
    }

    @PostMapping
    public ResponseEntity<RouteResponse> createRoute(@RequestBody CreateRouteRequest request){
        Route route = dispatchService.createRoute(request.getPlannedDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(RouteResponse.from(route));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteResponse> getRoute(@PathVariable UUID id){
        Route route = dispatchService.getRoute(id);
        return ResponseEntity.ok(RouteResponse.from(route));
    }

    @PostMapping("/{id}/assign")
    public ResponseEntity<Void> assignDriver(
        @PathVariable UUID id,
        @RequestBody AssignDriverRequest request) {
        dispatchService.assignDriver(id, request.getDriverId(), request.getVehicleId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/dispatch")
    public ResponseEntity<Void> dispatchRoute(@PathVariable UUID id){
        dispatchService.dispatchRoute(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> completeRoute(@PathVariable UUID id){
        dispatchService.completeRoute(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/stops")
    public ResponseEntity<Void> addStop(
            @PathVariable UUID id,
            @RequestBody AddStopRequest request) {
        dispatchService.addStop(id, request.getOrderId(), request.getStopSequence());
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/stops/{stopId}/complete")
    public ResponseEntity<Void> completeStop(@PathVariable UUID id, @PathVariable UUID stopId) {
        dispatchService.completeStop(id, stopId);
        return ResponseEntity.ok().build();
    }
    
}
