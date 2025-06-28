package com.mantenimiento.mantenimientos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mantenimiento.mantenimientos.model.Mantenimiento;
import com.mantenimiento.mantenimientos.service.MantenimientoService;
import com.mantenimiento.mantenimientos.assemblers.MantenimientoModelAssembler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

@WebMvcTest(MantenimientoController.class)
class MantenimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MantenimientoService mantenimientoService;

    @MockBean
    private MantenimientoModelAssembler assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deberiaRetornarListaDeMantenimientos() throws Exception {
        Mantenimiento mantenimiento = Mantenimiento.builder()
                .id(1L)
                .tipo("Preventivo")
                .descripcion("Revisión aire")
                .fechaProgramada(LocalDate.now())
                .estado("REALIZADO")
                .equipoId(100L)
                .build();

        given(mantenimientoService.obtenerTodos()).willReturn(List.of(mantenimiento));
        given(assembler.toModel(mantenimiento)).willReturn(EntityModel.of(mantenimiento));

        mockMvc.perform(get("/api/mantenimientos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").exists());
    }
}
