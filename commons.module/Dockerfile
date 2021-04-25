#
# Build stage
#
FROM maven:3.6.0-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -Dmaven.repo.local=/.m2 -f /home/app/pom.xml clean install