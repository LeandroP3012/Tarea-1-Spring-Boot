package com.homeworkSpring.homework_with_springboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "direccion")
@Setter
@Getter

public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "avenida",length = 250, nullable = false)
    private String avenida;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "distrito", length = 100, nullable = false)
    private String  distrito;

    @Column(name = "provincia", length = 100, nullable = false)
    private String  provincia;

    @Column(name = "departamento", length = 100, nullable = false)
    private String  departamento;
}
