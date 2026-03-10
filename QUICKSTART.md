# Quick Start Guide

## Pré-requisitos

- Java 17+ instalado
- Node.js 18+ instalado
- Maven instalado OU use uma IDE (IntelliJ, Eclipse, VS Code)

## Como Executar

### 1. Backend (Terminal 1 ou IDE)

**Opção A - IDE (Recomendado):**
1. Abra a pasta `backend` na sua IDE
2. Execute a classe `ProductionOptimizerApplication.java`
3. Aguarde: "Started ProductionOptimizerApplication"

**Opção B - Maven:**
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### 2. Frontend (Terminal 2)

```bash
cd frontend
npm install
npm run dev
```

### 3. Acessar

Abra o navegador: http://localhost:5173

## Testar a Aplicação

O sistema já vem com dados de exemplo carregados:
- 6 Matérias-Primas
- 5 Produtos

**Para testar a otimização:**
1. Acesse "Otimização" no menu
2. Clique em "Calcular Produção Ótima"
3. Veja os resultados!

## Executar Testes

```bash
cd backend
mvn test
```
