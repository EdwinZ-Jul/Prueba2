package com.example.service;

import com.example.entity.Entrenador;
import com.example.repository.EntrenadorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntrenadorServiceTest {

    @Mock
    private EntrenadorRepository entrenadorRepository;

    @InjectMocks
    private EntrenadorService entrenadorService;

    private Entrenador entrenador;

    @BeforeEach
    void setUp() {
        entrenador = new Entrenador();
        entrenador.setId(1L);
        entrenador.setNombre("Ash");
        entrenador.setNivel(10);
    }

    // ======================================================
    // TEST: crearEntrenador()
    // ======================================================
    @Test
    void testCrearEntrenador() {

        Entrenador entrenadorNuevo = new Entrenador();
        entrenadorNuevo.setNombre("Misty");

        Entrenador guardado = new Entrenador();
        guardado.setId(2L);
        guardado.setNombre("Misty");
        guardado.setNivel(1); // el servicio siempre setNivel(1)

        when(entrenadorRepository.save(any(Entrenador.class))).thenReturn(guardado);

        Entrenador result = entrenadorService.crearEntrenador(entrenadorNuevo);

        assertEquals("Misty", result.getNombre());
        assertEquals(1, result.getNivel());

        verify(entrenadorRepository, times(1)).save(any(Entrenador.class));
    }

    // ======================================================
    // TEST: listarEntrenadores()
    // ======================================================
    @Test
    void testListarEntrenadores() {

        List<Entrenador> lista = Arrays.asList(new Entrenador(), new Entrenador());

        when(entrenadorRepository.findAll()).thenReturn(lista);

        List<Entrenador> result = entrenadorService.listarEntrenadores();

        assertEquals(2, result.size());
        verify(entrenadorRepository, times(1)).findAll();
    }

    // ======================================================
    // TEST: actualizarEntrenador()
    // ======================================================
    @Test
    void testActualizarEntrenador() {

        Entrenador actualizado = new Entrenador();
        actualizado.setNombre("Ash Ketchum");
        actualizado.setNivel(50);

        when(entrenadorRepository.findById(1L)).thenReturn(Optional.of(entrenador));
        when(entrenadorRepository.save(any(Entrenador.class))).thenReturn(actualizado);

        Entrenador result = entrenadorService.actualizarEntrenador(1L, actualizado);

        assertEquals("Ash Ketchum", result.getNombre());
        assertEquals(50, result.getNivel());

        verify(entrenadorRepository, times(1)).findById(1L);
        verify(entrenadorRepository, times(1)).save(any(Entrenador.class));
    }

    // ======================================================
    // TEST: actualizarEntrenador() -> ERROR
    // ======================================================
    @Test
    void testActualizarEntrenador_NoEncontrado() {

        when(entrenadorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
            entrenadorService.actualizarEntrenador(1L, entrenador)
        );

        verify(entrenadorRepository, times(1)).findById(1L);
        verify(entrenadorRepository, never()).save(any());
    }

    // ======================================================
    // TEST: eliminarEntrenador()
    // ======================================================
    @Test
    void testEliminarEntrenador() {

        when(entrenadorRepository.findById(1L)).thenReturn(Optional.of(entrenador));

        doNothing().when(entrenadorRepository).delete(entrenador);

        entrenadorService.eliminarEntrenador(1L);

        verify(entrenadorRepository, times(1)).findById(1L);
        verify(entrenadorRepository, times(1)).delete(entrenador);
    }

    // ======================================================
    // TEST: eliminarEntrenador() -> ERROR
    // ======================================================
    @Test
    void testEliminarEntrenador_NoEncontrado() {

        when(entrenadorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                entrenadorService.eliminarEntrenador(1L)
        );

        verify(entrenadorRepository, times(1)).findById(1L);
        verify(entrenadorRepository, never()).delete(any());
    }
}
