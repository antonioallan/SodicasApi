FROM openjdk:8-jre-alpine
LABEL maintainer="gilmariokpslow"
COPY ./target/sodicas-swarm.jar /usr/src/app.jar
WORKDIR /usr/src/
CMD java -jar app.jar -Djava.net.preferIPv4Stack=true -Dswarm.project.stage=docker
EXPOSE 8080