# Desafio Técnico TexoIt

## Instruções para executar o projeto
O projeto foi criado utilizando o framework *SpringBoot*.

Na pasta do projeto, execute o comando:

```
mvnw clean spring-boot:run
```

Após a inicialização do projeto, importe a collection *Builders.postman_collection.json* no Postman.

O projeto utiliza um banco de dados interno, do tipo HSQLDB. Os dados para conexão são:

```
url=jdbc:h2:mem:mydb
username=sa
password=
```
Caso a conexão com o banco não seja possível, existe um endpoint que irá listar todos os boletos cadastrados na tabela.

OBS: quando o projeto é interrompido, a tabela é limpa.

OBS: pelo enunciado do desafio, entendi que era necessário salvar na tabela apenas os boletos com cálculos permitidos, então não está salvando os boletos inválidos.

## Testes Unitários
Para executar os testes do projeto, execute o comando abaixo:

```
mvnw clean test
```

Os testes cobrem apenas a classe *PaymentService*, pois ela que possui toda a lógica de negócio.