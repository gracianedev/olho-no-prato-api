#  olho-no-prato-api 🍽️

API REST para o projeto **OlhoNoPrato.app**, uma aplicação para auxiliar no acompanhamento da rotina alimentar através do registro de fotos das refeições e do peso corporal.

Este projeto está sendo desenvolvido como parte de uma jornada de aprendizado e prática em desenvolvimento backend com Java e Spring Boot. O desenvolvimento está sendo realizado com auxílio de uma IA (Gemini 2.5 Pro).

---

## 🎯 Funcionalidades Planejadas (MVP)

O objetivo é construir um Mínimo Produto Viável (MVP) com as seguintes funcionalidades:

* [x] **Cadastro de Usuário:** Criação de novos usuários com nome, email e senha.
* [ ] **Autenticação de Usuário:** Login e segurança dos endpoints.
* [ ] **Registro de Peso:** Permite ao usuário salvar seu peso em uma data específica.
* [ ] **Registro de Refeição:** Upload da foto de uma refeição (café, almoço, janta, etc.).
* [ ] **Visualização de Histórico:** Listagem dos registros de peso e refeições por data.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** H2 Database (para ambiente de desenvolvimento)
* **Validação:** Spring Boot Starter Validation
* **Build Tool:** Maven
* **Utilitários:** Lombok

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

Até o momento, o seguinte endpoint está disponível:

#### `POST /usuarios`
* Cria um novo usuário.
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

---

## 📝 Status do Projeto

**Em desenvolvimento...** 🚧