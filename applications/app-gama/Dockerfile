FROM amazoncorretto:17-alpine
ENV RABBITMQ_HOST=rabbitmq
COPY build/libs/Inventory.jar Inventory.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "Inventory.jar"]