# Use a base image with Java runtime for JDK 20
FROM openjdk:20-ea-8-jdk-oracle

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file (assuming the JAR is already built using Maven or Gradle)
COPY target/generator-0.0.1-SNAPSHOT.jar /app/generator-0.0.1-SNAPSHOT.jar

# Expose the port that the Spring Boot application is listening on
EXPOSE 8080

# Define the entry point for the container, starting the Spring Boot application
ENTRYPOINT ["java", "-jar", "generator-0.0.1-SNAPSHOT.jar"]
