FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_PROFILES_ACTIVE=""
CMD java -jar -D=spring.profiles.active=${SPRING_PROFILES_ACTIVE} "/app.jar"
#ENTRYPOINT java -jar -D=spring.profiles.active=${PROFILES} "/app.jar"