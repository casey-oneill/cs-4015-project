# cs-4015-project

## Description

## Project Setup
### Run the project.
- The bookstore project is rapped into the docker image.
    -  To start the project, just simply run the 
    ```
    docker-compose up 
    ```
    - To store the service:
    ``` 
    docker-compose down
    ```

### For Developing
- Run the following command to intial the mysql container.
    ```
    docker run -d -p 3306:3306  -e MYSQL_ROOT_PASSWORD=rootpwd -e MYSQL_DATABASE=bookstore_db -e MYSQL_USER=user -e MYSQL_PASSWORD=pwd --name local-mysql mysql:5.7.23
    ```

- Start the bookstore-service
    ```aidl
    ./gradlew build 
    ./gradlew bootRun
    ```