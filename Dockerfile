FROM openjdk:11
MAINTAINER Renan Zazula

RUN mkdir -p /standard-app/
RUN mkdir -p /var/www/app

RUN git clone -b master https://renanzazula@bitbucket.org/zazularenan/standard-app.git /standard-app/
RUN cp -R /standard-app/* /var/www/app
RUN chown app:app -R /var/www/app

ADD /var/www/app/app.jar /app.jar
EXPOSE 8080
EXPOSE 8788
CMD java -Dspring.profiles.active=local -agentlib:jdwp=transport=dt_socket,address=0.0.0.0:8788,server=y,suspend=n -Djava.security.egd=file:/dev/./urandom -jar /app.jar