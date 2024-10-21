package com.homeworkSpring.homework_with_springboot.service;

import com.homeworkSpring.homework_with_springboot.entity.PersonEntity;

import java.util.List;

public interface PersonService {
    PersonEntity guardarPersona(PersonEntity person);
    PersonEntity obtenerPersonaPorId(Long id);
    List<PersonEntity> obtenerTodasLasPersonas();
    List<PersonEntity> obtenerPersonasPorEstado(int estado);
    PersonEntity actualizarPersona(Long id, PersonEntity person);
    void eliminarPerson(Long id);
}
