FROM tomcat
RUN rm -rf /usr/local/tomcat/webapps
COPY ./target/householder /usr/local/tomcat/webapps/ROOT
