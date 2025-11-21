package com.example.service;

import com.example.entity.Batalla;
import com.example.entity.Pokemon;
import com.example.repository.BatallaRepository;
import com.example.repository.PokemonRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BatallaServiceTest {

    @Mock
    private BatallaRepository batallaRepository;

    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private BatallaService batallaService;

    private Pokemon p1;
    private Pokemon p2;

    @BeforeEach
    void setUp() {
        p1 = new Pokemon();
        p1.setId(1L);
        p1.setNombre("Pikachu");
        p1.setExperiencia(0);
        p1.setNivel(1);

        p2 = new Pokemon();
        p2.setId(2L);
        p2.setNombre("Charmander");
        p2.setExperiencia(0);
        p2.setNivel(1);
    }

    @Test
    void testIniciarBatalla_GanaPokemon1() {
        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(p1));
        when(pokemonRepository.findById(2L)).thenReturn(Optional.of(p2));
        when(batallaRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        BatallaService spyService = spy(batallaService);
        doReturn(true).when(spyService).decidirGanador(p1, p2); // fuerza la victoria de P1

        Batalla batalla = spyService.iniciarBatalla(1L, 2L);

        assertEquals("Pikachu ganó", batalla.getResultado());
        verify(pokemonService).actualizarExperiencia(p1, 50);
        verify(pokemonService).actualizarExperiencia(p2, 20);
        verify(batallaRepository).save(any());
    }

    @Test
    void testIniciarBatalla_GanaPokemon2() {
        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(p1));
        when(pokemonRepository.findById(2L)).thenReturn(Optional.of(p2));
        when(batallaRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        BatallaService spyService = spy(batallaService);
        doReturn(false).when(spyService).decidirGanador(p1, p2); // fuerza la victoria de P2

        Batalla batalla = spyService.iniciarBatalla(1L, 2L);

        assertEquals("Charmander ganó", batalla.getResultado());
        verify(pokemonService).actualizarExperiencia(p2, 50);
        verify(pokemonService).actualizarExperiencia(p1, 20);
        verify(batallaRepository).save(any());
    }
}
