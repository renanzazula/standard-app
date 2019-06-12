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

from maven command line profile local  
mvnw spring-boot:run -Dmaven.test.skip=true -Dspring-boot.run.profiles=local
