# Logicea
An application named cards application that allows users to create and manage tasks in the form of cards

## Features
* login with JWT authentication.
* Members have access to cards they created.
* Admins have access to all cards.
* A user creates a card by providing a name for it and, optionally, a description and a color.
* A user can search through cards they have access to.
* A user can request a single card they have access to.
* A user can update the name, the description, the color and/or the status of a card they have access to.
* A user can delete a card they have access to.
* Refresh token

  ## Technologies
* Spring Boot 3.0
* Spring Security
* Spring Data
* JSON Web Tokens (JWT)
* BCrypt
* Maven
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+
* Docker desktop
To build and run the project, follow these steps:

* Clone the repository: `git clone https://github.com/ali-bouali/spring-boot-3-jwt-security.git](https://github.com/ekcareer/Logicea.git`
* Navigate to the project directory: cd cards
* Run docker desktop because of MYSQL database container to run
* Build the project: mvn clean install
* Run the project: mvn spring-boot:run 

-> The application will be available at http://localhost:8080.
-> The application will be available at http://localhost:8080](http://localhost:8080/swagger-ui/index.html.
