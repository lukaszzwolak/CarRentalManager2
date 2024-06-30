# Car Rental Manager

A comprehensive system for managing cars, clients, employees, invoices, and reservations.

Small application made as a graduation project for Java: Web Developer course by CodersLab Warsaw. My objectives:
solve real life issue (however trivial it will be)
communicate with external APIs
utilize knowledge from the course and learn some new technologies/classes/interfaces

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Setup](#setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features
- Add, edit, view, and delete cars
- Add, edit, view, and delete clients
- Manage employees and their information
- Generate and manage invoices
- Create and manage reservations

## Technologies
- Java 21
- Spring Boot
- Thymeleaf
- HTML
- MySQL
- JPA & Hibernate

## Setup

### Prerequisites
- Java 21
- Maven

### Installation
1. Clone the repository
    ```bash
    git clone https://github.com/your-username/car-rental-manager.git
    ```
2. Navigate into the project directory
    ```bash
    cd car-rental-manager
    ```
3. Build the project with Maven
    ```bash
    mvn clean install
    ```

### Running the Application
1. Start the application
    ```bash
    mvn spring-boot:run
    ```
2. Open your browser and navigate to `http://localhost:8080`

## Usage

### Managing Cars
- **Add Car**: Navigate to `/car/add` and fill in the required fields to add a new car.
- **Edit Car**: Navigate to `/car/edit/{id}` where `{id}` is the ID of the car you want to edit.
- **View Cars**: Navigate to `/car/list` to see a list of all cars.
- **Delete Car**: Navigate to `/car/remove/{id}` where `{id}` is the ID of the car you want to delete.

## Contributing
1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
6. Open a Pull Request
   
## Database schema
![carrentdb](https://github.com/lukaszzwolak/CarRentalManager/assets/145567355/43493217-a8c0-496c-8a5a-e7d8f6cc3ab3)

## Contact
Please add suggestions or contact me if you like my project.
