package com.exemplo.cinema; // Pacote definido no pom.xml (groupId + artifactId)

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args); // Inicia a aplicação
    }
}