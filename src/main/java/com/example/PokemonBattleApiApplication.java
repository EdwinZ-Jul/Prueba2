package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PokemonBattleApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PokemonBattleApiApplication.class, args);
    }
}

