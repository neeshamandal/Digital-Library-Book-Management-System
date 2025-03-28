# Build stage (Java 21 with Maven)
FROM eclipse-temurin:21-jdk as builder

WORKDIR /app

# 1. Copy build files first (better layer caching)
COPY .mvn .mvn
COPY mvnw pom.xml ./
# Fix permission and handle Java 21 preview features
RUN chmod +x mvnw && \
    ./mvnw --batch-mode dependency:go-offline -Djavac.src.version=21 -Djavac.target.version=21

# 2. Copy source code and build
COPY src src
RUN ./mvnw --batch-mode clean package -DskipTests -Djavac.src.version=21 -Djavac.target.version=21

# Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy SPECIFIC jar name (no wildcards for reliability)
COPY --from=builder /app/target/library-management-system-0.0.1-SNAPSHOT.jar app.jar

# Health check for production
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Explicitly enable preview features for Java 21
ENTRYPOINT ["java", "--enable-preview", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
