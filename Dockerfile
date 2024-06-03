FROM eclipse-temurin:21.0.3_9-jre-ubi9-minimal

EXPOSE 8080

RUN mkdir /app

COPY build/libs/*.jar /app/marsExploration.jar

ENTRYPOINT ["java", "-jar", "/app/marsExploration.jar"]