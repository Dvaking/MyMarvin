FROM jenkins/jenkins:lts

WORKDIR /var/jenkins_home

USER root
RUN apt-get update && apt-get install build-essential libcriterion-dev -y
USER jenkins

COPY ./plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

COPY job_dsl.groovy /var/jenkins_home/job_dsl.groovy

ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yaml
COPY ./my_marvin.yml /var/jenkins_home/casc.yaml

EXPOSE 8080
EXPOSE 50000
