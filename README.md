# Desafio Técnico TexoIt

## Instruções para executar o projeto
O projeto foi criado utilizando o framework *SpringBoot*.

Na pasta do projeto, execute o comando:

```
mvnw clean spring-boot:run
```

Após a inicialização do projeto, importe a collection *TexoIt.postman_collection.json* no Postman.

O projeto utiliza um banco de dados interno, do tipo HSQLDB. Os dados para conexão são:

```
url=jdbc:h2:mem:mydb
username=sa
password=
```
OBS: quando o projeto é interrompido, a tabela é limpa.

O banco de dados possui apenas a tabela *movies* e possui a seguinte estrutura:

- id (integer)
- movie_year (integer)
- movie_title (varchar)
- movie_studios (varchar)
- movie_producers (varchar)
- winner (boolean)

A tabela é populada assim que a aplicação é iniciada, a partir do arquivo *movielist.csv*, localizado na pasta *src/main/resources*

## Testes Unitários
Para executar os testes do projeto, execute o comando abaixo:

```
mvnw clean test
```

Os testes cobrem apenas a classe *MoviesService*, pois ela que possui toda a lógica de negócio.