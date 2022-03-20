# Course Project Report

## Explanation of Software

## Four Key Design Patterns
Here are four key design patterns that we have identified as essential to our application:
1. Facade Pattern
2. Adapter Pattern
   - Problem:
     - Books are created by users.
     - On the UI, we want to display user information (ex. full name, email, etc.) alongside book information.
     - 
3. Factory Pattern
4. ???

## Contributions 
This project was made possible through the hard work and dedication of all 5 team members. Each team member provided a unique set of skills to the project, and not all contributions are reflected in the number of commits made to the codebase. Because not all team members were familiar with the Spring Boot Framework, a lot of their contribution was made either later in project’s development (once they had time to learn about Spring) or during the requirements/design phase of the project.

Here is what each group member contributed to the project:
- Bin Lao
- Logan Fitzpatrick
  - Refactored backend code for consistency:
    - Re-wrote database persistence implementation for User entities
    - Created User DTO classes in `api` project
- Patrick Mollins
  - Created SQL script to generate data for testing the application
- Qihao Guo
  - Implemented mapping functionality for User API to database entity classes
- Casey O'Neill
  - Configured JSF framework integration
  - Configured Spring Security integration
  - Created user interface screens (implemented as `.xhtml` files)
  - Created management beans for each user interface screen (connecting frontend UI to backend persistence functionality)
  - Configured inital database persistence implementation for User entities
