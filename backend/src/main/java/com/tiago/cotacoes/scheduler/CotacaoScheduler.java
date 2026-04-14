package com.tiago.cotacoes.scheduler;

import com.tiago.cotacoes.service.CotacaoService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// Indica que essa classe é um componente gerenciado pelo Spring
@Component
public class CotacaoScheduler {

    private final CotacaoService service;

    public CotacaoScheduler(CotacaoService service) {
        this.service = service;
    }

    // Executa automaticamente a cada 60 segundos (60000 ms)
    @Scheduled(fixedRate = 60000)
    public void executar() {

        // Chama o service para buscar e salvar dados
        service.buscarESalvarCotacao();

        // Log no console (debug)
        System.out.println("Cotação salva automaticamente!");
    }
}