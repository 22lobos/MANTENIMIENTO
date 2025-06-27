package com.mantenimiento.mantenimientos.service;

import com.mantenimiento.mantenimientos.model.Piso;
import com.mantenimiento.mantenimientos.repository.PisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PisoService {

    private final PisoRepository repository;

    public PisoService(PisoRepository repository) {
        this.repository = repository;
    }

    public List<Piso> findAll() {
        return repository.findAll();
    }

    public Piso findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Piso save(Piso piso) {
        return repository.save(piso);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
