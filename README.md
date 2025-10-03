# olho-no-prato-api 🍽️

API REST para o projeto **OlhoNoPrato.app**, uma aplicação para auxiliar no acompanhamento da rotina alimentar através do registro de fotos das refeições e do peso corporal.

Este projeto foi desenvolvido como parte de uma jornada de aprendizado em desenvolvimento backend com Java e Spring Boot, com foco em boas práticas, segurança e deploy em nuvem.

**[🚀 API Online no Render](https://olhonoprato-api.onrender.com)**

---

## ✅ Status do Projeto

**MVP Concluído e em Produção!** 🎉

A versão 1.0 da API está funcional e hospedada na nuvem. Próximos passos incluem a implementação das funcionalidades de Update e Delete (CRUD completo) e a criação de um endpoint para exportação de dados.

---

## ✨ Funcionalidades

* [x] **Cadastro e Autenticação de Usuário:** Criação de novos usuários (com senha criptografada) e login com geração de token JWT.
* [x] **CRUD (Parcial) de Registros de Peso:** Permite ao usuário salvar e listar seu peso em datas específicas.
* [x] **CRUD (Parcial) de Registros de Refeição:** Permite ao usuário salvar e listar detalhes de suas refeições.
* [x] **Segurança:** Endpoints protegidos, garantindo que cada usuário só acesse seus próprios dados.
* [x] **Paginação e Ordenação:** As listagens de dados são paginadas para melhor performance.
* [x] **Busca Avançada:** Endpoints para filtrar registros por tipo e/ou período.
* [x] **Deploy:** A aplicação está conteinerizada com Docker e hospedada na plataforma Render.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL
* **Segurança:** Spring Security com JSON Web Tokens (JWT)
* **Conteinerização:** Docker
* **Nuvem (Cloud):** Render
* **Build Tool:** Maven
* **Utilitários:** Lombok

---

## 🚀 Como Usar a API Online

Você pode interagir com a API hospedada usando qualquer cliente HTTP, como o Postman.

* **URL Base:** `https://olhonoprato-api.onrender.com`

Lembre-se que o primeiro acesso após 15 minutos de inatividade pode ser lento devido à hibernação do plano gratuito do Render.

## 📋 Endpoints da API

A API está disponível publicamente na seguinte URL base:

* **URL Base:** `https://olhonoprato-api.onrender.com`

**Lembrete:** A primeira requisição após 15 minutos de inatividade pode ser lenta devido à hibernação do plano gratuito do Render. Todos os endpoints marcados como **[PROTEGIDO]** exigem um token JWT no cabeçalho `Authorization`.

---
### Autenticação e Usuários

#### `POST /usuarios`
* **[PÚBLICO]** Cadastra um novo usuário no sistema.
* **URL Completa:** `https://olhonoprato-api.onrender.com/usuarios`

#### `POST /login`
* **[PÚBLICO]** Autentica um usuário, retornando um token JWT.
* **URL Completa:** `https://olhonoprato-api.onrender.com/login`

---
### Registros de Peso

#### `POST /pesos`
* **[PROTEGIDO]** Cadastra um novo registro de peso.
* **URL Completa:** `https://olhonoprato-api.onrender.com/pesos`

#### `GET /pesos`
* **[PROTEGIDO]** Lista os registros de peso do usuário de forma paginada.
* **Exemplo:** `https://olhonoprato-api.onrender.com/pesos?size=5&sort=dataRegistro,desc`

#### `GET /pesos/buscar`
* **[PROTEGIDO]** Busca registros de peso do usuário por um período.
* **Exemplo:** `https://olhonoprato-api.onrender.com/pesos/buscar?dataInicial=2025-10-01&dataFinal=2025-10-31`

---
### Registros de Refeição

#### `POST /refeicoes`
* **[PROTEGIDO]** Cadastra um novo registro de refeição.
* **URL Completa:** `https://olhonoprato-api.onrender.com/refeicoes`

#### `GET /refeicoes`
* **[PROTEGIDO]** Lista os registros de refeição do usuário de forma paginada.
* **Exemplo:** `https://olhonoprato-api.onrender.com/refeicoes?page=1&size=5`

#### `GET /refeicoes/buscar`
* **[PROTEGIDO]** Busca registros de refeição do usuário por tipo e período.
* **Exemplo:** `https://olhonoprato-api.onrender.com/refeicoes/buscar?tipo=ALMOCO&dataInicial=2025-09-15&dataFinal=2025-09-20`
---

## 💻 Como Executar o Projeto Localmente

1.  **Pré-requisitos:**
    * Java (JDK) 21 ou superior.
    * Maven 3.8 ou superior.
    * PostgreSQL rodando localmente.
    * Docker (opcional, para rodar via container).

2.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/gracianedev/olho-no-prato-api.git](https://github.com/gracianedev/olho-no-prato-api.git)
    cd olho-no-prato-api
    ```

3.  **Configure as Variáveis de Ambiente:**
    * Crie um arquivo `application.properties` ou configure as variáveis na sua IDE com as credenciais do seu banco de dados PostgreSQL local (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`) e um segredo para o JWT (`JWT_SECRET`).

4.  **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```
    * A API estará disponível em `http://localhost:8080`.