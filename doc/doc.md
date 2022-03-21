# **Course Project Report**

# Table of Contents
- [**Course Project Report**](#course-project-report)
- [Table of Contents](#table-of-contents)
- [Explanation of Software](#explanation-of-software)
  - [Overview](#overview)
  - [Implementation](#implementation)
- [Four Design Patterns](#four-design-patterns)
  - [Facade Pattern](#facade-pattern)
    - [Problem](#problem)
    - [Solution](#solution)
    - [Implementation](#implementation-1)
  - [Adapter Pattern](#adapter-pattern)
    - [Problem](#problem-1)
    - [Solution](#solution-1)
    - [Implementation](#implementation-2)
  - [Factory Pattern](#factory-pattern)
    - [Problem](#problem-2)
    - [Solution](#solution-2)
    - [Implementation](#implementation-3)
- [Contributions](#contributions)

# Explanation of Software
## Overview
We have used Java frameworks to develop a fully functioning web application for buying and selling used textbooks leveraging a microservices architecture. Our application is complete with:
- REST endpoints
- A professional user interface
- Persistent data
- User login and authentication

The key technologies that we are using are:
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL
- Java Servlet Faces (configured and integrated with PrimeFaces and Bootstrap using the AdminFaces template)

These frameworks – in particular, the Spring* frameworks – incorporate a variety of design patterns in order to provide functionality for our application. Working with these technologies has helped all of us to better understand and observe real-world usage and good implementation of design patterns. By leveraging these frameworks and, by extension, the good design patterns and practices used by them, we seek to create an extensible, reusable, and maintainable web application.

## Implementation
1. User Interface Layer
     - `/book-service/.../java/.../bean/*MB.java` classes: leverage JSF framework to provide information and functionality to user interface screens.
     - `/book-service/.../webapp/*.xhtml` files: define user interface screens using a combination of plain HTML and [PrimeFaces](https://www.primefaces.org/showcase/index.xhtml?jfwid=71d71) elements.
2. Database Persistence Layer
    - `/book-service/.../java/.../core/*` folders: contain data persistence configuration for `book` and `user` entities. The structure and implementation of each package (book and user) are identical.
    - `/book-service/java/.../core/.../model/*Entity.java` classes: use Spring Boot JPA annotation to define database persistence entity classes. The `@Table` annotation declares which DB table each entity reflects.
    - `/book-service/java/.../core/.../repository/*Repository.java` classes: use Spring Boot JPA framework to provide access to the information stored in the database. Basic CRUD operations are automatically implemented by extending the `JpaRepository` class. Additional functionality (ex. get users by username) is given by defining additional methods which follow specific JPA naming conventions (ex. `findByUsername(String username)`).
3. API Layer
   - `/api/.../core/book/models` and `/api/.../core/user/models`: contain DTO objects responsible for carrying data between the User Interface and Database Persistence layers. `/book-service/java/.../core/mapper/*Mapper.java` classes are used to convert DTO objects to DB entities, and vice-versa.
   - `/api/.../core/book/services/BookService.java` and `/api/.../core/user/services/UserService.java` classes: interfaces using Spring Boot annotations to define the REST endpoints of our application. Enpoints are defined using the `@*Mapping` annotations. Each endpoint is documented using the OpenAPI annotations (`@Operation`, `@ApiResponses`). These interfaces are implemented in the `/book-service/java/.../core/service/*ServiceImpl.java` classes.
   - `/book-service/java/.../core/manager/*.java` provide `*Repository.java` functionalities to `*ServiceImpl.java` implementations. They use the `/book-service/java/.../core/mapper/*Mapper.java` classes to convert DB entity data from repository classes to DTO objects used by the API and User Interface layers.

# Four Design Patterns
Here are four key design patterns that we have identified as essential to our application:

## Facade Pattern
### Problem
The Book Stroe service has divided it into subsystems to reduce complexities. For example, we have BookService to manage all the business logic of books, UserServices to manage users' business logic. And we have implemented the Spring JPA Data library to manage MySQL dada to CRUD (create, read, update and delete). Further than that, there are dependencies between those subsystems are exist. However, clients (frontend, mobile device, etc.) interacting with those subsystems classes to fulfill business requirements can result in a significant level of complexity. </p>
The Book Store app is designed for seller to sell their used textbook. To allow Seller to add and display the used books for sell, the following service complete the process:
  - **BookManager service**: Check the bookstore database running on MySQL for the book's availability.
  - **UserManager service**: Manager the user profile information.
  - **UserBookManager service**: Manager the books for sellers. The seller could add a text, list all the books he wants to sell, and he also cold removed his book from the display list.
  - **Spring MVC Controller**: A controller interacts with the preceding services for list books. When a user interacts with the UI to manger the used textbooks that he wants to sell, the request is mapped to the controllers, which is run interacts with the services to fulfill the request and then respond to the correct information and status of the request.

The used books selling store is a web-based app. But we also plan to make a selling apps are to support mobile clients. So the users could download the app and manage their books for sale from their devices. The following diagram shows how different clients interact with used book store services and their complexity.</p>
![Facade Pattern UML](images/uml-facade-pattern-2.png)
From the above diagram, we could see that the front end clients are tightly coupled with the systems classes. Therefore, if we add or update some part of the services, there will impact the existing system. For example, add a review service to manager user’s review of the sellers; all of the interacted classes need to update to adapt to the new service. </p>

### Solution
  Accordingly, we can implement Facade Pattern into the systems. We are creating a façade class, UserService. Clients can interact with faced classes for users' book management instead of interact with individual subsystem services.
### Implementation
![Facade Pattern UML](images/uml-facade-pattern-1.png)

## Adapter Pattern
### Problem
 - On the UI, we want to display User information (ex. full name, email, etc.) alongside book information.
 - The Book API entities used by the frontend beans contain only a user’s ID.
 - The JSF framework (used to display information on the UI) requires that we associate Book and User information within the same class. It is not good style to do something like getUserFor(book) once the book has been passed along to the UI. 
### Solution
 - We created an interface modeling the information expected by the UI (BookListing.java)
 - We used to Adapter pattern to make the Book API entity compatible  with this interface (BookListingAdapter.java)
### Implementation
![Adapter Pattern UML](images/uml-adapter-pattern.png)

## Factory Pattern
### Problem
  - In our application, there is more than one kind of book. We can have Hardcover, Paperback, and Digital books.
  - We model in the API Layer this by implementing:
      - One abstract class, `Book`
      - Three sub-classes of `Book`: `HardCoverBook`, `PaperBackBook`, and `DigitalBook`
  - We model this in the Database Persistence Layer by implementing:
      - One abstract class, `BookEntity`
      - Three sub-classes of `BookEntity`: `HardCoverBookEntity`, `PaperBackBookEntity`, and `DigitalBookEntity`
  - The `BookRepository` class returns objects of type `BookEntity`, but the API Layer expects objects of type `Book`. The Database Persistence Layer must convert all `BookEntity` objects to type `Book` before they can be received by API Layer, and vice-versa.
   - But we will need to instantiate different sub-classes of the `Book` class depending on what sub-class of `BookEntity` we are converting, and vice-versa.
### Solution
  - Use the factory pattern to define `APIFactory` and `EntityFactory` classes who, when given a `Book` or `BookEntity` object, will instantiate and return the appropriate subclass for that object.
### Implementation
![Factory Pattern Implementation](images/uml-factory-pattern.png)

# Contributions
This project was made possible through the hard work and dedication of all 5 team members. Each team member provided a unique set of skills to the project, and not all contributions are reflected in the number of commits made to the codebase. Because not all team members were familiar with the Spring Boot Framework, a lot of their contribution was made either later in project’s development (once they had time to learn about Spring) or during the requirements/design phase of the project.

Here is what each group member contributed to the project:
- Bin Lao
  - Configured initial Spring Boot project
  - Created REST endpoints
  - Created documentation for REST endpoints using the OpenAPI Specification
  - Configured Spring Boot JPA + MySQL implementation
  - Configured database persistence for Book entities
  - Created UML diagram describing facade pattern implementation
  - Dockerized application
- Logan Fitzpatrick
  - Refactored backend code for consistency:
    - Re-wrote database persistence implementation for User entities
    - Created User DTO classes in `api` project
- Patrick Mollins
  - Created SQL script to generate data for testing the application
  - Planned, recorded, and edited presentation video
- Qihao Guo
  - Implemented mapping functionality for User API to database entity classes
  - Implemented factory pattern for creating instances of the abstract classes `Book` and `BookEntity`
  - Created UML diagram describing factory pattern implementation
- Casey O'Neill
  - Configured JSF framework integration (including Dockerization of components)
  - Configured Spring Security integration
  - Created user interface screens (implemented as `.xhtml` files)
  - Created management beans for each user interface screen (connecting frontend UI to backend persistence functionality)
  - Configured inital database persistence implementation for User entities
  - Created UML diagram describing adapter pattern implementation
  - Created project report
