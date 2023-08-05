FROM    openjdk:17
WORKDIR opt/ForJob
COPY target/ForJob-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]