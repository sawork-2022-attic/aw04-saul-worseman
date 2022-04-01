
FROM openjdk:11
COPY target/webpos-0.0.1-SNAPSHOT.jar /webpos/
EXPOSE 8080
ENTRYPOINT java -jar /webpos/webpos.jar

# build on the top of Java image
