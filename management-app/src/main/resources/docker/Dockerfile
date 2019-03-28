FROM tomcat:8.5-alpine
VOLUME /tmp
COPY management-app-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/app.war
RUN sh -c 'touch /usr/local/tomcat/webapps/app.war'
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.cloud.config.uri=http://cloudconfigserver:8888 -Dspring.profiles.active=production -jar /usr/local/tomcat/webapps/app.war"]