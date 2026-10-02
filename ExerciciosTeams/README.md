# Exercício Teams

## Resumo

Este projeto foi desenvolvido como parte de um exercício de Programação Orientada a Objetos em Java, com foco na criação de uma aplicação desktop para controle de cartas/artefatos. A ideia central foi praticar a organização do código em camadas, o uso de classes modelo, acesso a dados e a construção de interfaces gráficas com Swing.

A aplicação simula um cadastro e consulta de itens, incluindo nome, categoria e força, além de permitir uma visão geral em tabela e a busca por tipo/categoria.

## O que foi implementado

- Cadastro de artefatos/cartas com campos como nome e categoria;
- Estrutura de modelo para representar os dados (
  `Artefato`, `ArtefatoList`, `ArtefatoTableModel`);
- Definição de categorias por enumeração (`Categoria`);
- Interface gráfica com painéis para cadastro e busca (`PainelCadastro`, `PainelBusca`);
- Janela principal com layout em bordas (`JanelaBorderLayout`);
- Acesso a dados em banco via DAO/JDBC (`ArtefatoDao`, `ArtefatoJdbc`, `GerenciadorConexao`);
- Uso de biblioteca MySQL Connector para conexão com banco de dados;
- Tratamento de exceções personalizadas para validação de dados (`DadosException`).

## Estrutura do projeto

```text
ExerciciosTeams/
├── dao/
│   └── ArtefatoDao.java
├── dba/
│   ├── ArtefatoJdbc.java
│   ├── GerenciadorConexao.java
│   ├── TesteConexao.java
│   └── TesteConexao.class
├── lib/
│   └── mysql-connector-j-8.0.33.jar
├── model/
│   ├── Artefato.java
│   ├── ArtefatoList.java
│   └── ArtefatoTableModel.java
├── view/
│   ├── Categoria.java
│   ├── DadosException.java
│   ├── DestacaFocoText.java
│   ├── JanelaBorderLayout.java
│   ├── PainelBusca.java
│   └── PainelCadastro.java
└── README.md
```

## Camadas da aplicação

### Model
Representa os dados e a lógica básica da entidade principal.

- `Artefato`: classe que define os atributos de um artefato/carta;
- `ArtefatoList`: estrutura de armazenamento e manipulação dos itens;
- `ArtefatoTableModel`: modelo usado para exibir os dados em tabela na interface gráfica.

### DAO
Define a interface para operações de persistência e acesso aos dados.

- `ArtefatoDao`: contrato com métodos de incluir, atualizar, excluir e buscar artefatos.

### DBA / JDBC
Responsável pela conexão com o banco e pelo acesso real aos dados.

- `GerenciadorConexao`: gerencia a conexão com o banco;
- `ArtefatoJdbc`: implementa as operações SQL para manipular os artefatos;
- `TesteConexao`: arquivo usado para validar a conexão com o banco.

### View
Contém a parte visual da aplicação em Swing.

- `JanelaBorderLayout`: janela principal da aplicação;
- `PainelCadastro`: painel para inserir novos dados;
- `PainelBusca`: painel para filtrar e consultar itens;
- `Categoria`: enum com os tipos de artefato;
- `DadosException`: exceção personalizada para tratar erros de dados.

## Tecnologias utilizadas

- Java
- Swing (interface gráfica)
- JDBC
- MySQL
- Apache NetBeans/IDE Java (estrutura de organização do projeto)

## Objetivo do exercício

O objetivo principal foi aplicar conceitos de programação orientada a objetos, organização em camadas, acesso a banco de dados e criação de interfaces gráficas simples, reforçando a prática de desenvolvimento de sistemas em Java.

## Observação

O projeto apresenta uma base funcional para cadastro e consulta de cartas/artefatos, sendo um bom exemplo de aplicação desktop com integração de dados e interface visual.
