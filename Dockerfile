FROM openjdk:21-jdk-slim
ADD ./build-jar/task-tracker-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "task-tracker-0.0.1-SNAPSHOT.jar"]