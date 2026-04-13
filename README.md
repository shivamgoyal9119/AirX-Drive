# AirX-Drive
ARX Drive - A scalable cloud file storage platform inspired by Google Drive, built with Spring Boot Microservices using Netflix Eureka for service discovery, API Gateway for routing, Load Balancing, JPA with H2 database, and a React-based UI.

The system is built using Spring Boot Microservices, leveraging Netflix Eureka for service discovery, API Gateway for centralized routing, and Load Balancing for scalability and fault tolerance. It uses JPA with an H2 in-memory database for fast data handling and a React frontend for a smooth user experience.

Additionally, the project includes an Admin Panel Service for managing system-level operations, making the platform more robust and closer to real-world cloud storage systems like Google Drive.

It allows users to upload, manage, and organize files efficiently with a scalable backend powered by Eureka Service Discovery, API Gateway routing, and Load Balancing.

📌 Features
📁 Upload and manage files
📂 Create and organize folders
🌐 REST API-based backend
⚡ Microservices architecture
🔁 Load balancing using Netflix Eureka
🌍 API Gateway for centralized routing
💾 H2 in-memory database with JPA
🖥️ React-based user interface
🛠️ Admin Panel for system monitoring & management
🔄 End-to-end integration between frontend and backend

🏗️ Architecture

The project follows a Microservices Architecture:

Eureka Server → Service discovery
API Gateway → Routes client requests
File Service → Handles file operations
Folder Service → Manages folder structure
Admin Service → Admin panel & system control
React Frontend → User interface

🛠️ Tech Stack
=> Backend
-> Java
-> Spring Boot
-> Spring Data JPA
-> Hibernate
-> REST APIs
-> Maven
-> Microservices & Cloud
-> Netflix Eureka (Service Discovery)
-> Spring Cloud Gateway (Routing)
-> Spring Cloud Load Balancer

=> Database
-> H2 Database (In-Memory)

=> Frontend
-> React.js
-> HTML5 & CSS3

⚙️ Project Structure:  

arx-drive/
│   
├── eureka-server/      # Service discovery server   
├── api-gateway/        # API Gateway for routing  
├── file-service/       # Handles file operations
├── folder-service/     # Manages folder structure  
├── admin-service/      # Admin panel backend
├── frontend/           # React UI
└── README.md  

🔄 How It Works
. React frontend sends request → API Gateway
. API Gateway routes request → appropriate microservice
. Eureka identifies available service instances
. Load balancer distributes traffic
. Service processes request using JPA + H2
. Response returned to frontend

🛠️ Admin Panel (NEW)
The Admin Panel Service provides:

📊 System monitoring
📁 File & folder management control
⚙️ Backend service visibility
🔧 Administrative operations

This enhances the system to behave more like a real-world cloud platform.






