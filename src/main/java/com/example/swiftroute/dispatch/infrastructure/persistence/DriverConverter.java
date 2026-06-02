package com.example.swiftroute.dispatch.infrastructure.persistence;

import org.springframework.stereotype.Component;

import com.example.swiftroute.dispatch.domain.model.Driver;
import com.example.swiftroute.dispatch.domain.model.DriverStatus;
@Component
public class DriverConverter {
    public Driver toDomain(DriverPersistence persistence){
        if (persistence == null){
            return null;
        }

        return Driver.reconstitute(
            persistence.getId(),
            persistence.getName(),
            persistence.getLicenseNumber(),
            persistence.getPhoneNumber(),
            DriverStatus.valueOf(persistence.getStatus())
        );
    }

    public DriverPersistence toPersistence(Driver domain){
        if (domain == null){
            return null;
        }

        DriverPersistence persistence = new DriverPersistence();
        persistence.setId(domain.getId());
        persistence.setName(domain.getName());
        persistence.setLicenseNumber(domain.getLicenseNumber());
        persistence.setPhoneNumber(domain.getPhoneNumber());
        persistence.setStatus(domain.getStatus().name());

        return persistence;
    }
}
