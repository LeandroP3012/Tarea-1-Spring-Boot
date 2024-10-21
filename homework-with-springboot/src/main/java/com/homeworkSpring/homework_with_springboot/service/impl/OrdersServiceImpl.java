package com.homeworkSpring.homework_with_springboot.service.impl;

import com.homeworkSpring.homework_with_springboot.entity.OrdersEntity;
import com.homeworkSpring.homework_with_springboot.entity.PersonEntity;
import com.homeworkSpring.homework_with_springboot.repository.OrdersRepository;
import com.homeworkSpring.homework_with_springboot.repository.PersonRepository;
import com.homeworkSpring.homework_with_springboot.service.OrdersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrdersServiceImpl implements OrdersService{

    private final OrdersRepository ordersRepository;
    private final PersonRepository personRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository, PersonRepository personRepository) {
        this.ordersRepository = ordersRepository;
        this.personRepository = personRepository;
    }

    @Override
    public OrdersEntity guardarPedido(Long personId, OrdersEntity pedido) {
        PersonEntity personaExistente = personRepository.findById(personId).orElseThrow(() -> new NoSuchElementException("Error Persona No Existe"));
        pedido.setPerson(personaExistente);
        return ordersRepository.save(pedido);
    }

    @Override
    public OrdersEntity obtenerPedidoPorId(Long id) {
        return ordersRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Pedido no Encontrado"));
    }

    @Override
    public List<OrdersEntity> obtenerTodosLosPedidos() {
        return ordersRepository.findAll();
    }

    @Override
    public List<OrdersEntity> obtenerPedidosPorEstado(int estado) {
        return ordersRepository.findByEstado(estado);
    }

    @Override
    public OrdersEntity actualizarPedido(Long id, Long personId, OrdersEntity pedido) {
        OrdersEntity pedidoExistente = obtenerPedidoPorId(id);
        pedidoExistente.setDescripcion(pedido.getDescripcion());
        pedidoExistente.setCantidad(pedido.getCantidad());
        pedidoExistente.setEstado(pedido.getEstado());
        return ordersRepository.save(pedidoExistente);
    }

    @Override
    public void eliminarPedido(Long id) {
        OrdersEntity pedidoExistente = obtenerPedidoPorId(id);
        ordersRepository.delete(pedidoExistente);
    }
}
