package com.exemplo.cinema.repository;

import com.exemplo.cinema.model.Filme;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;



@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    
}