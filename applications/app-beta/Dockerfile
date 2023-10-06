FROM amazoncorretto:17-alpine
COPY build/libs/Inventory.jar app-beta.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app-beta.jar"]