# Estágio 1: Compilação do projeto com Eclipse Temurin (JDK)
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw install -DskipTests

# Estágio 2: Imagem final de execução (JRE)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=build /workspace/app/target/*.jar app.jar

# A porta do Render é 10000 por padrão para Docker
EXPOSE 10000
ENTRYPOINT ["java","-jar","app.jar"]