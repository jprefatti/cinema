# cinema-sistema-web
Projeto Integrador - Atividade 1 - Sistema web para cadastro de filmes e análises de usuários.

# 🎬 Sistema de Gerenciamento de Filmes - Cinema

**Status do projeto:** Em desenvolvimento 🚧

## 📌 Descrição

Este projeto faz parte do Projeto Integrador do curso técnico, com o objetivo de aplicar conhecimentos de desenvolvimento web, versionamento de código e integração de front-end com back-end utilizando tecnologias abordadas no curso.

---

## 🎯 Objetivo

Desenvolver um sistema web que permita o gerenciamento de filmes, com funcionalidades de cadastro, listagem, edição e remoção, além de permitir que usuários insiram análises (comentários com nota) sobre cada filme. O sistema também oferece a opção de tema claro/escuro, com persistência via cookie.

---

## ✅ Funcionalidades

- [x] Cadastro de filmes com título, sinopse, gênero e ano de lançamento  
- [x] Listagem e visualização de filmes cadastrados  
- [x] Edição e exclusão de filmes  
- [x] Cadastro e exclusão de análises (com nota de 1 a 5)  
- [x] Interface com tema claro/escuro (modo escuro salvo com cookies)  
- [x] Integração com API REST utilizando jQuery e AJAX  
- [x] Empacotamento do sistema em arquivo `.war` para publicação  

---

## 🛠️ Tecnologias Utilizadas

- Java 20  
- Spring Boot 3.4.5  
- Spring Data JPA  
- Thymeleaf  
- jQuery  
- Bootstrap 4  
- MySQL  
- HTML5 / CSS3  
- Git & GitHub  

---

## 👥 Time de Desenvolvimento

- Fulano da Silva – Front-end (React, HTML/CSS)
- Beltrano Oliveira – Back-end (Node.js, Express)
- Cicrana Souza – Banco de Dados e Integração (SQLite/MySQL)
- João Paulo Refatti – Coordenação geral, testes e documentação

---

## 📁 Estrutura do Projeto

- `src/main/java` — código-fonte backend (controladores, modelos, repositórios)
- `src/main/resources/templates` — templates HTML com Thymeleaf
- `src/main/resources/static/js` — script `script.js` com lógica jQuery
- `pom.xml` — configuração Maven
- `README.md` — este arquivo

---

## 🚀 Execução e Deploy

1. Configure o banco de dados MySQL com o script `cinema_db.sql`  
2. Compile com:  
   ```bash
   mvn clean package
3. O arquivo .war será gerado em target/cinema.war
4. Publique o .war em um servidor compatível com Java EE, como Tomcat
