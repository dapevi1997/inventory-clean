FROM openjdk:17-alpine
COPY build/libs/Inventory.jar Inventory.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "Inventory.jar"]