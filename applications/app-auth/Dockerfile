FROM openjdk:17-alpine
ENV MYSQL_SERVICE=localhost:3306
ENV DB_NAME=prueba1
ENV DB_USERNAME=root
ENV DB_PASSWORD=d04m07a1997
COPY build/libs/Inventory.jar Inventory.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "Inventory.jar"]