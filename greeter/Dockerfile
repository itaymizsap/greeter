FROM openjdk:8-jre-alpine

WORKDIR /greeter/

# instal `curl`
RUN apk --no-cache add curl

# copy executable SystemTests install-distribution into image
COPY build/install/greeter .

# run application with this command line
ENTRYPOINT ["bin/greeter", "--server.port=${serverPort}", "--daytime.service.endpoint=http://${dayTimeHost}:${dayTimePort}"]

