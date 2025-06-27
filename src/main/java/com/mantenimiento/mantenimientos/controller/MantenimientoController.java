package com.mantenimiento.mantenimientos.controller;

import com.mantenimiento.mantenimientos.model.Mantenimiento;
import com.mantenimiento.mantenimientos.service.MantenimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")
@RequiredArgsConstructor
@Tag(name = "Mantenimientos", description = "Operaciones para gestionar mantenimientos de equipos y pisos")
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    @Operation(summary = "Obtener todos los mantenimientos")
    @GetMapping
    public ResponseEntity<List<Mantenimiento>> obtenerTodos() {
        return ResponseEntity.ok(mantenimientoService.obtenerTodos());
    }

    @Operation(summary = "Obtener un mantenimiento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Mantenimiento> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mantenimientoService.obtenerPorId(id));
    }

    @Operation(summary = "Crear un nuevo mantenimiento")
    @PostMapping
    public ResponseEntity<Mantenimiento> crear(@RequestBody Mantenimiento mantenimiento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mantenimientoService.guardar(mantenimiento));
    }

    @Operation(summary = "Actualizar un mantenimiento existente")
    @PutMapping("/{id}")
    public ResponseEntity<Mantenimiento> actualizar(@PathVariable Long id, @RequestBody Mantenimiento mantenimiento) {
        return ResponseEntity.ok(mantenimientoService.actualizar(id, mantenimiento));
    }

    @Operation(summary = "Obtener mantenimientos por ID de equipo")
    @GetMapping("/por-equipo/{equipoId}")
    public ResponseEntity<List<Mantenimiento>> obtenerPorEquipo(@PathVariable Long equipoId) {
        return ResponseEntity.ok(mantenimientoService.obtenerPorEquipo(equipoId));
    }

    @Operation(summary = "Eliminar un mantenimiento por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mantenimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
