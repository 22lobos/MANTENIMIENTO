package com.mantenimiento.mantenimientos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "mantenimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaProgramada;

    @Column
    private LocalDate fechaRealizacion;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private Long equipoId;
}