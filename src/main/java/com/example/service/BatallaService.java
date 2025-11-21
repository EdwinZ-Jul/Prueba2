package com.example.service;

import com.example.entity.Batalla;
import com.example.entity.Pokemon;
import com.example.repository.BatallaRepository;
import com.example.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

//BatallaService.java (modificado)
@Service
public class BatallaService {

 private final BatallaRepository batallaRepository; // prueba
 private final PokemonRepository pokemonRepository;
 private final PokemonService pokemonService;
 private final Random random = new Random();

 public BatallaService(BatallaRepository batallaRepository, PokemonRepository pokemonRepository, PokemonService pokemonService) {
     this.batallaRepository = batallaRepository;
     this.pokemonRepository = pokemonRepository;
     this.pokemonService = pokemonService;
 }

 public Batalla iniciarBatalla(Long id1, Long id2) {
     Pokemon p1 = pokemonRepository.findById(id1).orElseThrow();
     Pokemon p2 = pokemonRepository.findById(id2).orElseThrow();

     boolean ganaP1 = decidirGanador(p1, p2); // ahora controlable en tests
     String resultado;

     if (ganaP1) {
         resultado = p1.getNombre() + " ganó";
         pokemonService.actualizarExperiencia(p1, 50);
         pokemonService.actualizarExperiencia(p2, 20);
     } else {
         resultado = p2.getNombre() + " ganó";
         pokemonService.actualizarExperiencia(p2, 50);
         pokemonService.actualizarExperiencia(p1, 20);
     }

     Batalla batalla = new Batalla();
     batalla.setPokemon1(p1);
     batalla.setPokemon2(p2);
     batalla.setResultado(resultado);
     batalla.setFecha(LocalDateTime.now());

     return batallaRepository.save(batalla);
 }

 // método protegido que se puede mockear en los tests
 protected boolean decidirGanador(Pokemon p1, Pokemon p2) {
     return random.nextBoolean();
 }
}


