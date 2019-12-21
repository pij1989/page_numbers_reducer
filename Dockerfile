# Base image
FROM tomcat:9-jdk11
# Copy from build context assembled web archive in directory Tomcat
COPY target/page-numbers-reducer-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/pagenumbersreducer.war

WORKDIR /usr/local/tomcat
# Open port 8080
EXPOSE 8080