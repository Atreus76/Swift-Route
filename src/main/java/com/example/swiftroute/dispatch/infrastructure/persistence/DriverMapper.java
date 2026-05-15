package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.swiftroute.dispatch.domain.model.Driver;

@Mapper
public interface DriverMapper {
    void insert(Driver driver);
    void update(Driver driver);
    Optional<Driver> findById(@Param("id") UUID id);
    List<Driver> findAll();
    boolean existById(@Param("id") UUID id);
}
