FROM openjdk:17-alpine
COPY build/libs/Inventory.jar Inventory.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "Inventory.jar"]