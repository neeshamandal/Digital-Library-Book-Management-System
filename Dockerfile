FROM eclipse-temurin:17-jdk as builder
WORKDIR /app
COPY . .
RUN chmod +x mvnw && \
    ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Explicit port exposure (required by Render)
EXPOSE 8080

# Health check with timeout adjustment
HEALTHCHECK --interval=30s --timeout=10s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
