## dungeon master screen

Fullstack application built with Spring Boot, PostgreSQL, and React/Vite.

### Prerequisites

Java 21
Maven
Node.js and npm
Docker

### Start the database

Start the PostgreSQL container:
```bash
docker start dnd-postgres
```

The database will be available on:
```bash
localhost:5432
```

### Start the backend

Open a terminal and run:
```bash
cd backend
mvn spring-boot:run
```

The backend API will be available on:
```bash
http://localhost:8080
```

### Start the frontend

Open a second terminal and run:
```bash
cd frontend
npm install
```

The install step is only required the first time or when dependencies have changed.

### Then start the development server:

```Bash
npm run dev
```

The frontend will be available on:
```bash
http://localhost:5173
```

### Development setup

The application requires the following services to be running:
```Bash
PostgreSQL -> localhost:5432
Spring Boot -> localhost:8080
React/Vite -> localhost:5173
```