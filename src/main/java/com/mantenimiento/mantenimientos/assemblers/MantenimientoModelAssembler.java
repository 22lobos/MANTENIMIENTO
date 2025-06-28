package com.mantenimiento.mantenimientos.assemblers;

import com.mantenimiento.mantenimientos.controller.MantenimientoController;
import com.mantenimiento.mantenimientos.model.Mantenimiento;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class MantenimientoModelAssembler implements RepresentationModelAssembler<Mantenimiento, EntityModel<Mantenimiento>> {

    @Override
    public EntityModel<Mantenimiento> toModel(Mantenimiento mantenimiento) {
        return EntityModel.of(mantenimiento,
            linkTo(methodOn(MantenimientoController.class).obtenerPorId(mantenimiento.getId())).withSelfRel(),
            linkTo(methodOn(MantenimientoController.class).obtenerTodos()).withRel("mantenimientos"));
    }
}
