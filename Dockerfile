FROM maven:3-eclipse-temurin-21-alpine AS builder
WORKDIR /app
COPY . .
RUN mvn -B package -DskipTests

FROM eclipse-temurin:21-jre-ubi9-minimal
RUN useradd raguser
USER raguser

COPY --from=builder /app/target/rag_chat_api.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]