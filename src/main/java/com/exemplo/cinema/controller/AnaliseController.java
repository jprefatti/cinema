package com.exemplo.cinema.controller;

import com.exemplo.cinema.model.Analise;
import com.exemplo.cinema.model.Filme;
import com.exemplo.cinema.repository.AnaliseRepository;
import com.exemplo.cinema.repository.FilmeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnaliseController {

    private final AnaliseRepository analiseRepository;
    private final FilmeRepository filmeRepository;

    @Autowired
    public AnaliseController(AnaliseRepository analiseRepository, FilmeRepository filmeRepository) {
        this.analiseRepository = analiseRepository;
        this.filmeRepository = filmeRepository;
    }

    @PostMapping("/analises/{filmeId}")
    @Transactional
    public String adicionarAnalise(
        @PathVariable Long filmeId,
        @RequestParam("analise") String texto,
    @RequestParam("nota")   Integer nota
        
    ) {
      
        // Busca o filme e valida sua existência
        Filme filme = filmeRepository.findById(filmeId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "Filme não encontrado"
            ));

        Analise a = new Analise();
    a.setAnalise(texto);
    a.setNota(nota);
    a.setFilme(filme);
    analiseRepository.save(a);
        
        // Redireciona para a página de detalhes do filme
        return "redirect:/filmes/" + filmeId;
    }
}