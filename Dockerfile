# Estágio 1: Compilação do projeto com Maven
FROM openjdk:21-jdk-slim AS build
WORKDIR /workspace/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw install -DskipTests

# Estágio 2: Imagem final de execução, muito menor
FROM openjdk:21-jre-slim
WORKDIR /app

COPY --from=build /workspace/app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]