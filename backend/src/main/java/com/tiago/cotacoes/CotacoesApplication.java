package com.tiago.cotacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Habilita o agendador (Scheduler)
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CotacoesApplication {

    public static void main(String[] args) {

        // Inicializa toda aplicação Spring
        SpringApplication.run(CotacoesApplication.class, args);
    }
}