# -------- BUILD STAGE --------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copier uniquement les fichiers Maven pour optimiser le cache
COPY pom.xml .
COPY src ./src

# Compiler le projet et construire le jar
RUN mvn clean package -DskipTests

# -------- RUNTIME STAGE --------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copier le JAR depuis le premier stage
COPY --from=build /app/target/*.jar app.jar
# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
