package com.tiago.cotacoes.service;

import com.tiago.cotacoes.model.Cotacao;
import com.tiago.cotacoes.repository.CotacaoRepository;

import org.springframework.stereotype.Service;

// Classe para fazer requisições HTTP
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

// Indica que essa classe é responsável pela lógica de negócio
@Service
public class CotacaoService {

    // Injeção de dependência (o Spring fornece automaticamente)
    private final CotacaoRepository repository;

    public CotacaoService(CotacaoRepository repository) {
        this.repository = repository;
    }

    // Método principal: busca da API e salva no banco
    public void buscarESalvarCotacao() {

        // URL da API externa (HTTPS)
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum&vs_currencies=brl";

        // RestTemplate faz requisições HTTP (GET, POST, etc.)
        RestTemplate restTemplate = new RestTemplate();

        // Faz requisição GET e converte JSON para Map automaticamente
        Map response = restTemplate.getForObject(url, Map.class);

        /*
        Exemplo do JSON retornado:

        {
          "bitcoin": { "brl": 350000 },
          "ethereum": { "brl": 18000 }
        }
        */

        // Pegando os dados do JSON
        Map bitcoin = (Map) response.get("bitcoin");
        Map ethereum = (Map) response.get("ethereum");

        // Convertendo para Double
        Double btcValor = Double.valueOf(bitcoin.get("brl").toString());
        Double ethValor = Double.valueOf(ethereum.get("brl").toString());

        // Salvando no banco
        repository.save(
            new Cotacao(null, "bitcoin", btcValor, LocalDateTime.now())
        );

        repository.save(
            new Cotacao(null, "ethereum", ethValor, LocalDateTime.now())
        );
    }
}