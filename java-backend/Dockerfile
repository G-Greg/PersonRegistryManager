FROM openjdk:17-jdk-alpine

COPY --chown=185 target/PersonRegistryManager-0.0.1-SNAPSHOT.jar /PersonRegistryManager/app.jar

USER 185
EXPOSE 8080

CMD ["java", "-jar", "/PersonRegistryManager/app.jar"]