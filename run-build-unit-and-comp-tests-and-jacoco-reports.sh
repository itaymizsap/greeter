#!/bin/sh

mkdir -p "greeter/jacoco/unit-tests"
mkdir -p "greeter/jacoco/component-tests"
#mkdir -p "greeter/jacoco/merged"

rm -rf "greeter/jacoco/unit-tests/"
rm -rf "greeter/jacoco/component-tests/"
#rm -rf "greeter/jacoco/merged/"

mkdir -p "greeter/jacoco/unit-tests"
mkdir -p "greeter/jacoco/component-tests"
#mkdir -p "greeter/jacoco/merged"


./gradlew :day-time-resolver:clean :day-time-resolver:test :day-time-resolver:installDist -console plain || exit
./gradlew :greeter:clean :greeter:test :greeter:installDist -console plain || exit

docker-compose -f docker-compose-with_jacoco_agent.yml up --build --force-recreate -d

sleep 20

curl http://localhost:8070/greet/James

printf '\n'

docker stop -t 30 greeter || exit
printf '\n'
docker-compose -f docker-compose-with_jacoco_agent.yml down

cd greeter || exit

# UNIT-TESTS Jacoco REPORT
java -jar jacoco/org.jacoco.cli-0.8.6-nodeps.jar report jacoco/unit-tests/jacoco.exec \
--classfiles build/classes/java/main \
--sourcefiles src/main/java \
--xml jacoco/unit-tests/report.xml \
--html jacoco/unit-tests/html

# COMPONENT-TESTS Jacoco REPORT
java -jar jacoco/org.jacoco.cli-0.8.6-nodeps.jar report jacoco/component-tests/jacoco.exec \
--classfiles build/classes/java/main \
--sourcefiles src/main/java \
--xml jacoco/component-tests/report.xml \
--html jacoco/component-tests/html

# shellcheck disable=SC2039
bash <(curl -s https://codecov.io/bash) -t 276ee190-cce7-405f-99ab-8c71cf4acb89 -F unit-tests -f jacoco/unit-tests/report.xml

# shellcheck disable=SC2039
bash <(curl -s https://codecov.io/bash) -t 276ee190-cce7-405f-99ab-8c71cf4acb89 -F component-tests -f jacoco/component-tests/report.xml


# MERGED UNIT and COMPONENT-TESTS Jacoco REPORT
#java -jar jacoco/org.jacoco.cli-0.8.6-nodeps.jar report jacoco/unit-tests/jacoco.exec jacoco/component-tests/jacoco.exec \
#--classfiles build/classes/java/main \
#--sourcefiles src/main/java \
#--xml jacoco/merged/report.xml \
#--html jacoco/merged/html