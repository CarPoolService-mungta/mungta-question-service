FROM openjdk:12-jdk-alpine AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN dos2unix ./gradlew
RUN ./gradlew bootJAR

FROM openjdk:12-jdk-alpine
COPY --from=builder build/libs/*.jar app.jar

ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]