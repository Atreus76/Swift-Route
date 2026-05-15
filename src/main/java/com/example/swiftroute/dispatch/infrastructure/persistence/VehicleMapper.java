package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.swiftroute.dispatch.domain.model.Vehicle;

@Mapper
public interface VehicleMapper {
    void insert(Vehicle vehicle);
    void update (Vehicle vehicle);
    Optional<Vehicle> findById(@Param("id") UUID id);
    List<Vehicle> findAll();
    boolean existById(@Param("id") UUID id);
}
