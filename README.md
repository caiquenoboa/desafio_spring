# Desafio Spring Boot

## Descrição
O objetivo deste desafio é aplicar os conteúdos dados até o momento durante o Programa de aceleração MeLi 
(Git, Java e Spring), com a finalidade de poder implementar uma API REST a partir de um enunciado proposto, uma 
especificação de requisitos e documentação anexada.

## Endpoints / Requisitos Implementados

Para executar a API, basta executar o projeto no IntelliJ e testar os endpoints fornecidos:
* **US0000 -** Criar usuário à partir de um JSON no formato *{ userName: "String", isSeller: boolean }* **( /users )**;
* **US0001 -** Seguir um usuário **( /users/{userId}/follow/{userIdToFollow} )**;
* **US0002 -** Retornar número de seguidores de um vendedor **( /users/{userId}/followers/count/ )**;
* **US0003 -** Listar informações dos seguidores de um vendedor **( /users/{userId}/followers/list )**;
* **US0004/8 -** Listar vendedores que um usuário segue **( /users/{UserID}/followed/list?order=name_asc )**;
* **US0005 -** Cadastrar uma publicaçāo **( /products/newpost )**;
* **US0006/9 -** Listar últimas publicações dos vendedores que um usuário segue **( /products/followed/{userId}
  /list?order=date_asc )**;
* **US0007 -** Deixar de seguir um vendedor **( /users/{userId}/unfollow/{userIdToUnfollow} )**;
* **US0010 -** Cadastrar uma publicação promocional **( /products/newpromopost )**;
* **US0011 -** Listar a quantidade de publicações promocionais de um vendedor **( /products/{userId}/countPromo/ )**;
* **US0012 -** Listar produtos promocionais de um vendedor **( /products/{userId}/list/ )**.


## Documentação (Swagger)

A documentação da API pode ser visualizada através do Swagger em:
```
http://localhost:8080/swagger-ui.html
```


## Testes (Postman)

Foi criado uma coleção de testes no postman. Ela pode ser importada na ferramenta à partir do **arquivo localizado em 
"*src/main/resources/testes.postman_collection.json*"**.


## Informações adicionais

* O projeto foi desenvolvido utilizando lombok (esta ferramenta fornece anotaçōes para evitar a necessidade de
  implementar getters, setters e construtores);
* Para usuário e vendedor, foi implementado o conceito de herança, por possuírem atributos e comportamentos distintos;
* As publicaçōes promocionais estendem as publicaçōes normais, porém, por terem apenas dois atributos distintos
  (*hasPromo* e *discount*), no banco de dados elas foram tratadas em apenas uma tabela;
* Os atributos *post_id* e *product_id* não precisam ser informados para cadastrar uma publicação (US0005), visto que
  o banco ficará responsável por gerenciar os id`s;
* Caso a data de uma publicação não seja informada, a data atual será utilizada;
* Foram implementadas exceções caso um usuário tente seguir ele mesmo, caso um usuário ou vendedor tente seguir outro
  usuário, caso um usuário tente seguir alguém que já está seguindo, caso um usuário tente deixar de seguir alguem que
  ele não segue, caso uma publicação promocional tenha hasPromo como false ou desconto igual a 0, ou caso uma publicação
  normal tenha hasPromo como true ou tenha algum valor de desconto;


## Banco de dados (JPA / Hibernate / h2)

O banco de dados pode ser acessado no link:
```
http://localhost:8080/h2-console
```

As credenciais a serem utilizadas são:

* **Usuario:** mercadolivre
* **Senha:** não há senha para acesso, este campo deve ficar em branco.

<h3>Tabelas com os dados já cadastrados</h3>

**OBS:** Vale ressaltar que a tabela SELLER é composta por apenas uma coluna com os id`s de quem é vendedor 
(por isso ela não será demonstrada aqui) e de acordo com os dados já cadastrados no banco, **paulacorreia e lindivoraz 
são usuários, e paulacorreia e calabrezafe são vendedoras**. A tabela SELLER seria mais útil caso o vendedor tivesse mais 
características distintas, se comparado à um usuário. Além disso, foi admitido que um post possui um único produto.

**Tabela USER**

ID | USER_NAME |
--- | --- | 
1 | joteixera
2 | lindivoraz
3 | paulacorreia
4 | calabrezafe

**Tabela POST**

ID | CATEGORY | DATE | DISCOUNT | HAS_PROMO | PRICE | PRODUCT_ID | SELLER_ID
--- | --- | --- | --- | --- | --- | --- | ---
1 | 100 | 2021-04-29 | 0.0 | FALSE | 1500.5 | 1 | 3
2 | 50 | 2021-05-29 | 0.0 | FALSE | 1000.0 | 2 | 3
3 | 20 | 2021-05-01 | 0.0 | FALSE | 1700.3 | 3 | 3
4 | 100 | 2021-05-01 | 0.0 | FALSE | 2000.4 | 4 | 4
5 | 60 | 2021-05-28 | 20.0 | TRUE | 2500.5 | 5 | 3
6 | 60 | 2021-06-01 | 30.0  | TRUE | 1000.5 | 6 | 3
7 | 120 | 2021-06-05 | 50.0 | TRUE | 10000.0 | 7 | 3

**Tabela PRODUCT**

ID | BRAND | COLOR | NOTES | PRODUCT_NAME | TYPE
--- | --- | --- | --- | --- | --- 
1 | Racer | Red & Black | Special Edition | Cadeira Gamer | Gamer
2 | Nike | Preta | Boa pra trabalhar | Cadeira Gigante | Personal
3 | Adidas | Amarela | Boa pra fazer festa | Mesa Redonda | Personal
4 | Kabum | Preto | Bom pra jogar | Mouse Redondo | Gamer 
5 | Nivia | Branca | King Size | cama box | Acessorio de casa
6 | Soundfier | Marrom | Standard  | Caixa de som | Gamer
7 | Apple | Cinza | Standard | Macbook | Gamer

**Tabela FOLLOWERS**

ID | USER_NAME |
--- | --- | 
3 | 4
1 | 3
1 | 4
