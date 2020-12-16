#### Greeter service
##### The default service port is 8080, default `day-time-resolver` dependency service endpoint is `http://localhost:8081`.
##### Start service:
`./gradlew run`
  
##### Start service with custom port and/or `day-time-resolver` service custom endpoint: 
`./gradlew run --args="--server.port=[greeter_port] --daytime.service.endpoint=[http://day-time_host:day-time_port]"`
___