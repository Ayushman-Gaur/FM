# Use lightweight JDK
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy jar (make sure you ran: mvn clean package)
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]