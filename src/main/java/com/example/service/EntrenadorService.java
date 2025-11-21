package com.example.service;

import com.example.entity.Entrenador;
import com.example.repository.EntrenadorRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public Entrenador crearEntrenador(Entrenador entrenador) {
        entrenador.setNivel(1);
        return entrenadorRepository.save(entrenador);
    }

    @Cacheable("entrenadores")
    public List<Entrenador> listarEntrenadores() {
        return entrenadorRepository.findAll();
    }
    
    public Entrenador actualizarEntrenador(Long id, Entrenador datos) {
        Entrenador existente = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

        existente.setNombre(datos.getNombre());
        existente.setNivel(datos.getNivel());

        return entrenadorRepository.save(existente);
    }
    
    @Transactional
    public void eliminarEntrenador(Long id) {
        Entrenador entrenador = entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
        entrenadorRepository.delete(entrenador);
    }

}

