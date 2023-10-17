FROM amazoncorretto:17-alpine
ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_NAME=prueba1
ENV DB_USERNAME=root
ENV DB_PASSWORD=d04m07a1997
ENV RABBITMQ_HOST=rabbitmq
ENV MONGODB_HOST=mongodb
ENV MONGODB_PORT=27017
ENV MONGODB_DATABASE=test3
COPY build/libs/Inventory.jar app-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app-service.jar"]