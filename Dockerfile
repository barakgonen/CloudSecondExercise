FROM adoptopenjdk/openjdk11:alpine-jre

ARG DEPENDENCY=build/libs/
COPY ${DEPENDENCY}/WebServer-0.0.1-SNAPSHOT.jar .
RUN pwd
RUN ls
ENTRYPOINT ["java", "-jar", "./WebServer-0.0.1-SNAPSHOT.jar"]