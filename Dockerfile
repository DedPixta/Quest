FROM maven:3.9.0-amazoncorretto-17 AS build

# Copy pom.xml to the image
COPY pom.xml /home/app/pom.xml
RUN mvn -f /home/app/pom.xml verify clean --fail-never

# Copy the source code
COPY src /home/app/src
RUN mvn -f /home/app/pom.xml package

# Install Tomcat    & jdk 11
FROM tomcat:10.1.7-jdk17-temurin

# Copy source files to tomcat folder structure
COPY --from=build /home/app/target/Quest-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/quest.war

EXPOSE 8080

CMD ["catalina.sh", "run"]