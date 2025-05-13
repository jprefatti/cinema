package com.exemplo.cinema.controller;

import com.exemplo.cinema.model.Analise;
import com.exemplo.cinema.model.Filme;
import com.exemplo.cinema.repository.AnaliseRepository;
import com.exemplo.cinema.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/filmes/{filmeId}/analises")
public class AnaliseRestController {

    @Autowired
    private AnaliseRepository analiseRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping
    public List<Analise> listarAnalises(@PathVariable Long filmeId) {
        return analiseRepository.findByFilmeId(filmeId);
    }

    @PostMapping
    public Analise adicionarAnalise(
        @PathVariable Long filmeId, 
        @RequestBody Analise analise
    ) {
        Filme filme = filmeRepository.findById(filmeId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "Filme não encontrado"
            ));
        
        analise.setFilme(filme);
        return analiseRepository.save(analise);
    }

    @DeleteMapping("/{id}")
    public void excluirAnalise(@PathVariable Long id) {
        analiseRepository.deleteById(id);
    }
}