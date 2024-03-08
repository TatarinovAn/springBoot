FROM openjdk:17
EXPOSE 8081
ADD target/springBootDemo-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]