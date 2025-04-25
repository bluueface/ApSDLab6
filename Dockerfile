# Dockerfile

# Start from a Java runtime image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from target to app.jar inside container
COPY target/lab6-0.0.1-SNAPSHOT.jar app.jar

# Expose port (adjust if your app uses another one)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
