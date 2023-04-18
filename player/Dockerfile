FROM openjdk:20

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# RUN ./mvnw dependency:go-offline
 
COPY src ./src

CMD ["./mvnw", "spring-boot:run"]