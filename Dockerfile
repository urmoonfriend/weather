FROM openjdk:21-jdk-slim
EXPOSE 8082
ADD target/weather.jar weather.jar
ENTRYPOINT ["java", "-jar", "/weather.jar"]
ENV TZ="Asia/Almaty"
ENV SPRING_MAIN_BANNER-MODE=off
ENV SERVER_SHUTDOWN=graceful

