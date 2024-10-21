package com.homeworkSpring.homework_with_springboot.controller;

import com.homeworkSpring.homework_with_springboot.entity.OrdersEntity;
import com.homeworkSpring.homework_with_springboot.service.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos/v1")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/persona/{personaId}")
    public ResponseEntity<OrdersEntity> crearPedido(@PathVariable Long personaId,
                                                    @RequestBody OrdersEntity pedido) {
        OrdersEntity nuevoPedido = ordersService.guardarPedido(personaId, pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersEntity> obtenerPedido(@PathVariable Long id) {
        OrdersEntity pedido = ordersService.obtenerPedidoPorId(id);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @GetMapping("/buscatodop")
    public ResponseEntity<List<OrdersEntity>> obtenerTodosLosPedidos() {
        List<OrdersEntity> pedidos = ordersService.obtenerTodosLosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdersEntity>> obtenerPedidosPorEstado(@PathVariable int estado) {
        List<OrdersEntity> pedidos = ordersService.obtenerPedidosPorEstado(estado);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PutMapping("/{id}/persona/{idPersona}")
    public ResponseEntity<OrdersEntity> actualizarPedido(@PathVariable Long id,
                                                         @PathVariable Long idPersona,
                                                         @RequestBody OrdersEntity pedido) {
        OrdersEntity pedidoActualizado = ordersService.actualizarPedido(id, idPersona, pedido);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        ordersService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
