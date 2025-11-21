package com.example.factory;

import com.example.entity.Pokemon;
import com.example.entity.Entrenador;

public class PokemonFactory {

    public static Pokemon crearPokemonPorTipo(String tipo, Entrenador entrenador) {
        switch (tipo.toLowerCase()) {

            case "fuego":
                return new Pokemon.Builder()
                        .nombre("Charmander")
                        .nivel(1)
                        .experiencia(0)
                        .puntosDeVida(100)
                        .entrenador(entrenador)
                        .build();

            case "agua":
                return new Pokemon.Builder()
                        .nombre("Squirtle")
                        .nivel(1)
                        .experiencia(0)
                        .puntosDeVida(100)
                        .entrenador(entrenador)
                        .build();

            case "planta":
                return new Pokemon.Builder()
                        .nombre("Bulbasaur")
                        .nivel(1)
                        .experiencia(0)
                        .puntosDeVida(100)
                        .entrenador(entrenador)
                        .build();

            default:
                throw new IllegalArgumentException("Tipo de Pokémon no soportado: " + tipo);
        }
    }
}
