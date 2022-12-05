FROM eclipse-temurin:8-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src

ENTRYPOINT ["./mvnw", "spring-boot:run"]

# ENTRYPOINT ["java","-jar","/app.jar"]
# CMD ["./mvnw", "spring-boot:run"]
