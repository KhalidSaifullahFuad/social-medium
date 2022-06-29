FROM tomcat:9
RUN rm -rf /usr/local/tomcat/webapps/*

EXPOSE 8080:8080

COPY ./target/social-medium-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh","run"]
