FROM openjdk:11
MAINTAINER Renan Zazula

RUN git clone -b docker https://renanzazula@bitbucket.org/zazularenan/standard-app.git /standard-app/
RUN cp -R /standard-app/* /home/app/
RUN chown app:app -R /home/app/

ADD home/app/app.jar /app.jar
EXPOSE 8080
EXPOSE 8788
CMD java -Dspring.profiles.active=local -agentlib:jdwp=transport=dt_socket,address=0.0.0.0:8788,server=y,suspend=n -Djava.security.egd=file:/dev/./urandom -jar /app.jar