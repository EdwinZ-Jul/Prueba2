package com.example.controller;

import com.example.entity.Pokemon;
import com.example.service.PokemonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemones")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping
    public Pokemon crearPokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.crearPokemon(pokemon);
    }

    @GetMapping("/entrenador/{id}")
    public List<Pokemon> listarPorEntrenador(@PathVariable Long id) {
        return pokemonService.listarPorEntrenador(id);
    }
    
    @GetMapping
    public List<Pokemon> listarTodos(){
    	return pokemonService.listarTodos();
    }

}

