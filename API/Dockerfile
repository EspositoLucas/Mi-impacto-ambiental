FROM eclipse-temurin:8-jdk-ubi9-minimal

WORKDIR /app

COPY target/*.jar app.jar
# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./
# RUN ./mvnw dependency:resolve

# COPY src ./src

ENTRYPOINT ["java","-jar","/app/app.jar"]

# ENTRYPOINT ["./mvnw", "spring-boot:run"]
# CMD ["./mvnw", "spring-boot:run"]
