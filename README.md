# 🚀 Space War Game

![SpaceWar](https://img.shields.io/badge/JavaFX-23.0.1-blue) ![JPro](https://img.shields.io/badge/JPro-2024.4.1-blue) ![Maven](https://img.shields.io/badge/Maven-3.8.6-brightgreen)

**Space War** is a space combat game developed with **JavaFX** and deployed using **JPro** and **Docker**. The project incorporates several design patterns and programming best practices.

---

## 🎥 **Game Demonstration**

Watch the demo video of the project:  
[![Space War Video](https://img.youtube.com/vi/ljlll3Hr13M/0.jpg)](https://www.youtube.com/watch?v=ljlll3Hr13M)

---

## ⚙️ **Technologies Used**

- **Language**: Java 21  
- **Framework**: JavaFX 23.0.1 
- **Deployment**: JPro, Maven  
- **Dependency Management**: Maven 
- **Docker**: To package and deploy using containers  

---

## 🧩 **Implemented Design Patterns**

1. **Singleton**:  
   - The `GameState` class maintains a global state of the game (score, levels, etc.).

2. **Factory Method**:  
   - The `GameFactory` class centralizes the creation of objects like bullets, players, and enemies.

3. **Observer**:  
   - The HUD is automatically updated when `GameState` changes, implementing the Observer pattern.
   - 
---

## 🎮 **How to Run the Project**

### **Prerequisites**
1. Install **Java 21** or later.  
2. Install **Maven 3.8.6** or later.  
3. Download the JavaFX SDK (Version 23.0.1) from [OpenJFX](https://openjfx.io/).

## 📦 Running in Docker

To run the project in a Docker container:

### Build the Docker Image
docker build -t spacewar-game .


### Run the Container
docker run -p 8080:8080 spacewar-game

### Access the Game
Open your browser and visit:

http://localhost:8080


