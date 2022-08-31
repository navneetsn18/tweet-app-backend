FROM openjdk:8
EXPOSE 8081
COPY target/tweet-app.jar tweet-app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","tweet-app.jar"] 