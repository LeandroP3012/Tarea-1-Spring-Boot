package com.homeworkSpring.homework_with_springboot.repository;

import com.homeworkSpring.homework_with_springboot.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity,Long>{
    @Query("SELECT p FROM PersonEntity p WHERE p.estado = :datoEstado")
    List<PersonEntity> findByEstado(@Param("datoEstado") int datoEstado);
}
