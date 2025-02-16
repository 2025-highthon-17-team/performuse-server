FROM amazoncorretto:17-alpine

WORKDIR /app

COPY . .

ENV JAR_PATH=build/libs/*.jar

ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active='${PROFILE}' -jar ${JAR_PATH}"]
