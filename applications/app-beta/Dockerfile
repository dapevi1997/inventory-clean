FROM openjdk:17-alpine
ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_NAME=prueba1
ENV DB_USERNAME=root
ENV DB_PASSWORD=d04m07a1997
ENV RABBITMQ_HOST=rabbitmq
COPY build/libs/Inventory.jar Inventory.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "Inventory.jar"]