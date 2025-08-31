FROM openjdk:17

WORKDIR /myAPP

COPY ./target/spring-crud-app.jar /myAPP

EXPOSE 8085

CMD ["java","-jar","spring-crud-app.jar"]