FROM openjdk:21-jdk-slim
EXPOSE 8082
ADD target/proxy.jar proxy.jar
ENTRYPOINT ["java", "-jar", "/proxy.jar"]
ENV TZ="Asia/Almaty"
ENV SPRING_MAIN_BANNER-MODE=off
ENV SERVER_SHUTDOWN=graceful

