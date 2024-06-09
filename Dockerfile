# Use OpenJDK 21 base image from the official image repository
FROM openjdk:21-jdk-slim as build
LABEL maintainer="455634038@qq.com"
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline
COPY src src
RUN ./mvnw package -DskipTests
FROM openjdk:21-jdk-slim
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
