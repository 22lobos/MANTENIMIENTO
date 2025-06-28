package com.mantenimiento.mantenimientos.controller;

import com.mantenimiento.mantenimientos.model.Mantenimiento;
import com.mantenimiento.mantenimientos.service.MantenimientoService;
import com.mantenimiento.mantenimientos.assemblers.MantenimientoModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/mantenimientos")
@RequiredArgsConstructor
@Tag(name = "Mantenimientos", description = "Operaciones para gestionar mantenimientos de equipos y pisos")
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;
    private final MantenimientoModelAssembler assembler;

    @Operation(summary = "Obtener todos los mantenimientos")
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Mantenimiento>>> obtenerTodos() {
        List<Mantenimiento> lista = mantenimientoService.obtenerTodos();

        List<EntityModel<Mantenimiento>> mantenimientosModel = lista.stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Mantenimiento>> collectionModel =
            CollectionModel.of(mantenimientosModel,
                linkTo(methodOn(MantenimientoController.class).obtenerTodos()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @Operation(summary = "Obtener un mantenimiento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Mantenimiento>> obtenerPorId(@PathVariable Long id) {
        Mantenimiento mantenimiento = mantenimientoService.obtenerPorId(id);
        return ResponseEntity.ok(assembler.toModel(mantenimiento));
    }

    @Operation(summary = "Crear un nuevo mantenimiento")
    @PostMapping
    public ResponseEntity<EntityModel<Mantenimiento>> crear(@RequestBody Mantenimiento mantenimiento) {
        Mantenimiento nuevo = mantenimientoService.guardar(mantenimiento);
        EntityModel<Mantenimiento> model = assembler.toModel(nuevo);
        return ResponseEntity
            .created(model.getRequiredLink("self").toUri())
            .body(model);
    }

    @Operation(summary = "Actualizar un mantenimiento existente")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Mantenimiento>> actualizar(@PathVariable Long id, @RequestBody Mantenimiento mantenimiento) {
        Mantenimiento actualizado = mantenimientoService.actualizar(id, mantenimiento);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @Operation(summary = "Obtener mantenimientos por ID de equipo")
    @GetMapping("/por-equipo/{equipoId}")
    public ResponseEntity<CollectionModel<EntityModel<Mantenimiento>>> obtenerPorEquipo(@PathVariable Long equipoId) {
        List<Mantenimiento> lista = mantenimientoService.obtenerPorEquipo(equipoId);

        List<EntityModel<Mantenimiento>> mantenimientosModel = lista.stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        CollectionModel<EntityModel<Mantenimiento>> collectionModel =
            CollectionModel.of(mantenimientosModel,
                linkTo(methodOn(MantenimientoController.class).obtenerPorEquipo(equipoId)).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @Operation(summary = "Eliminar un mantenimiento por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mantenimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
