FROM maven:3.6.3-openjdk-8 AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn clean install

FROM openjdk:7
ENV DATABASE_HOST=database
ENV DATABASE_NAME=curriculo_db
ENV DATABASE_USER=curriculum
ENV DATABASE_PASSWORD=curriculum
ENV DATABASE_PORT=3306
VOLUME [ "/app/logs" ]
WORKDIR /app
COPY --from=build /app/target/curriculum-jsf12.war .
COPY arquivos_docker arquivos_docker
ADD https://download.jboss.org/jbossas/6.1/jboss-as-distribution-6.1.0.Final.zip .
RUN cp arquivos_docker/entrypoint.sh . && \
    cp arquivos_docker/wait-for-it.sh . && \
    chmod +x ./wait-for-it.sh && \
    chmod +x ./entrypoint.sh && \ 
    mkdir logs && \
    mkdir server && \  
    unzip jboss-as-distribution-6.1.0.Final.zip -d server && \
    cp arquivos_docker/mysql-connector-java-5.1.49.jar server/jboss-6.1.0.Final/server/default/lib && \
    cp arquivos_docker/curriculum-ds.xml server/jboss-6.1.0.Final/server/default/deploy && \ 
    cp arquivos_docker/login-config.xml server/jboss-6.1.0.Final/server/default/conf && \ 
    cp curriculum-jsf12.war server/jboss-6.1.0.Final/server/default/deploy && \
    rm -rf jboss-as-distribution-6.1.0.Final.zip && \
    rm -rf arquivos_docker && \
    rm curriculum-jsf12.war
EXPOSE 8080
ENTRYPOINT ./entrypoint.sh
