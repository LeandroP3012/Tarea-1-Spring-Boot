package com.homeworkSpring.homework_with_springboot.controller;

import com.homeworkSpring.homework_with_springboot.entity.PersonEntity;
import com.homeworkSpring.homework_with_springboot.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas/v1")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/crear")
    public ResponseEntity<PersonEntity> crearPersona(@Validated @RequestBody PersonEntity person){
        PersonEntity nuevaPersona = personService.guardarPersona(person);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    @GetMapping("/buscarpersona/{id}")
    public ResponseEntity<PersonEntity> obtenerPersona(@PathVariable Long id) {
        PersonEntity cliente = personService.obtenerPersonaPorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/buscatodo")
    public List<PersonEntity> obtenerTodasLasPersonas(){
        return personService.obtenerTodasLasPersonas();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PersonEntity>> obtenerPersonasPorEstado(@PathVariable int estado) {
        List<PersonEntity> personas = personService.obtenerPersonasPorEstado(estado);
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PersonEntity> actualizarPersona(@PathVariable Long id, @RequestBody PersonEntity person) {
        PersonEntity clienteActualizado = personService.actualizarPersona(id, person);
        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        personService.eliminarPerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


