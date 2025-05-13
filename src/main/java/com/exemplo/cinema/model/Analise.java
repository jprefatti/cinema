package com.exemplo.cinema.model;

import jakarta.persistence.*;

@Entity
public class Analise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String analise;
    private Integer nota;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;
     
    // Construtor vazio
    public Analise() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        if (this.filme != null) {
            this.filme.getAnalises().remove(this); // Remove do filme antigo
        }
        this.filme = filme;
        if (filme != null && !filme.getAnalises().contains(this)) {
            filme.getAnalises().add(this); // Adiciona ao novo filme
        }
    }
    
    public String getAnalise() {
        return analise;
    }

    public void setAnalise(String analise) {
        this.analise = analise;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}