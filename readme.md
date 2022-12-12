# Read Me First

## Pre-requisite
* jdk 17

## Building and running instructions
* Open a shell window
* change direction to base directory 'cake-manager'
* Execute './gradlew bootJar'
* Should produce 'cake-manager-0.0.1-SNAPSHOT.jar' under build/libs director
* Execute 'java -jar build/libs/cake-manager-0.0.1-SNAPSHOT.jar'
* The service should be up and bind on http://localhost:8080
* Alternatively executing './gradlew bootRun' should have the same effect

* The original package name 'com.waracle.cakemgr.cake-manager' is invalid and this project uses '
  com.waracle.cakemgr.cakemanager' instead.

# REST Endpoints

## /cakes
All the functional requirements related to cake CRUD operations are implemented following classic REST principals
### GET /cakes
Returns all the cakes currently in the system
### GET /cakes/{id}
Returns a single cake with specified id
### POST /cakes/{id}
Updates a specific cake with given id and payload as post body
### DELETE /cakes/{id}
Deletes a specific cake with the provided id
### POST /cakes
Creates a new cake

## /actuator
Actuator endpoint has been enabled using actuator starter. This will provide different
metrics to monitor the service.
* GET request on /actuator/health will show the system status
* Direct browser request on http://localhost:8080/actuator/health will also show application status
* More details on actuator can be found in https://docs.spring.io/spring-boot/docs/3.0.x/reference/htmlsingle/#actuator

#FIXME
#Feign Client
* CakeClient was introduced to call the endpoint https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json
* Unfortunately the endpoint returns the content as text and feign was having trouble parsing through that
#Tests
* Not enough test coverage
