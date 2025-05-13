package com.exemplo.cinema.controller;

import com.exemplo.cinema.model.Analise;
import com.exemplo.cinema.model.Filme;
import com.exemplo.cinema.repository.AnaliseRepository;
import com.exemplo.cinema.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeRepository filmeRepository;
    private final AnaliseRepository analiseRepository;

    // Injeção de dependência via construtor (melhor prática)
    @Autowired
    public FilmeController(FilmeRepository filmeRepository, AnaliseRepository analiseRepository) {
        this.filmeRepository = filmeRepository;
        this.analiseRepository = analiseRepository;
    }

    // Exibir formulário de cadastro
    @GetMapping("/cadastrar")
    public String mostrarForm(Model model) {
        model.addAttribute("filme", new Filme());
        return "cadastrar-filme";
    }

    // Processar cadastro (POST)
    @PostMapping
    public String cadastrarFilme(@ModelAttribute Filme filme) {
        filmeRepository.save(filme); // Usa o método save do JPA
        return "redirect:/filmes";
    }

    // Listar todos os filmes (GET)
    @GetMapping
    public String listarFilmes(Model model) {
        model.addAttribute("filmes", filmeRepository.findAll()); // findAll do JPA
        return "listar-filmes";
    }

    // Detalhes de um filme (GET)
    @GetMapping("/{id}")
    public String detalhesFilme(@PathVariable Long id, Model model) {
        Filme filme = filmeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "Filme não encontrado com ID: " + id
            ));

        model.addAttribute("filme", filme);
        model.addAttribute("analises", analiseRepository.findByFilmeId(id));
        model.addAttribute("analise", new Analise());
        return "detalhes-filme";
    }
}