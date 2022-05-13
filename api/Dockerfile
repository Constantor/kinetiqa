FROM gradle:jdk18 AS build
# MAINTAINER Stepan Konstantinov <stepan _AT_ konstantinov _DOT_ pl>
# LABEL maintainer="Stepan Konstantinov <stepan@konstantinov.pl>"

# RUN pacman -Syyu --needed --noconfirm jdk-openjdk kotlin gradle # openssh git maven base linux linux-firmware linux-headers

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM openjdk:18
EXPOSE 8080:80
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/ktor-docker-kinetiqa-api.jar
ENTRYPOINT ["java", "-jar", "/app/ktor-docker-kinetiqa-api.jar"]

# sudo docker build -t kinetiqa-api:latest .
# docker run -d -p 8080:80 kinetiqa-api:latest
