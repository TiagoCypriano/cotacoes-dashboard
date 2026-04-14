# 📊 Crypto Dashboard - Monitoramento de Cotações em Tempo Real

## 🚀 Sobre o Projeto

Este projeto é uma aplicação fullstack desenvolvida para monitorar e visualizar cotações de criptomoedas em tempo real.

A aplicação coleta dados de uma API externa, armazena localmente em um banco de dados e exibe essas informações em um dashboard interativo com gráficos e indicadores visuais.

---

## 🧠 Arquitetura do Sistema

A aplicação segue uma arquitetura moderna dividida em duas partes:

### 🔹 Backend (Java + Spring Boot)

* Responsável por consumir dados de uma API externa (CoinGecko)
* Armazena os dados no banco de dados MySQL
* Expõe uma API REST para o frontend

### 🔹 Frontend (React)

* Consome a API do backend
* Exibe gráficos em tempo real
* Mostra indicadores como preço atual e variação percentual

---

## 🔄 Fluxo de Dados

```
API Externa → Backend (Spring Boot) → Banco MySQL → API REST → Frontend (React)
```

---

## 🛠️ Tecnologias Utilizadas

### Backend

* Java
* Spring Boot
* Spring Data JPA
* MySQL

### Frontend

* React
* Chart.js
* JavaScript (ES6+)

---

## 📊 Funcionalidades

* 📈 Gráfico de cotações em tempo real
* 💰 Exibição do preço atual (Bitcoin e Ethereum)
* 📉 Cálculo de variação percentual
* 🟢🔴 Indicadores visuais de alta e baixa
* 🔄 Atualização automática a cada 1 minuto
* 🌙 Interface moderna (dark mode)

---

## ⚙️ Como Executar o Projeto

### 🔹 Backend

```bash
cd backend
./mvnw spring-boot:run
```

O backend estará disponível em:

```
http://localhost:8080
```

---

### 🔹 Frontend

```bash
cd frontend
npm install
npm start
```

A aplicação estará disponível em:

```
http://localhost:3000
```

---

## 🗄️ Banco de Dados

Certifique-se de que o MySQL está rodando e que o banco foi criado:

```sql
CREATE DATABASE cotacoes;
```

Configuração no backend (`application.properties`):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cotacoes
spring.datasource.username=root
spring.datasource.password=SUASENHA
```

---

## 📌 Endpoints da API

### 🔹 Listar cotações

```
GET /cotacoes
```

Retorna todas as cotações armazenadas no banco.

---

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com foco em:

* Prática de desenvolvimento fullstack
* Integração entre frontend e backend
* Consumo de APIs externas
* Persistência de dados
* Criação de dashboards interativos

---

## 🚀 Possíveis Melhorias

* Adicionar mais criptomoedas
* Implementar autenticação de usuários
* Criar filtros por período (dia, semana, mês)
* Deploy em nuvem (AWS, Vercel, Render)
* Notificações de variação de preço

---

