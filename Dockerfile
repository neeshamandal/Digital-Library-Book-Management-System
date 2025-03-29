# Use Maven to build the application
FROM maven:3.8.1-openjdk-17-slim AS builder

WORKDIR /app
COPY . /app

# Run Maven to build the application
RUN mvn clean package

# Now copy the jar from the builder image
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the jar file from the builder image
COPY --from=builder /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
