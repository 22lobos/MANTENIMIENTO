package com.mantenimiento.mantenimientos.repository;

import com.mantenimiento.mantenimientos.model.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
    List<Mantenimiento> findByEquipoId(Long equipoId);
    List<Mantenimiento> findByEstado(String estado);
}