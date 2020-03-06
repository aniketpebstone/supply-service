# Introduction
TUSLA Supply service, Concept 1.  Concept 1 introduces know-how of creating a Microservice on PCF platform.

1.  A supply service that will manage tire supply of TUSLA from abroad.

# Software needed
1. [Oracle Jdk] (hhttp://www.oracle.com/technetwork/java/javase) 1,8 JDK from Oracle
2. [Apache Maven] (http://maven.apache.org)
3. [Docker] (http://docker.com)

# Building the Docker Image
To build the code for supply service for Concept 1 as a docker image, open a command-line window change to the directory where you have the source code.

Run the following maven command.  This command will execute the [Spotify docker plugin](https://github.com/spotify/docker-maven-plugin) defined in the pom.xml file.  
```
mvn clean package docker:build
```
Running the above command at the root of the project directory will build the project. If everything builds successfully you should see a message indicating that the build was successful.

# Running the service locally with Docker
Now you may use docker-compose to start the actual image. To start the docker image, change to the docker-compose directory in the source code. Issue the following docker-compose command:
```
docker-compose -f docker/common/docker-compose.yml up
```
If everything starts correctly you should see a bunch of Spring Boot information fly by on standard out. At this point the service will be running.

# Running the service on PCF
Now you may use cf push to deploy and start the service. To deploy the microservice, change to the parent directory in the source code then login to PCF platform with the following cf command:
```
cf login -a https://api.local.pcfdev.io --skip-ssl-validation
```
https://api.local.pcfdev.io points to PCF Dev on local machine for PCF, change the URL to your target PCF installation.
   
deploy the stock service run the following cf command:
```
cf push
```
If cf pushes the microservice without any error, the service will be running on PCF. It will show up under the apps in the space you have pushed the microservice.

# Deploy the service with Concourse CI
