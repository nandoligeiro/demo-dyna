FROM eclipse-temurin:17-jdk-alpine

# Instalar ferramentas necessárias
RUN apk add --no-cache curl

WORKDIR /app
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Script de entrada modificado para lidar melhor com o Dynatrace
COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]
