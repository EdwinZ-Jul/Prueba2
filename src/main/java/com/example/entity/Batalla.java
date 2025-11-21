package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Batalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pokemon1_id")
    private Pokemon pokemon1;

    @ManyToOne
    @JoinColumn(name = "pokemon2_id")
    private Pokemon pokemon2;

    private String resultado;

    private LocalDateTime fecha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pokemon getPokemon1() {
		return pokemon1;
	}

	public void setPokemon1(Pokemon pokemon1) {
		this.pokemon1 = pokemon1;
	}

	public Pokemon getPokemon2() {
		return pokemon2;
	}

	public void setPokemon2(Pokemon pokemon2) {
		this.pokemon2 = pokemon2;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

   
    
}

