#
# Build stage
#
FROM lukkascost/data-analyze-commons AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -Dmaven.repo.local=/.m2 -f /home/app/pom.xml package

#
# Package stage
#
FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/sample.module-0.0.1.jar /usr/local/lib/run.jar
ENV SERVER_PORT 0

ENV API_GATEWAY_LOCATION=172.17.0.1:8080
ENV EUREKA_URI=http://localhost:8761/eureka

ENV BROKER_URL="localhost"
ENV BROKER_PORT="61616"
ENV BROKER_USER="admin"
ENV BROKER_PASSWORD="admin"

ENV REDIS_HOST="localhost"
ENV REDIS_PORT="6379"

ENV DATASOURCE_JDBC="jdbc:postgresql://localhost:5432/eletra_integration_db"
ENV DATASOURCE_USERNAME="postgres"
ENV DATASOURCE_PASSWORD="postgres"

ENV TZ 'America/Fortaleza'
RUN echo $TZ > /etc/timezone && \
    apt-get update && apt-get install -y tzdata && \
    rm /etc/localtime && \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && \
    dpkg-reconfigure -f noninteractive tzdata && \
    apt-get clean
WORKDIR /

ENTRYPOINT ["java","-jar","-Xmx1g", "/usr/local/lib/run.jar"]
