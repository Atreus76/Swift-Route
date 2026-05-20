package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.swiftroute.dispatch.application.port.DriverRepository;
import com.example.swiftroute.dispatch.domain.model.Driver;

@Repository
public class DriverRepositoryAdapter implements DriverRepository {
    private final DriverMapper driverMapper;

    public DriverRepositoryAdapter(DriverMapper driverMapper) {
        this.driverMapper = driverMapper;
    }

    @Override
    public void save(Driver driver){
        if (driverMapper.existById(driver.getId())) {
            driverMapper.update(driver);
        } else {
            driverMapper.insert(driver);
        }
    }

    @Override
    public Optional<Driver> findById(java.util.UUID id) {
        return driverMapper.findById(id);
    }

    @Override
    public List<Driver> findAll() {
        return driverMapper.findAll();
    }

    
}
