package com.example.swiftroute.dispatch.application.port;

import java.util.List;
import java.util.UUID;

import com.example.swiftroute.dispatch.domain.model.Driver;

public interface DriverRepository {
    void save(Driver driver);
    Driver findById(UUID id);
    List<Driver> findAll();
}
