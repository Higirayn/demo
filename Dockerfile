FROM openjdk:23-ea-17-jdk-bullseye
WORKDIR /app
COPY /build/libs/demo-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]