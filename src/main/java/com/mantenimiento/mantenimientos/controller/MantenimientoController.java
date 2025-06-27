package com.mantenimiento.mantenimientos.controller;

import com.mantenimiento.mantenimientos.model.Mantenimiento;
import com.mantenimiento.mantenimientos.service.MantenimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    @GetMapping
    public ResponseEntity<List<Mantenimiento>> obtenerTodos() {
        return ResponseEntity.ok(mantenimientoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mantenimiento> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mantenimientoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Mantenimiento> crear(@RequestBody Mantenimiento mantenimiento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mantenimientoService.guardar(mantenimiento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mantenimiento> actualizar(@PathVariable Long id, @RequestBody Mantenimiento mantenimiento) {
        return ResponseEntity.ok(mantenimientoService.actualizar(id, mantenimiento));
    }

    @GetMapping("/por-equipo/{equipoId}")
    public ResponseEntity<List<Mantenimiento>> obtenerPorEquipo(@PathVariable Long equipoId) {
        return ResponseEntity.ok(mantenimientoService.obtenerPorEquipo(equipoId));
    }
    @DeleteMapping("/{id}")
public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    mantenimientoService.eliminar(id);
    return ResponseEntity.noContent().build(); 
}
}