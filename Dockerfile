# Build stage (Maven + JDK 17)
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven descriptor first for better layer caching
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline

# Copy sources and build
COPY src src
RUN mvn -B -DskipTests package

# Runtime stage (JRE 17)
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Set JVM options for production
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
