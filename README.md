
# Desafio ApCoders!

Projeto de desafio do programa ApCoders onde os candidatos devem implementar sistema que auxilie no cadastro e gest�o dos inquilinos e unidades de um condom�nio.

**Requisitos**:
 - [x] Modelagem do banco de dados
 - [x] Utiliza��o de banco de dados relacional
 - [x] Controle de versionamento (Git e GitHub)
 - [X] Documenta��o do c�digo
 - [X] Implementa��o do sistema
 - [x] Utiliza��o de OOP
 - [x] Utiliza��o do padr�o de projeto MVC
 - [x] Testes unit�rios

## Sobre a API

A API foi desenvolvida com Java, Spring Boot e Spring Framework. A URL base da API � `/api`.
A documenta��o completa com as URLs espec�ficas, par�metros e m�todos (GET, POST, DELETE) pode ser consultada no seguinte endere�o [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) com a aplica��o rodando.

## Como rodar
 - Instalar o banco de dados MySQL;
 - Criar um `schema` no banco, por padr�o o nome j� configurado na aplica��o � `desafio_apcoders`:
	 - `CREATE SCHEMA desafio_apcoders;`
 - Clonar o projeto:
`git clone https://github.com/WilliamAB/desafioapcoders.git`
 - Configurar o arquivo `application.resources` dentro de `src/main/resources`:
	 - URL de acesso ao banco (conforme banco instalado e nome do schema criado): `spring.datasource.url=jdbc:mysql://localhost:3306/desafio_apcoders`;
	 - Usu�rio do banco de dados: `spring.datasource.username=root`
	 - Senha do usu�rio do banco: `spring.datasource.password=senha`
	 - Dialeto do banco de dados (conforme banco instalado): `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect`
 - Executar a aplica��o a partir do seguinte comando dentro do diret�rio clonado:
 `gradlew bootRun`
 - Acesse [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) para consultar a documenta��o da API e poder realizar os testes de requisi��es.
 
## Como testar a API

Pelo site da documenta��o da API � poss�vel realizar os testes, basta clicar em `Try it out` em cada requisi��o.

Outra possibilidade � utilizando o Postman. Basta baixar e enviar as requisi��es com os respectivos m�todos, par�metros e bodies.

## Modelagem do Banco de Dados

Abaixo anexo da modelagem do banco de dados.

[Modelagem](https://github.com/WilliamAB/desafioapcoders/blob/main/db-model/db-model.png)
