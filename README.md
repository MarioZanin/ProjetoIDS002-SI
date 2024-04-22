# ProjetoIDS002-SI
Projeto do curso de SI da FATEC-Rubens Lara destinado a construção de uma API  que deve controlar e gerenciar o inventário de produtos 
Projeto de Construção de APIs

## Abordagem:
Desenvolvimento de uma aplicação com REST APIs utilizando Java + Spring Boot

## Itens obrigatórios:
APIs que possuam os principais verbos (GET, POST, PUT, PATCH, DELETE)
Utilização do nível de maturidade 2 ou 3 + implementação HATEOAS
Tratamento de erros adequados da API / aplicação
Aplicação estruturada em camadas
Service, Controller, DTO, Bean, Repository
Outras camadas podem ser adicionadas
Versionamento da aplicação utilizando Git/Github
Uso de persistência (relacional ou não relacional)
Documentação das APIs (Swagger)
Segurança das APIs

## API de inventário de produtos:
O projeto deve gerenciar os produtos de um inventário
•	GET - Para obter todos os produtos ou um produto por id.
•	POST - Criar um novo produto
•	DELETE - Excluir um produto
•	PUT - Substituir todas as informações de um produto, dado um id.
•	PATCH - Atualizar parcialmente um produto, como seu produto ou quantidade.

Campos (3)
Product
•	id (Long)
•	name (String)
•	description (String)
•	price (BigDecimal)
•	quantity (Integer)
•	category (String)
