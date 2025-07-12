# 📝 Journal App

A modern, full-stack **Journal App** built with **Spring Boot**, integrated with **MongoDB**, **Redis**, and **Apache Kafka**. This application allows users to create, read, update, and delete journal entries, with robust support for event-driven messaging and caching.

> 🔧 Based on the YouTube series by [Engineering Digest](https://www.youtube.com/playlist?list=PLA3GkZPtsafacdBLdd3p1DyRd5FGfr3Ue)

---

## 🚀 Tech Stack

| Layer       | Technology          |
|-------------|---------------------|
| Backend     | Java, Spring Boot   |
| Database    | MongoDB             |
| Caching     | Redis               |
| Messaging   | Apache Kafka        |
| API Tooling | Spring Web, Spring Data MongoDB, Spring Cache |
| Build Tool  | Maven               |
| IDE         | IntelliJ IDEA       |

---

## ✨ Features

- 🔐 Create, Read, Update, Delete (CRUD) journal entries
- ⚡ Fast reads via **Redis caching**
- 🔊 **Kafka** event stream for entry tracking and decoupled services
- 📦 MongoDB for flexible and scalable data storage
- 🧪 RESTful APIs built using **Spring Boot**

---

## 📂 Project Structure
journal-app/  
├── controller/ # Handles incoming HTTP requests and routes them to services  
├── service/ # Contains business logic and service-layer abstractions  
├── repository/ # Manages database interactions (MongoDB)  
├── model/ # Domain models and DTOs  
├── config/ # Configuration classes (Kafka, Redis, MongoDB, etc.)  
└── util/ # Utility classes and helpers  


---

## 🛠️ Getting Started

### Prerequisites

- Java 17+
- Maven
- MongoDB running locally or on Atlas
- Redis running locally
- Apache Kafka running locally (Zookeeper + Kafka broker)

### Clone the repository

git clone https://github.com/Thegeekgirl19/journalApp.git
cd journalApp```


## 🔄 Kafka Topics

| Topic             | Purpose                                   |
|-------------------|--------------------------------------------|
| `journal.created` | Emitted when a new journal entry is created |
| `journal.updated` | Emitted when a journal entry is updated     |
| `journal.deleted` | Emitted when a journal entry is deleted     |
## 💡 API Endpoints

### 📄 Journal Entry APIs

| Method | Endpoint               | Description                     |
|--------|------------------------|---------------------------------|
| `GET`  | `/api/journals`        | Get all journal entries         |
| `GET`  | `/api/journals/{id}`   | Get journal entry by ID         |
| `POST` | `/api/journals`        | Create a new journal entry      |
| `PUT`  | `/api/journals/{id}`   | Update existing journal entry   |
| `DELETE` | `/api/journals/{id}` | Delete journal entry by ID      |

### 🔊 Kafka Test Endpoints (Optional)

If you’ve exposed Kafka-producing endpoints for testing:

| Method | Endpoint                  | Description                          |
|--------|---------------------------|--------------------------------------|
| `POST` | `/api/kafka/publish`      | Publish a test message to a topic    |
| `GET`  | `/api/kafka/messages`     | Fetch recent messages (if supported) |
