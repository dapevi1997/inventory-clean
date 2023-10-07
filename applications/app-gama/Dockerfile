FROM amazoncorretto:17-alpine
COPY build/libs/Inventory.jar app-gama.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app-gama.jar"]