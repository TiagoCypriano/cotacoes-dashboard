# 🚀 Crypto Dashboard - Monitoramento de Cotações em Tempo Real

## 📌 Sobre o Projeto

Este projeto é uma aplicação **fullstack** desenvolvida para monitorar e visualizar cotações de criptomoedas em tempo real.

A aplicação coleta dados de uma API externa, armazena em um banco de dados **MySQL** e exibe essas informações em um **dashboard interativo** com gráficos e indicadores visuais.

---

## 🌐 Demo

* **Frontend:** https://terrific-flexibility-production.up.railway.app
* **Backend API:** https://cotacoes-dashboard-production.up.railway.app/cotacoes

---

## 🏗️ Arquitetura do Sistema

A aplicação segue uma arquitetura moderna dividida em duas partes:

### 🔙 Backend (Java + Spring Boot)

* Consome dados da API externa (CoinGecko)
* Armazena os dados no banco de dados MySQL
* Expõe uma API REST para o frontend
* Configurado com CORS para permitir requisições do frontend

### 🎨 Frontend (React)

* Consome a API do backend
* Exibe gráficos em tempo real
* Mostra indicadores como preço atual e variação percentual

---

### 🔄 Fluxo de Dados

```
API Externa → Backend (Spring Boot) → MySQL → API REST → Frontend (React)
```

---

## 🛠️ Tecnologias Utilizadas

### Backend

* Java 17
* Spring Boot 4.0.5
* Spring Data JPA
* MySQL
* Docker

### Frontend

* React
* Chart.js
* JavaScript (ES6+)
* Docker

### Infraestrutura

* Railway (Deploy)
* Docker (Containerização)

---

## ⚙️ Funcionalidades

* 📊 Gráfico de cotações em tempo real
* 💰 Exibição do preço atual (Bitcoin e Ethereum)
* 📈 Cálculo de variação percentual
* 🔴🟢 Indicadores visuais de alta e baixa
* 🔄 Atualização automática a cada 1 minuto

---

## 🚀 Deploy em Produção (Railway)

| Serviço  | URL                                                    |
| -------- | ------------------------------------------------------ |
| Frontend | https://terrific-flexibility-production.up.railway.app |
| Backend  | https://cotacoes-dashboard-production.up.railway.app   |
| MySQL    | Conexão interna no Railway                             |

### 📁 Estrutura no Railway

```
Railway Project
├── cotacoes-dashboard (Backend - Spring Boot)
├── terrific-flexibility (Frontend - React)
└── MySQL (Banco de dados)
```

---

## 💻 Como Executar Localmente

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

Acesse: http://localhost:8080

---

### Frontend

```bash
cd frontend/dashboard
npm install
npm start
```

Acesse: http://localhost:3000

---

### 🗄️ Banco de Dados Local

Crie o banco no MySQL:

```sql
CREATE DATABASE cotacoes;
```

Configuração no `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/cotacoes
spring.datasource.username=root
spring.datasource.password=SUASENHA
spring.jackson.time-zone=America/Sao_Paulo
```

---

## 🔌 Endpoints da API

### 📥 Listar Cotações

```
GET /cotacoes
```

### Exemplo de resposta:

```json
[
  {
    "id": 1,
    "moeda": "bitcoin",
    "valor": 372358,
    "dataHora": "2026-04-14T16:19:48"
  },
  {
    "id": 2,
    "moeda": "ethereum",
    "valor": 11623.7,
    "dataHora": "2026-04-14T16:19:48"
  }
]
```

---

## 🐳 Configuração para Deploy

### Backend (`backend/Dockerfile`)

```dockerfile
FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline -B
COPY src src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

### Frontend (`frontend/dashboard/Dockerfile`)

```dockerfile
FROM node:18-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM node:18-alpine
RUN npm install -g serve
WORKDIR /app
COPY --from=build /app/build ./build
CMD serve -s build -l $PORT
```

---

### 🌱 Variáveis de Ambiente (Railway)

#### Backend:

```
SPRING_DATASOURCE_URL=jdbc:mysql://${MYSQLHOST}:${MYSQLPORT}/${MYSQLDATABASE}
SPRING_DATASOURCE_USERNAME=${MYSQLUSER}
SPRING_DATASOURCE_PASSWORD=${MYSQLPASSWORD}
SPRING_JPA_HIBERNATE_DDL_AUTO=update
```

---

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com foco em:

* Prática de desenvolvimento fullstack
* Integração entre frontend e backend
* Consumo de APIs externas
* Persistência de dados
* Criação de dashboards interativos
* Deploy em nuvem com Docker e Railway

---

## 🔮 Possíveis Melhorias

* Adicionar mais criptomoedas
* Implementar autenticação de usuários
* Criar filtros por período (dia, semana, mês)
* Notificações de variação de preço
* Testes automatizados

---

## 👨‍💻 Autor

Desenvolvido por **Tiago Cypriano**
