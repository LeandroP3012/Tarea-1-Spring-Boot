package com.homeworkSpring.homework_with_springboot.service;

import com.homeworkSpring.homework_with_springboot.entity.OrdersEntity;

import java.util.List;

public interface OrdersService {
    OrdersEntity guardarPedido(Long personId, OrdersEntity pedido);
    OrdersEntity obtenerPedidoPorId(Long id);
    List<OrdersEntity> obtenerTodosLosPedidos();
    List<OrdersEntity> obtenerPedidosPorEstado(int estado);
    OrdersEntity actualizarPedido(Long id, Long personId, OrdersEntity pedido);
    void eliminarPedido(Long id);
}
