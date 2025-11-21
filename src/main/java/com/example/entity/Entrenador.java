package com.example.entity;

import jakarta.persistence.*;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int nivel;

    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pokemon> pokemones;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public List<Pokemon> getPokemones() {
		return pokemones;
	}

	public void setPokemones(List<Pokemon> pokemones) {
		this.pokemones = pokemones;
	}

    
    
}

