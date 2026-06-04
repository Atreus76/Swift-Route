package com.example.swiftroute.order.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.swiftroute.order.domain.model.OrderStatus;
import com.example.swiftroute.shared.domain.PageRequest;


@Mapper
public interface OrderMapper {
    void insert(OrderPersistence order);
    int update(OrderPersistence order);
    Optional<OrderPersistence> findById(@Param("id") UUID id);
    List<OrderPersistence> findByStatus(@Param("status") OrderStatus status, @Param("pageRequest") PageRequest pageRequest);
    long countByStatus(@Param("status") OrderStatus status);
    void insertLine(OrderLinePersistence line);
    int deleteLinesByOrderId(@Param("orderId") UUID orderId);
    boolean existById(@Param("id") UUID id);
}
