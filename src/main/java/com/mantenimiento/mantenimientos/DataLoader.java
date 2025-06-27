package com.mantenimiento.mantenimientos;

import com.mantenimiento.mantenimientos.model.Mantenimiento;
import com.mantenimiento.mantenimientos.repository.MantenimientoRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Profile("dev") 
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Mantenimiento mantenimiento = Mantenimiento.builder()
                .tipo(faker.options().option("Preventivo", "Correctivo"))
                .descripcion(faker.lorem().sentence())
                .fechaProgramada(LocalDate.now().plusDays(random.nextInt(10)))
                .estado(faker.options().option("Programado", "En Proceso", "Finalizado"))
                .equipoId((long) faker.number().numberBetween(1, 100))
                .build();

            mantenimientoRepository.save(mantenimiento);
        }
    }
}
