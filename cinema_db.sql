-- Criar o banco de dados (opcional)
CREATE DATABASE IF NOT EXISTS cinema_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cinema_db;

-- Tabela de filmes
CREATE TABLE filme (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    sinopse TEXT,
    genero VARCHAR(100),
    ano_lancamento INT
);

-- Tabela de análises
CREATE TABLE analise (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    analise TEXT,
    nota INT,
    filme_id BIGINT,
    CONSTRAINT fk_analise_filme FOREIGN KEY (filme_id)
        REFERENCES filme(id)
        ON DELETE CASCADE
);

