# Multi-stage Docker build for REST API Testing Framework

# Build stage
FROM maven:3.9.4-openjdk-17-slim AS build

# Set working directory
WORKDIR /app

# Copy pom.xml first for better layer caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean compile test-compile -B

# Runtime stage
FROM openjdk:17-jdk-slim AS runtime

# Install necessary tools
RUN apt-get update && apt-get install -y \
    curl \
    wget \
    mysql-client \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy built application from build stage
COPY --from=build /app/target ./target
COPY --from=build /app/src ./src
COPY --from=build /app/pom.xml .

# Copy Maven from build stage
COPY --from=build /usr/share/maven /usr/share/maven
RUN ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# Create directories for reports and logs
RUN mkdir -p /app/target/test-reports /app/logs

# Set environment variables
ENV MAVEN_HOME=/usr/share/maven
ENV MAVEN_CONFIG=/root/.m2

# Expose port for potential web reports
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:8080/health || exit 1

# Default command to run tests
CMD ["mvn", "test"]
