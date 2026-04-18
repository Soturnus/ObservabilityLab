# =========================
# 1. Build da aplicação
# =========================
FROM maven:3.9.14-eclipse-temurin-21 AS build

WORKDIR /app

# Cache de dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Códigow
COPY src ./src

# Build
RUN mvn clean package -DskipTests


# =========================
# 2. Runtime leve
# =========================
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "app.jar"]