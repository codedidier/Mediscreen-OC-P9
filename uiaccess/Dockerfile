FROM openjdk:8

ARG JAR_FILE=target/uiaccess-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8081


ENTRYPOINT ["java", "-jar", "app.jar"]