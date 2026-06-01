package com.example.swiftroute.order.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.swiftroute.order.domain.model.Order;

@Mapper
public interface OrderMapper {
    void insert(OrderPersistence order);
    int update(OrderPersistence order);
    Optional<OrderPersistence> findById(@Param("id") UUID id);
    boolean existById(@Param("id") UUID id);
}
