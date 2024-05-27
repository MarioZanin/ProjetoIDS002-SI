# ProjetoIDS002-SI
Projeto do curso de SI da FATEC-Rubens Lara destinado a construção de uma API  que deve controlar e gerenciar o inventário de produtos 
Projeto de Construção de APIs

## Como baixar o projeto:
Após acessar o repositório: MarioZanin/ProjetoIDS002-SI.
Clique no botão Code;
Opte pela aba “Local”;
Escolha a forma que deseja fazer o pull do programa. 
Sugiro que faça o PULL pelo HTTPS copiando o link descrito (https://github.com/MarioZanin/ProjetoIDS002-SI.git)
Efetue o PULL da forma e no programa que desejar, salvando-o no local de sua preferência.
Após e só ir e abrir o arquivo criado chamado "ProjetoIDSoo2-Si".
Dentro deste diretório, acesse/abra a pasta "projeto-invetproduto" pois nela estará o projeto criado no qual deverá ser trabalhado.
Se estiver usando o VSCode, basta abrir diretamente o arquivo ProjetoIDS002-Si que você terá acesso a todo os arquivos e, posteriormente, basta selecionar e abrir a pasta "projeto-inventproduto" que terá acesso a toda a estrutura criada.  

## Abordagem do projeto:
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
