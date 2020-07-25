FROM maven:3.6.3-openjdk-8 AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn clean install

FROM openjdk:7
EXPOSE 8080
WORKDIR /app
COPY --from=build /app/target/curriculum-jsf12.war .
COPY recursos_importantes recursos_importantes
COPY wait-for-it.sh .
COPY entrypoint.sh .
ADD https://download.jboss.org/jbossas/6.1/jboss-as-distribution-6.1.0.Final.zip .
RUN chmod +x ./wait-for-it.sh && \
    chmod +x ./entrypoint.sh && \ 
    mkdir server && \  
    unzip jboss-as-distribution-6.1.0.Final.zip -d server && \
    ls server/ && \
    cp recursos_importantes/mysql-connector-java-5.1.49.jar server/jboss-6.1.0.Final/server/default/lib && \
    cp recursos_importantes/curriculum-ds.xml server/jboss-6.1.0.Final/server/default/deploy && \ 
    cp recursos_importantes/login-config.xml server/jboss-6.1.0.Final/server/default/conf && \ 
    cp curriculum-jsf12.war server/jboss-6.1.0.Final/server/default/deploy && \
    rm -rm jboss-as-distribution-6.1.0.Final.zip && \
    rm -rf recursos_importantes && \
    rm curriculum-jsf12.war
ENTRYPOINT ./entrypoint.sh
