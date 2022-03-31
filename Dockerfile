#FROM openjdk:11-ea-9-jdk
##CMD java -cp webpos.jar com.example.webpos.WebPosApplication.class
#COPY ./target/webpos-0.0.1-SNAPSHOT.jar webpos/webpos.jar
#EXPOSE 8080
#ENTRYPOINT java -cp webpos/webpos.jar com.example.webpos.WebPosApplication.class
#
#FROM openjdk:11
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:11
COPY target/webpos-0.0.1-SNAPSHOT.jar /webpos/
EXPOSE 8080
ENTRYPOINT java -jar /webpos/webpos.jar

# build on the top of Java image
