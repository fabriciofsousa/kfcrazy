FROM maven:3.9.8-amazoncorretto-17-al2023 AS build

WORKDIR /app

COPY . /app

RUN mvn clean package

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/kycrazy-1.0.0.jar /app/kycrazy.jar

EXPOSE 8080

CMD ["java", "-jar", "kycrazy.jar"]
