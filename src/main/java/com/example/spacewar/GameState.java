package com.example.spacewar;

import java.util.List;
import java.util.ArrayList;

public class GameState {
    // variables
    private static GameState instance;
    private int score;
    private int level;
    private boolean gameOver;
    private static int gameCount = -1;
    private boolean isSaved;
    private List<GameStateObserver> _observers;

    // Constructor
    private GameState() {
        _observers = new ArrayList<>();
        resetState();
    }

    // Obtiene la instancia única de la clase GameState
    public static synchronized GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    // Agrega un observador al estado del juego
    public void addObserver(GameStateObserver observer) {
        _observers.add(observer);
    }

    // Notifica a los observadores que el estado del juego ha cambiado
    protected void notifyObservers() {
        for (GameStateObserver observer : _observers) {
            observer.update();
        }
    }

    // Obtiene el puntaje actual
    public int getScore() {
        return score;
    }

    // Incrementa el puntaje y notifica a los observadores
    public void increaseScore(int increment) {
        this.score += increment;
        notifyObservers(); // Notifica a los observadores
    }

    // Obtiene el nivel actual
    public int getLevel() {
        return level;
    }

    // Incrementa el nivel y notifica a los observadores
    public void levelUp() {
        this.level++;
        notifyObservers();
    }

    // Obtiene si el juego ha terminado
    public boolean isGameOver() {
        return gameOver;
    }

    // Es para establecer que ha terminado y notificar a los observadores
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;

        if (gameOver && !isSaved) { // Guarda solo si no se ha guardado ya
            saveHighScore(); // Guarda los datos al finalizar la partida
            isSaved = true; // Marca como guardado
        }
        notifyObservers();
    }

    // Obtiene el puntaje más alto y lo guarda
    public void saveHighScore() {
        if (!isSaved) { // Evita múltiples guardados
            FileManager.saveHighScore(gameCount, score, level); // Guarda la partida actual
        }
    }

    // Restablece el estado del juego
    public void resetState() {
        if (!gameOver) { // Incrementa solo si la partida anterior terminó
            gameCount++;
        }
        this.score = 0;
        this.level = 1;
        this.gameOver = false;
        isSaved = false; // Marca como no guardado al iniciar una nueva partida
        notifyObservers();
    }
}

// Esta clase implementa el patrón Singleton, que garantiza que solo exista una instancia de la clase GameState en la aplicación.
// La clase GameState mantiene el estado del juego, incluido el puntaje, el nivel y el estado del juego (si el juego ha terminado o no).
// También proporciona métodos para aumentar el puntaje, subir de nivel, verificar si el juego ha terminado y guardar el puntaje más alto.
// La clase GameState también notifica a los observadores (GameStateObserver) cuando cambia el estado del juego.
