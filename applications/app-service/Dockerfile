FROM amazoncorretto:17-alpine
COPY build/libs/Inventory.jar app-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app-service.jar"]