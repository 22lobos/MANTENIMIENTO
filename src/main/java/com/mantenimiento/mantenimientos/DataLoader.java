package com.mantenimiento.mantenimientos;

import com.mantenimiento.mantenimientos.model.Mantenimiento;
import com.mantenimiento.mantenimientos.model.Piso;
import com.mantenimiento.mantenimientos.repository.MantenimientoRepository;
import com.mantenimiento.mantenimientos.repository.PisoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PisoRepository pisoRepository;

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Crear pisos
        for (int i = 1; i <= 5; i++) {
            Piso piso = Piso.builder()
                .numeroPiso(i)
                .descripcion("Piso " + i + " - " + faker.company().industry())
                .build();
            pisoRepository.save(piso);
        }

        List<Piso> pisos = pisoRepository.findAll();

        // Descripciones realistas
        String[] descripciones = {
            "Cambio de filtro de aire",
            "Inspección de extintores",
            "Revisión de luminarias LED",
            "Chequeo del sistema eléctrico",
            "Limpieza de canaletas",
            "Mantenimiento de bombas de agua",
            "Verificación de sistema de incendios",
            "Lubricación de motores de ascensor",
            "Control de plagas programado",
            "Chequeo de climatización"
        };

        // Crear mantenimientos
        for (int i = 0; i < 10; i++) {
            LocalDate fechaProgramada = LocalDate.now().plusDays(random.nextInt(10));
            LocalDate fechaRealizacion = fechaProgramada.plusDays(random.nextInt(5));

            Mantenimiento mantenimiento = Mantenimiento.builder()
                .tipo(faker.options().option("Preventivo", "Correctivo", "Inspección", "Emergencia"))
                .descripcion(descripciones[i % descripciones.length])
                .fechaProgramada(fechaProgramada)
                .fechaRealizacion(fechaRealizacion)
                .estado(faker.options().option("Programado", "En Proceso", "Finalizado"))
                .equipoId((long) faker.number().numberBetween(100, 999))
               
                .build();

            mantenimientoRepository.save(mantenimiento);
        }
    }
}
