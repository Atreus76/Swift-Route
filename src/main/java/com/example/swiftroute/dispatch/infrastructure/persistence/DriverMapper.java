package com.example.swiftroute.dispatch.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DriverMapper {
    void insert(DriverPersistence driver);
    void update(DriverPersistence driver);
    Optional<DriverPersistence> findById(@Param("id") UUID id);
    List<DriverPersistence> findAll();
    boolean existById(@Param("id") UUID id);
}
