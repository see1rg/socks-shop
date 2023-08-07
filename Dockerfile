FROM    openjdk:17.0.2-slim


WORKDIR opt/ForJob
COPY target/ForJob-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
