package com.example.repository;

import com.example.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByEntrenadorId(Long entrenadorId);
}
