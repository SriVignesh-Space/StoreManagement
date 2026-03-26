FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY ./target/VinylBackend.jar VinylBackend.jar

EXPOSE 8080
    
ENTRYPOINT [ "java",  "-jar", "VinylBackend.jar"]