FROM openjdk:17-oracle
EXPOSE 8081:8081
ARG JAR_FILE=./exposition*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar","-DCONFIG_PATH=/conf_app/ideflix_app_docker.properties","/app.jar"]
