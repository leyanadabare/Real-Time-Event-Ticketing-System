# Real-Time Event Ticketing System

## Project Description
The **Real-Time Event Ticketing System** is a robust application designed for simulating and managing real-time ticket sales for events. It features:
1. **CLI Version**: A console-based interface to simulate ticket transactions.
2. **GUI Version**: A web-based interactive system developed using Angular and Spring Boot.

The system supports multi-threaded operations for:
- **Vendors** adding tickets to a shared ticket pool.
- **Customers** purchasing tickets while ensuring concurrency and data consistency.

Additional features include real-time logging, configurable simulation settings, and a seamless frontend-backend integration.

---

## Features

### General Features:
- Multi-threaded ticket simulation for real-time operations.
- Configurable vendor and customer settings.
- Comprehensive real-time logging for simulation events.
- Ability to save and reload configurations.

### CLI Version:
- Command-line interface for ticket transaction simulation.
- Detailed log outputs for event tracking.

### GUI Version:
- User-friendly Angular-based frontend.
- Real-time display of ticket pool status and logs.
- RESTful APIs to manage simulation and configurations.

---

## Project Structure

### Backend (Spring Boot)

#### Directories and Key Components:
- **`config`**:
    - `CorsConfig`: Configures Cross-Origin Resource Sharing (CORS) settings.
    - `SecurityConfig`: Manages security settings, including CSRF disabling.
    - `WebConfig`: Handles static resource configurations.
- **`controller`**:
    - `ConfigurationController`: Manages API endpoints for starting/stopping simulations.
    - `LoggingController`: Handles API requests for fetching logs.
- **`objects`**:
    - Core entities include `Customer`,`Ticket`, `TicketPool`, and `Vendor`.
- - **`util`**:
    - Core entities include `Configuration`,`Logging`, `Main`, and `Services`.
- **`repository`**:
    - DAOs such as `CustomerDAO`, `EventDAO`, `TicketDAO`, and `VendorDAO` for data persistence.
- **`service`**:
    - `LoggingService`: Manages logging during simulation.
    - `ConfigurationService`: Handles saving and loading simulation configurations.

#### Resources:
- **`application.properties`**: Stores application-level settings.
- **`logger`**: Configures the logging framework.

---

### Frontend (Angular)

#### Directory and Components:
- **`components`**:
    - `configuration-form`: Captures simulation settings from users.
    - `control-panel`: Allows users to start/stop simulations.
    - `log-display`: Shows system logs in real-time.
    - `ticket-display`: Visualizes the current ticket pool status.
- **`services`**:
    - `shared.service.ts`: Manages HTTP API calls to the backend.
- **`styles`**:
    - `styles.css`: Provides global styling for the application.

#### Integration:
- The frontend communicates with the backend via HTTP API calls using the `SimulationService`.

---

## Technologies Used
- **Backend**: Spring Boot (Java)
- **Frontend**: Angular (TypeScript)
- **Concurrency**: Java multi-threading for real-time operations
- **Logging**: Logger for real-time and historical log management

---

## How to Run the Project

### Prerequisites
- **Backend**: Java Development Kit (JDK) 11+, Maven
- **Frontend**: Node.js, npm, Angular CLI

### Steps to Run

### Backend

#### Build and start the backend application:
```bash
mvn spring-boot:run
```

### Frontend

#### Navigate to the frontend folder:
```bash
cd frontend
```

#### Install required dependencies:
```bash
npm install
```

#### Start the Angular development server:
```bash
ng serve
```

Open the application at http://localhost:54194/

### CLI Version

#### Navigate to the backend directory:

```bash
cd backend
```

#### Compile and run the CLI application:
```bash
mvn compile
```
---
## API Endpoints

### Configuration Management
- **POST /api/configurations**: Save a new configuration.
- **GET /api/configurations**: Retrieve existing configurations.

### Simulation Control
- **POST /api/simulation/start**: Start the simulation.
- **GET /api/simulation/results**: Retrieve simulation results.

### Log Management
- **GET /api/logs**: Fetch real-time logs.

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.

---

## Author
- **Leyana Dabare**: Lead Developer
---

## Acknowledgements
- Special thanks to the Angular and Spring communities for their frameworks and libraries.




