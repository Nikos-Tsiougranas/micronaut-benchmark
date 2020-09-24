FROM adoptopenjdk/openjdk11:jdk-11.0.7_10-alpine-slim

EXPOSE 8080

COPY scripts/docker-entrypoint.sh .

RUN chmod a+x docker-entrypoint.sh

COPY build/libs/app-*-all.jar micronaut-benchmark.jar

ENTRYPOINT ["sh", "./docker-entrypoint.sh"]
