FROM openjdk:13
EXPOSE 8080
ARG JAR_FILE=target/InvetoryAPI-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} InvetoryAPI.jar 
ENTRYPOINT ["java","-jar","/InvetoryAPI.jar"]