# Curriculum JSF

Essa aplicação é um exemplo de aplicação utilizando Java EE 5.
* Java Server Faces 1.2
* EJB 3
* JPA com Hibernate
* MySQL
* JBoss AS 6

### Instalação da Ambiente

1. Instale o MySQL e crie um banco de dados chamado curriculo_db.
2. Faça o download do servidor em [https://jbossas.jboss.org/downloads](https://jbossas.jboss.org/downloads). É a versão JBoss AS 6.1.0.Final;
3. Descompacte em um local de sua preferência;
4. Copie o driver [mysql-connector-java-5.1.49.jar](recursos_importantes/mysql-connector-java-5.1.49.jar) no caminho <pasta_jboss>/server/default/lib;
5. Copie o arquivo [curriculum-ds.xml](recursos_importantes/curriculum-ds.xml) no caminho <pasta_jboss>/server/default/deploy;
6. Copie o conteudo do arquivo [login.xml](recursos_importantes/login.xml) dentro do arquivo login-config.xml no caminho <pasta_jboss>/server/default/conf;

### Instalação da Aplicação

1. Na pasta do projeto execute:
> mvn clean install
2. Copie o arquivo target/curriculum-jsf12.war para a pasta <pasta_jboss>/server/default/deploy;
3. Inicie o servidor

Acesse: http://localhost:8080/curriculum-jsf12/

Obs: A aplicação é velha mesmo. rsrs

