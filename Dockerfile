FROM openjdk:11
MAINTAINER Renan Zazula
ADD target/standard-app-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
EXPOSE 8788
CMD java -Dspring.profiles.active=local -agentlib:jdwp=transport=dt_socket,address=0.0.0.0:8788,server=y,suspend=n -Djava.security.egd=file:/dev/./urandom -jar /app.jar
