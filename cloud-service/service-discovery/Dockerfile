#
# Build stage
#
FROM maven:3.6.0-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -Dmaven.repo.local=/.m2 -f /home/app/pom.xml package

#
# Package stage
#
FROM openjdk:8-jre-alpine
COPY --from=build /home/app/target/cloud.discovery-0.0.1.jar /usr/local/lib/run.jar

ENV SPRING_PROFILES_ACTIVE=docker

RUN apk add --no-cache tzdata
ENV TZ America/Fortaleza

WORKDIR /
EXPOSE 8761
ENTRYPOINT ["java","-jar", "/usr/local/lib/run.jar"]
