#FROM maven:3.8.4-openjdk-17-slim AS builder
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src

FROM maven:3.8.4-openjdk-17-slim
EXPOSE 8080
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]