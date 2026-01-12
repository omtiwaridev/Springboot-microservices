FROM eclipse-temurin:17-jre-alpine

WORKDIR /accounts

COPY build/libs/accounts-0.0.1-SNAPSHOT.jar accounts.jar

EXPOSE 8085

ENTRYPOINT ["java","-jar","accounts.jar"]

