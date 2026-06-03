package com.example.swiftroute.order.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface OrderMapper {
    void insert(OrderPersistence order);
    int update(OrderPersistence order);
    Optional<OrderPersistence> findById(@Param("id") UUID id);
    void insertLine(OrderLinePersistence line);
    int deleteLinesByOrderId(@Param("orderId") UUID orderId);
    boolean existById(@Param("id") UUID id);
}
