FROM openjdk:latest
ADD ../build/libs/tuzello-0.0.1.jar /app/tuzello.jar
ENTRYPOINT ["java", "-jar","/app/tuzello.jar"]
EXPOSE 8080