
# Desafio ApCoders!

Projeto de desafio do programa ApCoders onde os candidatos devem implementar sistema que auxilie no cadastro e gestão dos inquilinos e unidades de um condomínio.

**Requisitos**:
 - [x] Modelagem do banco de dados
 - [x] Utilização de banco de dados relacional
 - [x] Controle de versionamento (Git e GitHub)
 - [X] Documentação do código
 - [X] Implementação do sistema
 - [x] Utilização de OOP
 - [x] Utilização do padrão de projeto MVC
 - [x] Testes unitários

## Sobre a API

A API foi desenvolvida com Java, Spring Boot e Spring Framework. A URL base da API é `/api`.
A documentação completa com as URLs específicas, parâmetros e métodos (GET, POST, DELETE) pode ser consultada no seguinte endereço [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) com a aplicação rodando.

## Como rodar
 - Instalar o banco de dados MySQL;
 - Criar um `schema` no banco, por padrão o nome já configurado na aplicação é `desafio_apcoders`:
	 - `CREATE SCHEMA desafio_apcoders;`
 - Clonar o projeto:
`git clone https://github.com/WilliamAB/desafioapcoders.git`
 - Configurar o arquivo `application.resources` dentro de `src/main/resources`:
	 - URL de acesso ao banco (conforme banco instalado e nome do schema criado): `spring.datasource.url=jdbc:mysql://localhost:3306/desafio_apcoders`;
	 - Usuário do banco de dados: `spring.datasource.username=root`
	 - Senha do usuário do banco: `spring.datasource.password=senha`
	 - Dialeto do banco de dados (conforme banco instalado): `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect`
 - Executar a aplicação a partir do seguinte comando dentro do diretório clonado:
 `gradlew bootRun`
 - Acesse [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) para consultar a documentação da API e poder realizar os testes de requisições.
 
## Como testar a API

Pelo site da documentação da API é possível realizar os testes, basta clicar em `Try it out` em cada requisição.

Outra possibilidade é utilizando o Postman. Basta baixar e enviar as requisições com os respectivos métodos, parâmetros e bodies.

## Modelagem do Banco de Dados

Abaixo anexo da modelagem do banco de dados.

![Alt text](https://github.com/WilliamAB/desafioapcoders/blob/main/db-model/db-model.png?raw=true)
