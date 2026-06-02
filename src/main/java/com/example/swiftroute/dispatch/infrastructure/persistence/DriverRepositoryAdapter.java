package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.swiftroute.dispatch.application.port.DriverRepository;
import com.example.swiftroute.dispatch.domain.model.Driver;

@Repository
public class DriverRepositoryAdapter implements DriverRepository {
    private final DriverMapper driverMapper;
    private final DriverConverter driverConverter;

    public DriverRepositoryAdapter(DriverMapper driverMapper, DriverConverter driverConverter) {
        this.driverMapper = driverMapper;
        this.driverConverter = driverConverter;
    }

    @Override
    public void save(Driver driver){
        DriverPersistence persistence = driverConverter.toPersistence(driver);
        if (driverMapper.existById(driver.getId())) {
            driverMapper.update(persistence);
        } else {
            driverMapper.insert(persistence);
        }
    }

    @Override
    public Optional<Driver> findById(java.util.UUID id) {
        return driverMapper.findById(id).map(driverConverter::toDomain);
    }

    @Override
    public List<Driver> findAll() {
        return driverMapper.findAll().stream().map(driverConverter::toDomain).collect(java.util.stream.Collectors.toList());
    }

    
}
