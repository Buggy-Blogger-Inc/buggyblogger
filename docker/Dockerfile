FROM tomcat:jre8
MAINTAINER Harlau (CMAD-Team-6)
RUN \
rm -rf /opt/tomcat/webapps/examples && \
rm -rf /opt/tomcat/webapps/docs && \
rm -rf /opt/tomcat/webapps/ROOT && \
rm -rf /opt/tomcat/webapps/host-manager && \
rm -rf /opt/tomcat/webapps/manager
COPY cmad.buggyblogger.war /usr/local/tomcat/webapps/cmad.war
