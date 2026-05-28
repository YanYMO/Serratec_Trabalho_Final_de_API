# 🎬 SerratecFlix API

API RESTful desenvolvida com Spring Boot para gerenciamento de filmes, séries, avaliações, categorias, favoritos e usuários.

---

## 📚 Sobre o Projeto

O **SerratecFlix API** é um sistema inspirado em plataformas de streaming, permitindo:

* Cadastro de filmes e séries
* Avaliações de conteúdos
* Gerenciamento de categorias
* Lista de favoritos
* Cadastro e autenticação de usuários
* Controle de acesso com JWT
* Documentação automática com Swagger/OpenAPI
* Aceso a informações de uma API externa

---

## 🚀 Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Web 
* Spring Data JPA
* Spring Security
* JWT Authentication
* PostgreSQL
* Lombok
* ModelMapper
* Swagger/OpenAPI
* Maven

---

## 🗂️ Estrutura do Projeto

```bash
src/main/java/org/serratec/serratecFlix
│
├── config          # Configurações da aplicação
├── controller      # Endpoints da API
├── dto             # Objetos de transferência de dados
├── entity          # Entidades do banco
├── enums           # Enumerações
├── exception       # Tratamento de exceções
├── repository      # Interfaces JPA
├── security        # Configuração JWT e filtros
└── service         # Regras de negócio
```

---

## 🛠️ Configuração do Banco de Dados

A aplicação utiliza PostgreSQL.

### 📌 Configuração atual (`application.properties`)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/serratec_flix
spring.datasource.username=postgres
spring.datasource.password=1234
```

### ✅ Crie o banco:

```sql
CREATE DATABASE serratec_flix;
```

---

## ▶️ Como Executar o Projeto

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/YanYMO/Serratec_Trabalho_Final_de_API.git
```

### 2️⃣ Acesse a pasta do projeto

```bash
cd serratecFlix
```

### 3️⃣ Execute a aplicação

Pelo terminal:

```bash
./mvnw spring-boot:run
```

Ou execute diretamente pela IDE.

---

## 🔐 Autenticação JWT

A API utiliza autenticação JWT para proteger endpoints.

Após realizar login, será retornado um token JWT que deverá ser enviado no header das requisições:

```http
Authorization: Bearer SEU_TOKEN
```

---

## 📌 Principais Endpoints

### 👤 Usuários

| Método | Endpoint              | Descrição         |
| ------ | --------------------  | ----------------- |
| POST   | `/usuarios/cadastrar` | Cadastrar usuário |
| POST   | `/login`              | Realizar login    |
| GET    | `/usuarios`           | Listar usuários   |

---

### 🎬 Filmes

| Método | Endpoint          | Descrição                 |
| ------ | ----------------  | ------------------------- |
| GET    | `/filmes`         | Listar filmes             |
| GET    | `/filmes/{id}`    | Buscar filme por ID       |
| POST   | `/filmes`         | Cadastrar filme           |
| PUT    | `/filmes/{id}`    | Atualizar filme           |
| DELETE | `/filmes/{id}`    | Remover filme             |
| GET    | `/filmes/externo` | Usar API externa de filmes|


---

### 📺 Séries

| Método | Endpoint       | Descrição       |
| ------ | -------------- | --------------- |
| GET    | `/series`      | Listar séries   |
| POST   | `/series`      | Cadastrar série |
| PUT    | `/series/{id}` | Atualizar série |
| DELETE | `/series/{id}` | Remover série   |

---

### ⭐ Avaliações

| Método | Endpoint            | Descrição                   |
| ------ | ------------------- | --------------------------- |
| POST   | `/avaliacoes-filme` | Avaliar filme               |
| POST   | `/avaliacoes-serie` | Avaliar série               |
| GET    | `/avaliacoes-filme` | Listar avaliações de filmes |
| GET    | `/avaliacoes-serie` | Listar avaliações de séries |

---

### ❤️ Favoritos

| Método | Endpoint                      | Descrição          |
| ------ | ---------------------------   | ------------------ |
| GET    | `/lista-favoritos`            | Listar favoritos   |
| POST   | `/lista-favoritos`            | Adicionar favorito |
| DELETE | `/lista-favoritos/{id}`       | Remover favorito   |

---

### 🗂️ Categorias

| Método | Endpoint           | Descrição               |
| ------ | ------------------ | ----------------------- |
| GET    | `/categorias`      | Listar categorias       |
| GET    | `/categorias/{id}` | Buscar categoria por ID |
| POST   | `/categorias`      | Cadastrar categoria     |
| PUT    | `/categorias/{id}` | Atualizar categoria     |
| DELETE | `/categorias/{id}` | Remover categoria       |

---

### 🏆 Prêmios

| Método | Endpoint                   | Descrição                |
| ------ | -------------------------- | ------------------------ |
| GET    | `/premios`                 | Listar prêmios           |
| GET    | `/premios/{id}`            | Buscar prêmio por ID     |
| GET    | `/premios/filme/{idFilme}` | Buscar prêmios por filme |
| GET    | `/premios/serie/{idSerie}` | Buscar prêmios por série |
| POST   | `/premios`                 | Cadastrar prêmio         |
| PUT    | `/premios/{id}`            | Atualizar prêmio         |
| DELETE | `/premios/{id}`            | Remover prêmio           |

---

### 👑 Experiência

| Método | Endpoint                   | Descrição                       |
| ------ | -------------------------- | ------------------------------- |
| GET    | `/experiencias`            | Listar experiências             |
| GET    | `/experiencias/{id}`       | Buscar experiência por ID       |
| GET    | `/experiencias/minhaexp`   | Buscar a experiencia do usuario |

---

### 🕘 Histórico de Exibição

| Método | Endpoint                  | Descrição                             |
| ------ | ------------------------- | ------------------------------------- |
| GET    | `/historicos/filmes`      | Listar histórico de filmes            |
| GET    | `/historicos/series`      | Listar histórico de séries            |
| GET    | `/historicos/resumo`      | Exibir resumo do histórico do usuário |
| GET    | `/historicos/completo`    | Listar histórico completo do usuário  |
| POST   | `/historicos`             | Cadastrar histórico                   |
| PUT    | `/historicos/{id}/status` | Atualizar status do histórico         |
| DELETE | `/historicos/{id}`        | Remover histórico                     |

---

### 🏆 Ranking

| Método | Endpoint                   | Descrição                       |
| ------ | -------------------------- | ------------------------------- |
| GET    | `/ranking/filmes`          | Top5 filmes mais bem avaliados  |
| GET    | `/ranking/series`          | Top5 séries mais bem avaliadas  |

---

## 📖 Swagger / OpenAPI

Após iniciar a aplicação, acesse:

```bash
http://localhost:8080/swagger-ui.html
```

ou

```bash
http://localhost:8080/swagger-ui/index.html
```

---

## 🔒 Segurança

A aplicação utiliza:

* Spring Security
* JWT Authentication Filter
* JWT Authorization Filter
* Controle de acesso por autenticação

---

## 📌 Funcionalidades

* ✅ CRUD completo de filmes e séries

* ✅ Cadastro e login de usuários

* ✅ Sistema de favoritos

* ✅ Avaliação de filmes e séries

* ✅ Relacionamento entre entidades

* ✅ Validações com Bean Validation

* ✅ Tratamento global de exceções

* ✅ Documentação automática da API

* ✅ Segurança com JWT

* ✅ Consumo de API externa

---

## 👨‍💻 Desenvolvedores / Tarefas Individuais

| Desenvolvedor                 | Responsabilidades                                                                                                                |
| ----------------------------- |----------------------------------------------------------------------------------------------------------------------------------|
| **João Pedro Carneiro Motta** | Prêmios (todas as classes, endpoints e repositories relacionados)                                                                |
| **Yan Martins de Oliveira**   | Validação de idade ao acessar os filmes e séries (Metodo/Função) seja acessando, avaliando ou adicionando na lista de favoritos. |
| **Carlos Eduardo Carvalho**   | Experiência (todas as classes, endpoints e repositories relacionados)                                                            |
| **Marcos Paulo**              | Histórico de exibição (todas as classes, endpoints e repositories relacionados)                                                  |
| **Phelipe Damasio**           | Ranking (todas as classes, endpoints e repositories relacionados)                                                                |


Projeto desenvolvido como trabalho final utilizando Spring Boot e PostgreSQL.

---

## 🔨 Separação Inicial de Tarefas</b></h2>

| Desenvolvedor                 | Responsabilidades Gerais                                                                   |
| ----------------------------- | ------------------------------------------------------------------------------------------ |
| **João Pedro Carneiro Motta** | Criar o config do Swagger, criar os repositories principais, ajudar na criação de services |
| **Yan Martins de Oliveira**   |                                                                                            |
| **Carlos Eduardo Carvalho**   | Criar ResponseDTO, Exceptions e Fazer a Analise dos Pull Request antes dos Merges.         |
| **Marcos Paulo**              |                                                                                            |
| **Phelipe Damasio**           |                                                                                            |

---


