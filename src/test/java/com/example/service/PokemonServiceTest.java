package com.example.service;

import com.example.entity.Entrenador;
import com.example.entity.Pokemon;
import com.example.factory.PokemonFactory;
import com.example.repository.PokemonRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonService pokemonService;

    private Entrenador entrenador;

    @BeforeEach
    void setUp() {
        entrenador = new Entrenador();
        entrenador.setId(1L);
        entrenador.setNombre("Ash");
        entrenador.setNivel(1);
    }

    // ======================================================
    // TEST: crearPokemon()
    // ======================================================
    @Test
    void testCrearPokemon() {

        Pokemon pokemon = new Pokemon();
        pokemon.setEntrenador(entrenador);

        Pokemon pokemonGuardado = new Pokemon();
        pokemonGuardado.setNivel(1);
        pokemonGuardado.setExperiencia(0);
        pokemonGuardado.setPuntosDeVida(100);
        pokemonGuardado.setEntrenador(entrenador);

        when(pokemonRepository.save(any(Pokemon.class))).thenReturn(pokemonGuardado);

        Pokemon result = pokemonService.crearPokemon(pokemon);

        assertEquals(1, result.getNivel());
        assertEquals(0, result.getExperiencia());
        assertEquals(100, result.getPuntosDeVida());
        assertEquals(entrenador, result.getEntrenador());

        verify(pokemonRepository, times(1)).save(any(Pokemon.class));
    }

    // ======================================================
    // TEST: crearPokemonPorTipo() usando FACTORY
    // ======================================================
    @Test
    void testCrearPokemonPorTipo() {

        Pokemon pokemonMock = PokemonFactory.crearPokemonPorTipo("fuego", entrenador);

        when(pokemonRepository.save(any(Pokemon.class))).thenReturn(pokemonMock);

        Pokemon result = pokemonService.crearPokemonPorTipo("fuego", entrenador);

        assertEquals("Charmander", result.getNombre());
        assertEquals(1, result.getNivel());
        assertEquals(0, result.getExperiencia());
        assertEquals(entrenador, result.getEntrenador());

        verify(pokemonRepository, times(1)).save(any(Pokemon.class));
    }

    // ======================================================
    // TEST: actualizarExperiencia()
    // ======================================================
    @Test
    void testActualizarExperiencia() {

        Pokemon pokemon = new Pokemon();
        pokemon.setExperiencia(80);
        pokemon.setNivel(1);

        Pokemon pokemonActualizado = new Pokemon();
        pokemonActualizado.setExperiencia(130);  // 80 + 50
        pokemonActualizado.setNivel(2);          // 130/100 + 1 = nivel 2

        when(pokemonRepository.save(any(Pokemon.class))).thenReturn(pokemonActualizado);

        Pokemon result = pokemonService.actualizarExperiencia(pokemon, 50);

        assertEquals(130, result.getExperiencia());
        assertEquals(2, result.getNivel());

        verify(pokemonRepository, times(1)).save(any(Pokemon.class));
    }

    // ======================================================
    // TEST: listarPorEntrenador()
    // ======================================================
    @Test
    void testListarPorEntrenador() {

        List<Pokemon> lista = Arrays.asList(new Pokemon(), new Pokemon());

        when(pokemonRepository.findByEntrenadorId(1L)).thenReturn(lista);

        List<Pokemon> result = pokemonService.listarPorEntrenador(1L);

        assertEquals(2, result.size());
        verify(pokemonRepository, times(1)).findByEntrenadorId(1L);
    }

    // ======================================================
    // TEST: listarTodos()
    // ======================================================
    @Test
    void testListarTodos() {

        List<Pokemon> lista = Arrays.asList(new Pokemon(), new Pokemon());

        when(pokemonRepository.findAll()).thenReturn(lista);

        List<Pokemon> result = pokemonService.listarTodos();

        assertEquals(2, result.size());
        verify(pokemonRepository, times(1)).findAll();
    }
}

