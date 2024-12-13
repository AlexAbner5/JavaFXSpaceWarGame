package com.example.spacewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HUDUpdater implements GameStateObserver {

    // Variable para dibujar en el canvas
    private GraphicsContext gc;

    // Constructor
    public HUDUpdater(GraphicsContext gc) {
        this.gc = gc;
    }

    // Metodo para actualizar el HUD del juego (puntaje y nivel)
    @Override
    public void update() {
        GameState gameState = GameState.getInstance();

        // Limpia solo las áreas necesarias para el puntaje y el nivel
        gc.setFill(Color.grayRgb(20));

        // Limpia la región del puntaje
        gc.fillRect(50, 0, 120, 40);

        // Limpia la región del nivel
        gc.fillRect(SpaceWar.WIDTH - 150, 0, 120, 40);

        // Dibuja el puntaje
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(20));
        gc.fillText("Score: " + gameState.getScore(), 60, 20);

        // Dibuja el nivel
        gc.fillText("Level: " + gameState.getLevel(), SpaceWar.WIDTH - 100, 20);
    }
}

// Esto implemnta el patrón Observer, ya que la clase HUDUpdater implementa la interfaz GameStateObserver
// observando el estado del juego y actualizando el HUD en consecuencia.
