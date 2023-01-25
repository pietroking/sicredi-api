# Election-Api
Gerenciador de eleição

## Desafio para empresa Sicredi
Desenvolver uma aplicação para realizar a gestão de uma eleição

## Regras de negócio
- Cadastro elitor / colaborador
  - Não é permitido cadastrar com cpf já cadastrado
  - Tem que obrigatoriamente ser ligado a uma seção
  
- Cadastro candidato
  - Não é permitido cadastrar com cpf já cadastrado
  - Tem que obrigatoriamente possuir um número para voto
  
- Realização do voto
  - O eleitor precisa votar na seção onde ele possui vinculo
  
- Busca do resultado parcial
  - Vizualizar candidatos com a quantidade de votos recebidos

## Pré-requisitos
- Java 11
- Spring 2.3.3.RELEASE
- Maven
- JUnit
- PostgreSQL 15

## Caminho do banco de dados
- DDL: sicredi-api/db/script_postgresql.txt
