package com.example.spacewar;

// Interfaz para observadores del estado del juego
// Implementada por HUDUpdater
// impmentando asi el patrón Observer
public interface GameStateObserver  {
    void update();
}
