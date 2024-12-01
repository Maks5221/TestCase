FROM maven:3.9.9-eclipse-temurin-21 AS build
COPY src /app/src
COPY mvnw pom.xml /app/
WORKDIR /app
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/*.jar /app/*.jar
CMD ["java", "-jar", "/app/*.jar"]