package com.example.swiftroute.order.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.swiftroute.order.domain.model.Order;

@Mapper
public interface OrderMapper {
    void insert(Order order);
    int update(Order order);
    Optional<Order> findById(@Param("id") UUID id);
}
