FROM adoptopenjdk:11-jre-hotspot
WORKDIR /
ADD ./build/libs/*.war app.war
EXPOSE 8080
CMD java -jar app.war