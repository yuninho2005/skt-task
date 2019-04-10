FROM tomcat:8.5-alpine
COPY cloud-config-server-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/app.war
RUN sh -c 'touch /usr/local/tomcat/webapps/app.war'
CMD ["sh", "-c", "java -Xmx512m -Djava.security.egd=file:/dev/./urandom -Dserver.port=$PORT -jar /usr/local/tomcat/webapps/app.war"]