FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S raguser && adduser -S raguser -G raguser
USER raguser
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]