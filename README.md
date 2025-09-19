#  olho-no-prato-api 🍽️

API REST para o projeto **OlhoNoPrato.app**, uma aplicação para auxiliar no acompanhamento da rotina alimentar através do registro de fotos das refeições e do peso corporal.

Este projeto está sendo desenvolvido como parte de uma jornada de aprendizado e prática em desenvolvimento backend com Java e Spring Boot. O desenvolvimento está sendo realizado com auxílio de uma IA (Gemini 2.5 Pro).

---

## 🎯 Funcionalidades Planejadas (MVP)

O objetivo é construir um Mínimo Produto Viável (MVP) com as seguintes funcionalidades:

* [x] **Cadastro de Usuário:** Criação de novos usuários com nome, email e senha.
* [x] **Autenticação de Usuário:** Login com e-mail/senha, geração e validação de token JWT para proteger endpoints.
* [x] **CRUD de Registro de Peso:** Permite ao usuário salvar seu peso em uma data específica.
* [x] **CRUD de Registro de Refeição:** Upload da foto de uma refeição (café, almoço, janta, etc.).
* [x] **Segurança:** Endpoints protegidos, garantindo que cada usuário só acesse seus próprios dados.
* [x] **Paginação e Ordenação:** As listagens de dados são paginadas para melhor performance.
* [x] **Busca Avançada:** Endpoints para filtrar registros por tipo e/ou período.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** H2 Database (para ambiente de desenvolvimento)
* **Validação:** Spring Boot Starter Validation
* **Autenticação:** JSON Web Tokens (JWT)
* **Build Tool:** Maven
* **Utilitários:** Lombok

---

## ✨ Funcionalidades

* [x] **Cadastro de Usuário:** Criação de novos usuários com senha criptografada (BCrypt).
* [x] **Autenticação de Usuário:** Login com e-mail/senha, geração e validação de token JWT para proteger endpoints.
* [x] **Cadastro de Registro de Peso:** Permite que um usuário autenticado salve seu peso em uma data específica.
* [x] **Listagem de Dados:** Permite que um usuário autenticado liste seus dados (usuários e registros de peso).

---

## 🚀 Como Executar o Projeto

1.  **Pré-requisitos:**
    * Java (JDK) 17 ou superior.
    * Maven 3.8 ou superior.

2.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/olho-no-prato-api.git](https://github.com/seu-usuario/olho-no-prato-api.git)
    ```

3.  **Execute a aplicação:**
    * Abra o projeto na sua IDE de preferência (IntelliJ, VS Code, etc.).
    * Aguarde a IDE baixar todas as dependências do Maven.
    * Execute a classe principal `OlhonopratoApplication.java`.
    * A API estará disponível em `http://localhost:8080`.

---

## 📋 Endpoints da API

Até o momento, os seguintes endpoints estão disponíveis:

#### `POST /usuarios`
* **[PÚBLICO]** Cadastra um novo usuário no sistema.
* **Corpo da Requisição (JSON):**
    ```json
    {
        "nome": "Seu Nome",
        "email": "seu@email.com",
        "senha": "sua_senha_min_6_chars"
    }
    ```
* **Resposta de Sucesso (Status `201 Created`):**
    ```json
    {
        "id": 1,
        "nome": "Seu Nome",
        "email": "seu@email.com",
        "senha": "sua_senha_min_6_chars"
    }
    ```
#### `POST /login`
* **[PÚBLICO]** Autentica um usuário com e-mail e senha, retornando um token JWT em caso de sucesso.
* **Corpo da Requisição (JSON):**
    ```json
    {
        "email": "seu@email.com",
        "senha": "sua_senha_cadastrada"
    }
    ```
* **Resposta de Sucesso (Status `200 OK`):**
    ```json
    {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgT2xob05v..."
    }
    ```
#### `GET /usuarios`
* **[PROTEGIDO]** Lista todos os usuários cadastrados no sistema (requer token).
* **Cabeçalho de Autenticação:**
    ```
    Authorization: Bearer <seu_token_jwt>
    ```
* **Resposta de Sucesso (Status `200 OK`):**
    ```json
    [
        {
            "id": 1,
            "nome": "Usuário Teste 1",
            "email": "teste1@email.com"
        },
        {
            "id": 2,
            "nome": "Usuário Teste 2",
            "email": "teste2@email.com"        
        }
    ]
    ```
  ### Registros de Peso

#### `POST /registros-peso`
* **[PROTEGIDO]** Cadastra um novo registro de peso para o usuário autenticado.
* **Cabeçalho de Autenticação:**
    ```
    Authorization: Bearer <seu_token_jwt>
    ```
* **Corpo da Requisição (JSON):**
    ```json
    {
        "peso": 75.5
    }
    ```
* **Resposta de Sucesso (Status `201 Created`):**
    * Retorna a URL do novo recurso no cabeçalho `Location`.
    * Retorna o objeto completo do registro no corpo da resposta.
    ```json
    {
        "id": 1,
        "peso": 75.5,
        "dataRegistro": "2025-09-17T20:30:00.123456"
    }
    ```

#### `GET /registros-peso`
* **[PROTEGIDO]** Lista apenas os registros de peso pertencentes ao usuário autenticado.
* **Parâmetros (Opcionais):** `?page=0`, `?size=10`, `?sort=dataRegistro,desc`
* **Exemplo:** `/registros-peso?size=5&page=1`
* **Cabeçalho de Autenticação:**
    ```
    Authorization: Bearer <seu_token_jwt>
    ```
* **Resposta de Sucesso (Status `200 OK`):**
    ```json
    [
        {
            "id": 1,
            "peso": 75.5,
            "dataRegistro": "2025-09-17T20:30:00.123456"
        },
        {
            "id": 2,
            "peso": 76.2,
            "dataRegistro": "2025-09-18T08:15:00.789012"
        }
    ]
    ```
  #### `GET /registros-peso/buscar`
* **[PROTEGIDO]** Busca registros de peso do usuário por um período.
* **Parâmetros (Obrigatórios):**
    * `dataInicial`: Data de início no formato `YYYY-MM-DD`.
    * `dataFinal`: Data de fim no formato `YYYY-MM-DD`.
* **Exemplo:** `/registros-peso/buscar?dataInicial=2025-09-01&dataFinal=2025-09-30`

  ### Registros de Refeição

#### `POST /refeicoes`
* **[PROTEGIDO]** Cadastra um novo registro de refeição para o usuário autenticado.
* **Cabeçalho de Autenticação:**
    ```
    Authorization: Bearer <seu_token_jwt>
    ```
* **Corpo da Requisição (JSON):**
    ```json
    {
        "urlFoto": "[http://site.com/foto.jpg](http://site.com/foto.jpg)",
        "descricaoPrato": "Frango grelhado com salada.",
        "tipoRefeicao": "ALMOCO"
    }
    ```
* **Resposta de Sucesso (Status `201 Created`):**
    ```json
    {
        "id": 1,
        "urlFoto": "[http://site.com/foto.jpg](http://site.com/foto.jpg)",
        "descricao": "Frango grelhado com salada.",
        "tipo": "ALMOCO",
        "dataRegistro": "2025-09-17T22:25:00.123456"
    }
    ```

#### `GET /refeicoes`
* **[PROTEGIDO]** Lista todos os registros de refeição pertencentes ao usuário autenticado.
* **Parâmetros (Opcionais):** `?page=0`, `?size=10`, `?sort=dataRegistro,desc`
* **Exemplo:** `/refeicoes?size=5`
* **Cabeçalho de Autenticação:**
    ```
    Authorization: Bearer <seu_token_jwt>
    ```
* **Resposta de Sucesso (Status `200 OK`):**
    ```json
    [
        {
            "id": 1,
            "urlFoto": "[http://site.com/foto.jpg](http://site.com/foto.jpg)",
            "descricao": "Frango grelhado com salada.",
            "tipo": "ALMOCO",
            "dataRegistro": "2025-09-17T22:25:00.123456"
        }
    ]
    ```
#### `GET /refeicoes/buscar`
* **[PROTEGIDO]** Busca registros de refeição do usuário por tipo e período.
* **Parâmetros (Obrigatórios):**
    * `tipo`: `CAFE_DA_MANHA`, `ALMOCO`, `JANTAR` ou `LANCHE`.
    * `dataInicial`: Data de início no formato `YYYY-MM-DD`.
    * `dataFinal`: Data de fim no formato `YYYY-MM-DD`.
* **Exemplo:** `/refeicoes/buscar?tipo=ALMOCO&dataInicial=2025-09-15&dataFinal=2025-09-20`

## 📝 Status do Projeto

**Em desenvolvimento...** 🚧