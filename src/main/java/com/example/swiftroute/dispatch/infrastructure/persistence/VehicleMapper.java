package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface VehicleMapper {
    void insert(VehiclePersistence vehicle);
    void update (VehiclePersistence vehicle);
    Optional<VehiclePersistence> findById(@Param("id") UUID id);
    List<VehiclePersistence> findAll();
    boolean existById(@Param("id") UUID id);
}
