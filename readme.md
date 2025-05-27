# 📝 Collaborative Editing Tool Backend

A real-time collaborative document editing backend built with **Spring Boot**, **PostgreSQL**, **Redis**, and **WebSockets**.

## 🚀 Features

- Create, update, delete, and fetch documents
- Real-time document collaboration with WebSocket support
- Redis-powered caching and session management
- PostgreSQL for persistent storage

## 🛠️ Tech Stack

- Java + Spring Boot
- PostgreSQL
- Redis (Cache & Session)
- WebSocket (Spring Messaging)
- Spring Session, Spring Cache, Spring Data JPA

## ⚙️ Setup

1. **Clone the repo**

```bash
git clone https://github.com/mahingaRodin/collaborative-editing-tool.git
cd collaborative-editing-tool
```

2. **Configure `application.properties`**

Make sure your PostgreSQL and Redis instances are running, then update credentials as needed.

3. **Run the app**

```bash
./mvnw spring-boot:run
```

## 📄 License

MIT License — free to use and modify.
