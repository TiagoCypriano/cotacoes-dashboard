package com.tiago.cotacoes.model;

// Importa anotações do JPA (Java Persistence API)
// JPA é responsável por mapear objetos Java para tabelas no banco
import jakarta.persistence.*;

// Lombok gera automaticamente getters, setters, construtores, etc.
// Evita código repetitivo
import lombok.*;

import java.time.LocalDateTime;

// Indica que essa classe representa uma tabela no banco
@Entity

// Lombok: gera automaticamente getters, setters, toString, equals, hashCode
@Data

// Construtor vazio (necessário para o JPA funcionar)
@NoArgsConstructor

// Construtor com todos os atributos
@AllArgsConstructor
public class Cotacao {

    // Define que esse campo é a chave primária (ID da tabela)
    @Id

    // Define que o ID será gerado automaticamente (auto incremento)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome da moeda (bitcoin, ethereum)
    private String moeda;

    // Valor da moeda (ex: 350000.50)
    private Double valor;

    // Data e hora da coleta
    private LocalDateTime dataHora;
}