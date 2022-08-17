#
# Package stage
#
FROM openjdk:8-jre-alpine
COPY target/api.gateway-0.0.1.jar /usr/local/lib/run.jar

ENV EUREKA_URI=http://localhost:8761/eureka
ENV REDIS_HOST="localhost"
ENV REDIS_PORT="6379"
ENV DATASOURCE_JDBC="jdbc:postgresql://localhost:5432/eletra_integration_db"
ENV DATASOURCE_USERNAME="postgres"
ENV DATASOURCE_PASSWORD="postgres"

RUN apk add --no-cache tzdata
ENV TZ America/Fortaleza

ENTRYPOINT ["java","-jar", "/usr/local/lib/run.jar"]
