FROM maven:3.8.4-eclipse-temurin-17-alpine as build
WORKDIR /workspace/app
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn -Dpmd.skip=true -DskipTests package
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jdk-alpine
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","ru.r1b.calorietrackingservice.CalorieTrackingServiceApplication"]