FROM maven:3.8-openjdk-11-slim as DEPENDENCIES
WORKDIR /opt/app
COPY pom.xml .
RUN mvn -B -e -C clean org.apache.maven.plugins:maven-dependency-plugin:3.3.0:go-offline

# Copy the dependencies from the DEPS stage with the advantage
# of using docker layer caches. If something goes wrong from this
# line on, all dependencies from DEPS were already downloaded and
# stored in docker's layers.
FROM maven:3.8-openjdk-11-slim as BUILDER
WORKDIR /opt/app
COPY --from=DEPENDENCIES /root/.m2 /root/.m2
COPY src /opt/app/src
COPY pom.xml .
RUN mvn -B -e -o clean package -DskipTests=true

FROM azul/zulu-openjdk-alpine:11-jre-headless
WORKDIR /opt/app
COPY --from=BUILDER /opt/app/target/lab-spring-boot-docker.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "lab-spring-boot-docker.jar"]