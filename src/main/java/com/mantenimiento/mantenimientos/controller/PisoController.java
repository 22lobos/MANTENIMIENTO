package com.mantenimiento.mantenimientos.controller;

import com.mantenimiento.mantenimientos.model.Piso;
import com.mantenimiento.mantenimientos.service.PisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pisos")
public class PisoController {

    @Autowired
    private PisoService pisoService;

    @GetMapping
    public List<Piso> getAllPisos() {
        return pisoService.findAll();
    }

    @GetMapping("/{id}")
    public Piso getPisoById(@PathVariable Long id) {
        return pisoService.findById(id);
    }

    @PostMapping
    public Piso createPiso(@RequestBody Piso piso) {
        return pisoService.save(piso);
    }

    @PutMapping("/{id}")
    public Piso updatePiso(@PathVariable Long id, @RequestBody Piso piso) {
        piso.setId(id);
        return pisoService.save(piso);
    }

    @DeleteMapping("/{id}")
    public void deletePiso(@PathVariable Long id) {
        pisoService.deleteById(id);
    }
}
