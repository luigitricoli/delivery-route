# Delivery Route

## Tecnologias

O projeto foi desenvolvido em Java7 utilizando as especificações Servelet3, JAX-RS, JAXB, CDI. Meu desenvolvimento foi feito no Tomcat7 então utilizei os frameworks Jersey, MOXy, Weld, para suportar as especificações.

Como base de dados optei por utilizar o SQLite3 por se tratar de um projeto conceitual, não voltando para produção, e também por ser auto contido, não necessitando configurações específicas.

Escolhi o Maven como gerenciador de dependências e release.

## Design 

O design que escolhi para implementar o projeto foi utilizar o modelo MVC e conceitos de DDD. A escolhe do MVC é devido à facilidade de se trabalhar com esse modelo com o framework Jersey. Utilizei DDD ao implementar o conceitos e padrões: Respository, Entity, Aggregate.

Escolhi para resolver o problema proposto o [algoritmo de  Dijkstra](http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html) sem otimizações específicas para grafos mais ou menos densos ou filas priorizadas. 
Como os valores das distâncias são positivos a limitação do algoritmo de apenas utilizar números positivos para indexar as arestas não será um problema.

## Instalação

1. Clone o repositório master
2. Faça checkout de tags/tag 1.0-beta

### Se Tomcat7

3. Copie o arquivo ``dr_config.properties`` para o diretório TOMCAT_HOME/lib
4. Altere a propriedade ``dp``, do arquivo ``dr_config.properties``, para o diretório e nome do banco de dados
5. Execute ``mvn -Ptomcat-packaging package``
6. Faça deploy do artefato ``dr.war`` para o diretório TOMCAT_HOME/lib
7. Execute o Tomcat.
8. Acesse ip_tomcat:porta_tomcat/dr e visualize a mensagem: **DR funcionando...**

### Se outros

3. Coloque o arquivo ``dr_config.properties`` no CLASSPATH do servidor
4. Altere a propriedade ``dp``, do arquivo ``dr_config.properties``, para o diretório e nome do banco de dados
5. Execute ``mvn package``
6. Faça deploy do artefato ``dr.war`` no servidor
8. Acesse http://ip_tomcat:porta_tomcat/dr e visualize a mensagem: **DR funcionando...**

## Acesso

### Cadastrar um novo mapa

Envie uma requisição POST para ``http://ip_servidor:porta_servidor/dr/mapas`` com um JSON no formato abaixo:

```{
"mapa":"NOME_DO_MAPA",
"distancias":[
{"origem":"A","destino":"B","valor":4}, {"origem":"B","destino":"C","valor":3}
                                      ]
} ```

### Consultar um mapa

Envie uma requisição GET para ``http://ip_servidor:porta_servidor/dr/mapas/NOME_DO_MAPA``.

### Verificar custo de uma rota

Envie uma requisição GET para ``http://ip_servidor:porta_servidor/dr/rotas/NOME_DO_MAPA?origem=X&destino=Y&autonomia=0.0&valor_litro=0.0``.

