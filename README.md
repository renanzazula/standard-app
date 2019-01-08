[![CircleCI](https://circleci.com/bb/zazularenan/standard-app.svg?style=svg)](https://circleci.com/bb/zazularenan/standard-app)

[![codecov](https://codecov.io/bb/zazularenan/standard-app/branch/master/graph/badge.svg?token=eCbbvVVqpe)](https://codecov.io/bb/zazularenan/standard-app)

[![quality_gate](https://sonarcloud.io/api/project_badges/quality_gate?project=standard-app)](https://sonarcloud.io/api/project_badges/quality_gate?project=standard-app)

[![metric_bugs](https://sonarcloud.io/api/project_badges/measure?project=standard-app&metric=bugs)](https://sonarcloud.io/api/project_badges/measure?project=standard-app&metric=bugs)

[![code_smells](https://sonarcloud.io/api/project_badges/measure?project=standard-app&metric=code_smells)](https://sonarcloud.io/api/project_badges/measure?project=standard-app&metric=code_smells)

[![coverage](https://sonarcloud.io/api/project_badges/measure?project=standard-app&metric=coverage)](https://sonarcloud.io/api/project_badges/measure?project=standard-app&metric=code_smells)

[![security_rating](https://sonarcloud.io/api/project_badges/measure?project=standard-app&metric=security_rating)](https://sonarcloud.io/api/project_badges/measure?project=standard-app&metric=code_smells)

[![vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=standard-app&metric=vulnerabilities)](https://sonarcloud.io/api/project_badges/measure?project=standard-app&metric=vulnerabilities)

Standard-app

From Docker image at centos 



 - To build "docker build -t spring-boot-docker ." with dot
 - To running "docker run -d -p 8080:8080 spring-boot-docker"
 
 
 
 #FROM openjdk:8-jre-alpine
 ##TODO When changing to jdk10/11, add the "--add-modules java.xml.bind" to the CMD or correspondent gradle dependency, otherwise errors will occur
 #MAINTAINER Crealogix AG
 #ADD build/libs/clx-payments-server-*-boot.war /app.war
 #EXPOSE 9090
 #EXPOSE 8788
 #CMD java -Dspring.profiles.active=$ACTIVE_SPRING_PROFILE -agentlib:jdwp=transport=dt_socket,address=0.0.0.0:8788,server=y,suspend=n -Djava.security.egd=file:/dev/./urandom -jar /app.war
 FROM openjdk:11-browsers-legacy
 COPY clx-payments-server/build/libs/*-boot.war /app.war
 EXPOSE 9090
 ENTRYPOINT ["sh", "-c", "java --add-modules java.xml.bind -jar -Dspring.profiles.active=$ACTIVE_SPRING_PROFILE app.war"]
