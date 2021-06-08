# Desafio Java Spring Boot

## Infos
Gabriel Henrique Leal Garcia - (Grupo 5)

Este projeto foi implementaod utilizando o banco de dados *H2*.
Os arquivos estão na raiz do projeto, portanto não é necessário se preocupar com configurações.  
Todos os end-points seguem o mesmo padrão da documentação.  
Um end-point para cadastro de usuários também foi adicionado ao projeto.

## Checkout para o projeto
Após clonar o projeto, altere para a *brach* `gabriel-henrique-leal-garcia`.

```
git checkout gabriel-henrique-leal-garcia
```

## Run
Para iniciar, abra o projeto no *IntelliJ*, aguarde ele instalar todas as dependências e clique em *Run*.

## Postman
Para importar a *collection* deste projeto em seu *Postman*, importe o arquivo `Requisitos.postman_collection.json`.  
Este arquivo se encontra na raiz do projeto.

## Swagger
Para documentar as *APIs* e facilitar os testes, este projeto está mapeado com o *Swagger*.  
Inicie o projeto e acesse [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html).

## Acessar banco de dados
Para acessar o banco dados, entre em: [`http://localhost:8080/h2-console`](http://localhost:8080/h2-console)

Driver Class: org.h2.Driver  
JDBC URL: jdbc:h2:./h2-database  
User Name: root  
Password: root  

## Registros já cadastrados
Usuários

id | userName | isSeller
--- | --- | ---
1 | cliente1 | `false`
2 | cliente2 | `false`
3 | cliente3 | `false`
4 | vendedor1 | `true`
5 | vendedor2 | `true`

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

## Testes automatizados
Para trazer uma experiência mais próxima do dia a dia, alguns testes de integração foram adicionados ao projeto.  
Os seguintes requisitos estão com os testes escritos:

Requisito | Teste escrito
--- | ---
US 0001 | ✅
US 0002 | ✅
US 0003 | ✅
US 0004 | ✅
US 0005 | ✅

## Itens extra-escopo
✅ Banco de dados  
✅ Swagger  
✅ Testes automatizados  
✅ Validação de dados  
✅ Tratamento de vários tipos de exções  
✅ End-point para cadastrar usuários  