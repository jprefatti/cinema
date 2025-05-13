package com.exemplo.cinema.controller;

import com.exemplo.cinema.model.Filme;
import com.exemplo.cinema.repository.FilmeRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeRestController {

    private final FilmeRepository filmeRepository;

    
    @Autowired
    public FilmeRestController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    // GET: Listar todos os filmes
    @GetMapping
    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }
    
    @GetMapping("/{id}")
public Filme buscarFilme(@PathVariable Long id) {
    return filmeRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Filme não encontrado com o ID: " + id));
}


    // POST: Cadastrar filme
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Filme cadastrarFilme(@RequestBody Filme filme) {
        return filmeRepository.save(filme);
    }

    // PUT: Atualizar filme (com tratamento de erro)
    @PutMapping("/{id}")
    public Filme atualizarFilme(@PathVariable Long id, @RequestBody Filme filmeAtualizado) {
        return filmeRepository.findById(id)
            .map(filme -> {
                filme.setTitulo(filmeAtualizado.getTitulo());
                filme.setSinopse(filmeAtualizado.getSinopse());
                filme.setGenero(filmeAtualizado.getGenero());
                filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());
                return filmeRepository.save(filme);
            })
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "Filme não encontrado com o ID: " + id
            ));
    }

    // DELETE: Excluir filme (com verificação de existência)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirFilme(@PathVariable Long id) {
        if (!filmeRepository.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "Filme não encontrado com o ID: " + id
            );
        }
        filmeRepository.deleteById(id);
    }
}