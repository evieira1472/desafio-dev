#API Rest para importação de transações financeiras

Esta API tem por objetivo a importar e receber dados de transções financeiras realizadas por 
estabelecimentos.

#Ferramentas utilizadas:

- Maven (Para gerenciar as dependências do projeto);
- Spring framework (Para construção dos controllers, models, e views da aplicação);
- MariaDB (Banco de dados relacional gratuito baseado em mysql)
- Apache tomcat (Servidor da aplicação)

#Conceitos utilizados;

O projeto foi estruturado utilizando o conceito MVC onde se baseia em 3 camadas: Model, View e Controller.
O Model é responsável pela regra de negócio é onde contém as classes responáveis por representar as entidades,
realizar a abstração dos dados (services) e a persistência no banco de dados (Repository).
View é a parte de interface com o usuário.
E Controller é resposável por receber as requisições e destinar aos destino correto dentro da aplicação.

#Configurações


Para que seja possível iniciar a API é necessário instalar o banco de dados de sua preferência, importar o projeto maven em sua IDE de preferência, 
configurar o banco de dados e a porta do servidor através do arquivo "src/main/resources/aplication.properties", além do endereço do banco de dados, usuário 
e senha é necessário verifica o driver de conexão e o dialeto, pois o arquivo está configurado para utilizar o MariaDB.
Após o build do projeto e download das dependências a API estará pronta para o Run.

#Consumindo os endpoints:

Para acessar a interface de importação do arquivo basta apenas acessar o 
endereço padrão do servidor em que a a aplicação foi iniciada pelo navegador.
url:porta, por exemplo: localhost:8080 ou 192.168.3.128:8080
Após selecionar o arquivo será consumido outro serviço realizando a importação dos dados no banco de dados.

Importação do arquivo: Realizar uma requisição http com método "POST" para "localhost:8080/cnab" utilizando form-data e setando 
no body da requisição o atributo file.

Buscar todas as transações: Realizar uma requisição http com método "GET" para "localhost:8080/cnab", o resultado
da consulta será em formato JSON com o agrupamento das informações de cada empresa, já com o cálculo
do saldo em conta.

Buscar transações por nome da empresa: Realizar uma requisição http com método "GET" para "localhost:8080/cnab/loja/{nomeLoja}" onde "{nomeLoja}" deverá ser
substituido pela string com o nome da loja. O resultado
da consulta será em formato JSON com o agrupamento das transações realizadas, e o cálculo
do saldo em conta.




