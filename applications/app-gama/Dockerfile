FROM amazoncorretto:17-alpine
COPY build/libs/Inventory.jar app-gama.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app-gama.jar"]