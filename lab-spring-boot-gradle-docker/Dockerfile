FROM gradle:7.6-alpine AS Cache
WORKDIR /opt/app
ENV GRADLE_USER_HOME /cache
COPY build.gradle ./
RUN gradle --no-daemon dependencies --stacktrace

FROM gradle:7.6-jdk11-alpine AS Builder
WORKDIR /opt/app
COPY --from=Cache /cache /home/gradle/.gradle
COPY build.gradle settings.gradle ./
COPY src /opt/app/src
RUN gradle --no-daemon build --stacktrace --offline

FROM azul/zulu-openjdk-alpine:11-jre-headless
WORKDIR /opt/app
COPY --from=Builder /opt/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]