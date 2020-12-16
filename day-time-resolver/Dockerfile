FROM openjdk:8-jre-alpine

WORKDIR /day-time-resolver/

# instal `curl`
RUN apk --no-cache add curl

# copy executable SystemTests install-distribution into image
COPY build/install/day-time-resolver .

# run application with this command line
ENTRYPOINT ["bin/day-time-resolver", "--server.port=${serverPort}"]