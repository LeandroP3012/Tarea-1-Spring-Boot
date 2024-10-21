package com.homeworkSpring.homework_with_springboot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "persona")
@Getter
@Setter
public class PersonEntity {
    public static final int activo = 1;
    public static final int inactivo = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 100, nullable = false)
    private String apellido;

    @Column(name = "DNI", nullable = false)
    private Integer DNI;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private AddressEntity addressEntity;

    @JsonManagedReference
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<OrdersEntity> pedidos;
}
