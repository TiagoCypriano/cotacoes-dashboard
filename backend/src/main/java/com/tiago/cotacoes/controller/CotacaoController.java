package com.tiago.cotacoes.controller;

import com.tiago.cotacoes.model.Cotacao;
import com.tiago.cotacoes.repository.CotacaoRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")

// Indica que essa classe expõe endpoints REST
@RestController

// Define prefixo da URL
@RequestMapping("/cotacoes")
public class CotacaoController {

    private final CotacaoRepository repository;

    public CotacaoController(CotacaoRepository repository) {
        this.repository = repository;
    }

    // GET /cotacoes
    // Retorna todas as cotações
    @GetMapping
    public List<Cotacao> listar() {
        return repository.findAll();
    }

    // GET /cotacoes/bitcoin
    // Filtra por moeda
    @GetMapping("/{moeda}")
    public List<Cotacao> porMoeda(@PathVariable String moeda) {

        // @PathVariable pega valor da URL
        return repository.findByMoeda(moeda);
    }
}