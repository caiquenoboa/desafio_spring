# Desafio Java Spring Boot

## Desenvolvedor
Paulo Gustavo de Carvalho Souza - (Grupo 5)

## Informacções gerais
O Projeto foi desenvolvido utilizando java, spring boot e H2 como banco de dados.
Os dados sempre serão salvo [nesse arquivo](https://github.com/caiquenoboa/desafio_spring/blob/paulo/src/main/resources/db/desafio.mv.db).


## Para acessar o H2
    JDBC URL: jdbc:h2:file:./src/main/resources/db/desafio
    User name: root
    Password: root

## Cadastro de usuário
    POST localhost:8080/users/

    {
        "name" : "{Nome do usuário}",
        "type" : "{SELLER || CLIENT}"
    }

## Usuários já cadastrados

id | userName 
--- | --- 
1 | VENDEDOR1 
2 | VENDEDOR2 
3 | CLIENTE1 
4 | CLIENTE2 

## Requisitos implementados
Requisito | Implementado
--- | ---
US 0001 | ✅
US 0002 | ✅
US 0003 | ✅
US 0004 | ✅
US 0005 | ✅
US 0006 | ✅
US 0007 | ✅
US 0008 | ✅
US 0009 | ✅
US 0010 | ✅
US 0011 | ✅
US 0012 | ✅

## Refatoração

* Criei a classe [OrderListUtil](https://github.com/caiquenoboa/desafio_spring/blob/paulo/src/main/java/com/meli/desafiospring/util/list/OrderListUtil.java) 
para abstrair e juntar a forma com que sorteia uma lista em uma classe só.