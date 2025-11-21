package com.example.controller;

import com.example.entity.Entrenador;
import com.example.service.EntrenadorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @PostMapping
    public Entrenador crearEntrenador(@RequestBody Entrenador entrenador) {
        return entrenadorService.crearEntrenador(entrenador);
    }

    @GetMapping
    public List<Entrenador> listarEntrenadores() {
        return entrenadorService.listarEntrenadores();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEntrenador(@PathVariable Long id, @RequestBody Entrenador entrenadorActualizado) {
        try {
            Entrenador actualizado = entrenadorService.actualizarEntrenador(id, entrenadorActualizado);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEntrenador(@PathVariable Long id) {
        try {
            entrenadorService.eliminarEntrenador(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
