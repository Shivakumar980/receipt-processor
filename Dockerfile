# Use an official OpenJDK runtime as a base image
FROM openjdk:17-jdk-slim AS build

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY . .

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use a minimal Java runtime for the final image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the previous build stage
COPY --from=build /app/target/receipt_processor-0.0.1-SNAPSHOT.jar /app/receipt-processor.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "/app/receipt-processor.jar"]
