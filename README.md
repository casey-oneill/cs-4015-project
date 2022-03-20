# Book Swap

## Description
A web application for buying and selling used textbooks. Created for CS4015 / CS6075 / SWE4403 Course Project assignment.

API documents, please go to: 
http://localhost:8080/openapi/swagger-ui/index.html

## Project Setup
### Run the Project
- The bookstore project is wrapped into the docker image.
    -  To start the project, simply run the following commands: 
    ```aidl
    docker-compose up -d
    ```
    - To vew the log from docker:
    ```
    docker-compose logs -f
    ```
    - To stop the service:
    ``` 
    docker-compose stop
    ```
    - To update the book service in the image file:
  ```aidl
  docker-compose build book  
  ```

### For Developing
- Run the following command to intialize the MySQL container:
    ```aidl
    docker run -d -p 3306:3306  -e MYSQL_ROOT_PASSWORD=rootpwd -e MYSQL_DATABASE=bookstore_db -e MYSQL_USER=user -e MYSQL_PASSWORD=pwd --name local-mysql mysql:5.7.23
    ```

- Start the bookstore-service:
    ```aidl
    ./gradlew build 
    ./gradlew bootRun
    ```
    
