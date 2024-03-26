FROM openjdk:17-jdk-slim AS builder
LABEL org.opencontainers.image.authors="Renan zazula"

WORKDIR application

ADD maven/${project.build.finalName}.jar ${project.build.finalName}.jar
RUN java -Djarmode=layertools -jar ${project.build.finalName}.jar extract

FROM openjdk:17-jdk-slim
LABEL PROJECT_NAME=${project.artifactId} \
      PROJECT=${project.id}

EXPOSE ${APP_DOCKER_PORT}

WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Dspring.profiles.active=docker","-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]