FROM openjdk:17-jdk-alpine
MAINTAINER kaimingwan
COPY techwandering-test/target/techwandering-test.jar techwandering-test.jar
ENTRYPOINT ["java","-jar","techwandering-test.jar"]