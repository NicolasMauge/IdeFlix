FROM openjdk:17-oracle
EXPOSE 8080:8080
ARG JAR_FILE=./exposition*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar","-DCONFIG_PATH=/conf_iam/ideflix_iam_docker.properties","/app.jar"]
