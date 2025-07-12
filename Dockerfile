FROM gradle:8.0.2-jdk17 AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN rm -rf /home/gradle/.gradle/caches
RUN chmod +x ./gradlew
RUN ./gradlew build --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]