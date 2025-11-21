package com.example.service;

import com.example.entity.Pokemon;
import com.example.entity.Entrenador;
import com.example.factory.PokemonFactory;
import com.example.repository.PokemonRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @CacheEvict(value = "pokemones", key = "#pokemon.entrenador.id")
    public Pokemon crearPokemon(Pokemon pokemon) {
        pokemon.setNivel(1);
        pokemon.setExperiencia(0);
        pokemon.setPuntosDeVida(100);
        return pokemonRepository.save(pokemon);
    }

    // =============================
    // NUEVO MÉTODO USANDO FACTORY
    // =============================
    public Pokemon crearPokemonPorTipo(String tipo, Entrenador entrenador) {
        Pokemon pokemon = PokemonFactory.crearPokemonPorTipo(tipo, entrenador);
        return pokemonRepository.save(pokemon);
    }

    @Cacheable(value = "pokemones", key = "#entrenadorId")
    public List<Pokemon> listarPorEntrenador(Long entrenadorId) {
        return pokemonRepository.findByEntrenadorId(entrenadorId);
    }

    public Pokemon actualizarExperiencia(Pokemon pokemon, int experienciaGanada) {
        int nuevaExperiencia = pokemon.getExperiencia() + experienciaGanada;
        pokemon.setExperiencia(nuevaExperiencia);

        int nuevoNivel = nuevaExperiencia / 100 + 1;
        pokemon.setNivel(nuevoNivel);

        return pokemonRepository.save(pokemon);
    }

    public List<Pokemon> listarTodos() {
        return pokemonRepository.findAll();
    }
}

