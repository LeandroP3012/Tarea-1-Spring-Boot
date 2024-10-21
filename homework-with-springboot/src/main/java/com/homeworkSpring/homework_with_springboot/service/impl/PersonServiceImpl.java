package com.homeworkSpring.homework_with_springboot.service.impl;

import com.homeworkSpring.homework_with_springboot.entity.OrdersEntity;
import com.homeworkSpring.homework_with_springboot.entity.PersonEntity;
import com.homeworkSpring.homework_with_springboot.repository.OrdersRepository;
import com.homeworkSpring.homework_with_springboot.repository.PersonRepository;
import com.homeworkSpring.homework_with_springboot.service.OrdersService;
import com.homeworkSpring.homework_with_springboot.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final OrdersService pedidoService;
    private final OrdersRepository pedidoRepository;

    public PersonServiceImpl(PersonRepository personRepository, OrdersService pedidoService, OrdersRepository pedidoRepository) {
        this.personRepository = personRepository;
        this.pedidoService = pedidoService;
        this.pedidoRepository = pedidoRepository;
    }


    @Override
    public PersonEntity guardarPersona(PersonEntity person) {
        return personRepository.save(person);
    }

    @Override
    public PersonEntity obtenerPersonaPorId(Long id) {
        return personRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Persona no Encontrada"));
    }

    @Override
    public List<PersonEntity> obtenerTodasLasPersonas() {
        return personRepository.findAll();
    }

    @Override
    public List<PersonEntity> obtenerPersonasPorEstado(int estado) {
        return personRepository.findByEstado(estado);
    }


    @Override
    public PersonEntity actualizarPersona(Long id, PersonEntity personaActual) {
        PersonEntity personaExistente = obtenerPersonaPorId(id);
        personaExistente.setNombre(personaActual.getNombre());
        personaExistente.setApellido(personaActual.getApellido());
        personaExistente.setDNI(personaActual.getDNI());

        gestionarPedidos(personaExistente,personaActual.getPedidos());
        personaExistente.setPedidos(personaActual.getPedidos());
        return personRepository.save(personaExistente);
    }


    private void gestionarPedidos(PersonEntity personaExistente, List<OrdersEntity> pedidosActualizados) {
        List<OrdersEntity> pedidosExistentes = personaExistente.getPedidos();
        for (OrdersEntity pedido : pedidosActualizados) {
            if (pedido.getId() != null) {
                OrdersEntity pedidoEncontrado = pedidoRepository.findById(pedido.getId())
                        .orElseThrow(() -> new NoSuchElementException("Error pedido no ubicado"));
                pedidoEncontrado.setDescripcion(pedido.getDescripcion());
                pedidoEncontrado.setCantidad(pedido.getCantidad());
                pedidoEncontrado.setEstado(pedido.getEstado());
                pedidosExistentes.add(pedidoEncontrado);
            } else {
                pedido.setPerson(personaExistente);
                pedidosExistentes.add(pedido);
            }
        }
    }


    @Override
    public void eliminarPerson(Long id) {
        PersonEntity personaExistente = obtenerPersonaPorId(id);
        personRepository.delete(personaExistente);

    }
}
