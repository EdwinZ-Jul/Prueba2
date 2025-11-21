package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int nivel;
    private int experiencia;
    private int puntosDeVida;

    @ManyToOne
    @JoinColumn(name = "entrenador_id")
    @JsonBackReference
    private Entrenador entrenador;

    @OneToMany(mappedBy = "pokemon1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Batalla> batallasComoPokemon1;

    @OneToMany(mappedBy = "pokemon2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Batalla> batallasComoPokemon2;

    // ==========================
    //   CONSTRUCTOR REQUERIDO POR JPA
    // ==========================
    public Pokemon() {
    }

    // ==========================
    //   CONSTRUCTOR PRIVADO PARA BUILDER
    // ==========================
    private Pokemon(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.nivel = builder.nivel;
        this.experiencia = builder.experiencia;
        this.puntosDeVida = builder.puntosDeVida;
        this.entrenador = builder.entrenador;
    }

    // ==========================
    //          BUILDER
    // ==========================
    public static class Builder {
        private Long id;
        private String nombre;
        private int nivel;
        private int experiencia;
        private int puntosDeVida;
        private Entrenador entrenador;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder nivel(int nivel) {
            this.nivel = nivel;
            return this;
        }

        public Builder experiencia(int experiencia) {
            this.experiencia = experiencia;
            return this;
        }

        public Builder puntosDeVida(int puntosDeVida) {
            this.puntosDeVida = puntosDeVida;
            return this;
        }

        public Builder entrenador(Entrenador entrenador) {
            this.entrenador = entrenador;
            return this;
        }

        public Pokemon build() {
            return new Pokemon(this);
        }
    }

    // ==========================
    //       GETTERS & SETTERS
    // ==========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getExperiencia() { return experiencia; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }

    public int getPuntosDeVida() { return puntosDeVida; }
    public void setPuntosDeVida(int puntosDeVida) { this.puntosDeVida = puntosDeVida; }

    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }
}
