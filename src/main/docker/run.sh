#!/bin/sh

echo "********************************************************"
echo "Starting Supply Service on port $SERVER_PORT"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/supplyservice/@project.build.finalName@.jar
