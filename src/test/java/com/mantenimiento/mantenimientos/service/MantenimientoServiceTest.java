package com.mantenimiento.mantenimientos.service;

import com.mantenimiento.mantenimientos.model.Mantenimiento;
import com.mantenimiento.mantenimientos.repository.MantenimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MantenimientoServiceTest {

    @Mock
    private MantenimientoRepository repository;

    @InjectMocks
    private MantenimientoService service;

    private Mantenimiento mantenimiento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mantenimiento = Mantenimiento.builder()
                .id(1L)
                .tipo("Correctivo")
                .descripcion("Cambio de batería")
                .fechaProgramada(LocalDate.of(2025, 7, 1))
                .estado("PENDIENTE")
                .equipoId(10L)
                .build();
    }

    @Test
    void guardarDebeRetornarMantenimiento() {
        when(repository.save(any(Mantenimiento.class))).thenReturn(mantenimiento);

        Mantenimiento guardado = service.guardar(mantenimiento);

        assertThat(guardado).isNotNull();
        assertThat(guardado.getDescripcion()).isEqualTo("Cambio de batería");
        verify(repository).save(mantenimiento);
    }

    @Test
    void obtenerPorIdDebeRetornarMantenimiento() {
        when(repository.findById(1L)).thenReturn(Optional.of(mantenimiento));

        Mantenimiento resultado = service.obtenerPorId(1L);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
    }

    @Test
    void obtenerTodosDebeRetornarLista() {
        when(repository.findAll()).thenReturn(List.of(mantenimiento));

        List<Mantenimiento> lista = service.obtenerTodos();

        assertThat(lista).hasSize(1);
        assertThat(lista.get(0).getEstado()).isEqualTo("PENDIENTE");
    }
}