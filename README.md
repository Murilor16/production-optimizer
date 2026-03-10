# Otimizador de Produção

Aplicação web para gerenciamento de estoque de matérias-primas e otimização de produção industrial para maximizar lucro.

## 🎯 Funcionalidades

- **Gerenciamento de Matérias-Primas**: CRUD completo para controle de estoque
- **Gerenciamento de Produtos**: Definição de produtos com suas composições (matérias-primas necessárias)
- **Otimização de Produção**: Cálculo do plano de produção ótimo para maximizar receita baseado no estoque disponível
- **Arquitetura Limpa**: Backend segue princípios de clean code com separação de responsabilidades
- **Stack Moderna**: Spring Boot + Vue.js + PostgreSQL/H2

## 🛠️ Tecnologias

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database (in-memory para desenvolvimento)
- PostgreSQL (pronto para produção)
- Maven
- JUnit 5 + Mockito

### Frontend
- Vue.js 3
- Vue Router
- Axios
- Vite

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Node.js 18+ e npm
- Git

## 🚀 Como Executar

### Backend

1. Navegue até o diretório backend:
```bash
cd backend
```

2. Compile o projeto:
```bash
mvn clean install
```

3. Execute a aplicação:
```bash
mvn spring-boot:run
```

O backend iniciará em `http://localhost:8080`

### Frontend

1. Navegue até o diretório frontend:
```bash
cd frontend
```

2. Instale as dependências:
```bash
npm install
```

3. Execute o servidor de desenvolvimento:
```bash
npm run dev
```

O frontend iniciará em `http://localhost:5173` que será onde você vai poder usar o sistema

## 🧪 Executar Testes

### Testes Backend

```bash
cd backend
mvn test
```

## 📊 Configuração do Banco de Dados

### H2 (Padrão - Desenvolvimento)

A aplicação usa banco de dados H2 in-memory por padrão. Acesse o console H2 em:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:productiondb`
- Username: `sa`
- Password: (vazio)

### PostgreSQL (Produção)

Para usar PostgreSQL, atualize `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/productiondb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## 🎮 Como Usar

1. **Adicionar Matérias-Primas**: Vá para a página "Matérias-Primas" e adicione itens do estoque (ex: Aço, Plástico)
2. **Criar Produtos**: Vá para a página "Produtos" e defina produtos com suas composições
3. **Otimizar Produção**: Vá para a página "Otimização" e clique em "Calcular Produção Ótima"

## 🧮 Algoritmo de Otimização

O sistema usa um **Algoritmo Greedy** que:
1. Ordena produtos por preço (maior primeiro)
2. Para cada produto, calcula a quantidade máxima que pode ser produzida com o estoque disponível
3. Produz o máximo possível de produtos de maior preço primeiro
4. Atualiza o estoque restante após cada decisão de produção
5. Retorna o plano de produção ótimo com receita total máxima

## 📁 Estrutura do Projeto

```
.
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/projedata/optimizer/
│   │   │   │   ├── entity/          # Entidades JPA
│   │   │   │   ├── repository/      # Camada de acesso a dados
│   │   │   │   ├── service/         # Lógica de negócio
│   │   │   │   ├── controller/      # Endpoints REST
│   │   │   │   └── dto/             # Objetos de transferência de dados
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/                    # Testes unitários
│   └── pom.xml
│
└── frontend/
    ├── src/
    │   ├── components/              # Componentes Vue
    │   ├── views/                   # Componentes de página
    │   ├── services/                # Serviço de API
    │   ├── App.vue
    │   ├── main.js
    │   └── router.js
    ├── package.json
    └── vite.config.js
```

## 🔌 Endpoints da API

### Matérias-Primas
- `GET /api/raw-materials` - Listar todas as matérias-primas
- `GET /api/raw-materials/{id}` - Buscar matéria-prima por ID
- `POST /api/raw-materials` - Criar matéria-prima
- `PUT /api/raw-materials/{id}` - Atualizar matéria-prima
- `DELETE /api/raw-materials/{id}` - Deletar matéria-prima

### Produtos
- `GET /api/products` - Listar todos os produtos
- `GET /api/products/{id}` - Buscar produto por ID
- `POST /api/products` - Criar produto
- `PUT /api/products/{id}` - Atualizar produto
- `DELETE /api/products/{id}` - Deletar produto

### Otimização
- `GET /api/optimization/calculate` - Calcular produção ótima

## 👨💻 Desenvolvimento

### Qualidade de Código
- Princípios de Clean Code aplicados
- Convenções de nomenclatura em inglês
- Separação de responsabilidades (Entity, Repository, Service, Controller)
- DTOs para comunicação com a API
- Testes unitários abrangentes

### Testes
- Testes unitários para algoritmo de otimização
- Mockito para simulação de dependências
- Cobertura de testes para lógica de negócio crítica

## 📝 Licença

Este projeto foi desenvolvido como teste prático para a Projedata.

## 🤝 Contribuindo

Este é um projeto de teste. Para dúvidas ou sugestões, entre em contato com a equipe de desenvolvimento.
