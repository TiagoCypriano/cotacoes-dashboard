package com.tiago.cotacoes.repository;

import com.tiago.cotacoes.model.Cotacao;

// JpaRepository já traz tudo pronto:
// salvar, deletar, buscar, etc.
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// <Cotacao, Long>
// Cotacao = entidade
// Long = tipo do ID
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {

    // Método automático (Spring cria sozinho)
    // Vai gerar SQL tipo:
    // SELECT * FROM cotacao WHERE moeda = ?
    List<Cotacao> findByMoeda(String moeda);
}