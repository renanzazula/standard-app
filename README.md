[![CircleCI](https://dl.circleci.com/status-badge/img/gh/renanzazula/standard-app/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/renanzazula/standard-app/tree/master)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=renanzazula_standard-app&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=renanzazula_standard-app)

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=renanzazula_standard-app)](https://sonarcloud.io/summary/new_code?id=renanzazula_standard-app)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=renanzazula_standard-app&metric=bugs)](https://sonarcloud.io/summary/new_code?id=renanzazula_standard-app)

Standard-app

From the Docker image at Centos.
 - To build "docker build -t spring-boot-docker ." with dot
 - To run "docker run -d -p 8080:8080 spring-boot-docker"

From the maven command line profile local  
mvnw spring-boot:run -Dmaven.test.skip=true -Dspring-boot.run.profiles=local
