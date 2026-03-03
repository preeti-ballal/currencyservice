# Use JDK 17 as the base image
FROM eclipse-temurin:17-jdk-alpine

# Set the directory inside the container
WORKDIR /app

# Copy the JAR file you just built into the container
# We rename it to 'app.jar' to keep it simple
COPY target/*.jar app.jar

# Tell the container to listen on port 8081
EXPOSE 8081

# The command to start the app
ENTRYPOINT ["java", "-jar", "app.jar"]