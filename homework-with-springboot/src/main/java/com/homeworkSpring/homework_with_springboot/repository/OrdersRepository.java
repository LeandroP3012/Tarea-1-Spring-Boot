package com.homeworkSpring.homework_with_springboot.repository;

import com.homeworkSpring.homework_with_springboot.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<OrdersEntity,Long> {
    @Query("SELECT o FROM OrdersEntity o WHERE o.estado = :datoEstado")
    List<OrdersEntity> findByEstado(@Param("datoEstado") int datoEstado);
}
