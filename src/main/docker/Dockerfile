FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:22.3-java11 AS build
COPY . /workspace
WORKDIR /workspace
RUN ./mvnw clean package -DskipTests

FROM quay.io/quarkus/quarkus-mandrel-image:22.3-java11
WORKDIR /work/
COPY --from=build /workspace/target/*-runner.jar /work/application.jar
EXPOSE 8080
CMD ["java", "-jar", "/work/application.jar"]