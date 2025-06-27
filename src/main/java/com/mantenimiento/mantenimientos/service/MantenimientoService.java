package com.mantenimiento.mantenimientos.service;

import com.mantenimiento.mantenimientos.model.Mantenimiento;
import com.mantenimiento.mantenimientos.repository.MantenimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;

    public List<Mantenimiento> obtenerTodos() {
        return mantenimientoRepository.findAll();
    }

    public Mantenimiento obtenerPorId(Long id) {
        return mantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));
    }

    public Mantenimiento guardar(Mantenimiento mantenimiento) {
        return mantenimientoRepository.save(mantenimiento);
    }

    public Mantenimiento actualizar(Long id, Mantenimiento mantenimiento) {
        Mantenimiento existente = obtenerPorId(id);
        existente.setTipo(mantenimiento.getTipo());
        existente.setDescripcion(mantenimiento.getDescripcion());
        existente.setFechaProgramada(mantenimiento.getFechaProgramada());
        existente.setEstado(mantenimiento.getEstado());
        return mantenimientoRepository.save(existente);
    }

    public List<Mantenimiento> obtenerPorEquipo(Long equipoId) {
        return mantenimientoRepository.findByEquipoId(equipoId);
    }
    public void eliminar(Long id) {
        if (!mantenimientoRepository.existsById(id)) {
            throw new RuntimeException("Mantenimiento no encontrado con ID: " + id);
        }
        mantenimientoRepository.deleteById(id);
    }
}