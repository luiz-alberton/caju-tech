# Desafio técnico Caju #

---

Instruções do desafio podem ser encontradas no link: 
[Desafio tecnico para fazer em casa](https://caju.notion.site/Desafio-T-cnico-para-fazer-em-casa-218d49808fe14a4189c3ca664857de72) 

O projeto está configurado com o *spring-boot docker compose*, então a imagem do banco vai ser baixada ao iniciar a aplicação.

Para executar a aplicação:
`mvn spring-boot:run`

O esquema e dados iniciais do banco de dados da aplicação estão na pasta: `resources`


Com relação a L4 foi implementado um validação de Lock otimista. Para isso foi criado o campo `balance->version` que valida a versão em caso de concorrência.
