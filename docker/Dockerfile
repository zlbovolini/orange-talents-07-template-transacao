# RUN
FROM openjdk:11-jre-slim

MAINTAINER Lucas Bovolini lbovolini94@gmail.com

ENV APP_NAME=transactions-search
ENV APP_FOLDER=~/transactions-search

RUN addgroup transactions && adduser --ingroup transactions transactions
USER transactions:transactions

WORKDIR ${APP_FOLDER}

COPY "./target/${APP_NAME}*.jar" ${APP_NAME}.jar

EXPOSE 8080

ENTRYPOINT java -jar ${APP_NAME}.jar
